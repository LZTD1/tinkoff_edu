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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger();
    private final ExecutorService threadPool;
    private final ReentrantReadWriteLock locker;
    private ServerSocketChannel serverSocket;
    private Selector selector;

    public Server() {
        this.threadPool = Executors.newFixedThreadPool(4);
        this.locker = new ReentrantReadWriteLock(true);
    }

    public Server(int threads, ReentrantReadWriteLock locker) {
        this.threadPool = Executors.newFixedThreadPool(threads);
        this.locker = new ReentrantReadWriteLock(true);
    }

    public void start(int port) {
        try {
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(256);
            LOGGER.info("SERVER | Was started on " + serverSocket.getLocalAddress());

            while (selector.isOpen()) {
                selector.select();
                if (selector.isOpen()) { // Если мы действительно после блокировки еще открыты
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = selectedKeys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();

                        if (key.isAcceptable()) {
                            register(selector, serverSocket);
                        }
                        if (key.isReadable()) {
                            this.threadPool.submit(
                                () -> {
                                    try {
                                        answer(buffer, key);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            );
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void answer(ByteBuffer buffer, SelectionKey key) throws IOException {
        locker.writeLock().lock(); // Потому что один буфер на все потоки
        // или лучше создавать каждому потоку по буферу?

        SocketChannel client = (SocketChannel) key.channel();

        int numBytesRead = client.read(buffer);
        while (numBytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                client.write(buffer);
                LOGGER.info("SERVER | Answered to client " + client.getLocalAddress());
            }
            buffer.clear();

            numBytesRead = client.read(buffer);
        }

        client.close();

        locker.writeLock().unlock();
    }

    private void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel socket = serverSocket.accept();
        if (socket != null) {
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
