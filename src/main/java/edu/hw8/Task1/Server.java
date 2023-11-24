package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Server implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static int DEFAULT_MAX_CAPACITY = 5;
    private final static int DEFAULT_MAX_THREADS = 4;
    private final static int DEFAULT_BYTEBYFFER_CAPACITY = 256;
    private final ExecutorService threadPool;
    private final ReentrantReadWriteLock locker;
    private final BlockingQueue blockingQueue;
    private final Handler handler;
    private ServerSocketChannel serverSocket;
    private Selector selector;

    public Server(Handler handler) {
        this.handler = handler;
        this.blockingQueue = new ArrayBlockingQueue<>(DEFAULT_MAX_CAPACITY);
        this.threadPool = Executors.newFixedThreadPool(DEFAULT_MAX_THREADS);
        this.locker = new ReentrantReadWriteLock(true);
    }

    public Server(int threads, int maxSizeQueue, Handler handler) {
        this.handler = handler;
        this.threadPool = Executors.newFixedThreadPool(threads);
        this.blockingQueue = new ArrayBlockingQueue<>(maxSizeQueue);
        this.locker = new ReentrantReadWriteLock(true);
    }

    public void start(int port) {
        try {
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            LOGGER.info("SERVER | Was started on " + serverSocket.getLocalAddress());

            while (selector.isOpen()) {
                selector.select();
                if (selector.isOpen()) { // Если мы действительно после блокировки еще открыты
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectedKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();

                        if (!key.isValid()) {
                            iter.remove();
                            blockingQueue.poll();
                            continue;
                        }
                        if (key.isAcceptable()) {
                            register(selector, serverSocket);
                        }
                        if (key.isReadable()) {
                            this.threadPool.submit(
                                () -> {
                                    try {
                                        answer(ByteBuffer.allocate(DEFAULT_BYTEBYFFER_CAPACITY), key);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            );
                        }
                    }
                }
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAvailableConsumersCount() {
        return this.blockingQueue.size();
    }

    private void answer(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();

        int numBytesRead = client.read(buffer);
        while (numBytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {

                ByteBuffer result = getResponseByHandler(buffer);

                client.write(result);
                LOGGER.info("SERVER | Answered to client " + client.getRemoteAddress());
            }
            buffer.clear();

            numBytesRead = client.read(buffer);
        }

        client.close();
        blockingQueue.remove(client);
    }

    private ByteBuffer getResponseByHandler(ByteBuffer buffer) {
        String message = UTF_8.decode(buffer).toString();
        String answer = handler.getResponse(message) + "\n";
        return ByteBuffer.wrap(answer.getBytes(UTF_8));
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException,
        InterruptedException {
        SocketChannel socket = serverSocket.accept();
        if (socket != null) {
            blockingQueue.put(socket);

            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_READ);
            LOGGER.info("SERVER | Registered a new connection " + socket.getRemoteAddress());
        }
    }

    @Override
    public void close() throws Exception {
        serverSocket.close();
        selector.close();
    }
}
