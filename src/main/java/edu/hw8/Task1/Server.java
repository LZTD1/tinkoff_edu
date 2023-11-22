package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger();
    private ServerSocketChannel serverSocket;
    private Selector selector;

    public Server() {
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
                            answer(buffer, key);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void answer(ByteBuffer buffer, SelectionKey key) throws IOException {
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
