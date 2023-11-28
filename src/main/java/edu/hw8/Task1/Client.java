package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger();
    private Socket clientSocket;
    private BufferedReader inStream;
    private BufferedWriter outStream;

    public Client() {
    }

    public void start(String host, int port) {
        try {
            clientSocket = new Socket(InetAddress.getByName(host), port);
            createStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createStreams() {
        try {
            inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToServer(String line) {
        try {
            outStream.write(line + "\n");
            outStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFromServer() {
        String message;
        try {
            message = inStream.readLine();
            LOGGER.info("CLIENT | Received message from server " + message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
        try {
            inStream.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
        try {
            outStream.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
