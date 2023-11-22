package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Database;
import edu.hw8.Task1.Server;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {
    @Nested
    class TestDatabase {
        private final Database db = new Database();

        @Test
        void testGetOneQuote() {
            String category = "глупый";

            String quote = db.getQuote(category);

            assertThat(db.getAllDatabase().get(category)).contains(quote);
        }

        @Test
        void testGetAllQuotesByCategory() {
            String category = "глупый";

            List<String> quotes = db.getQuotes(category);

            assertThat(db.getAllDatabase().get(category)).isEqualTo(quotes);
        }
    }

    @Nested
    class ServerTest {

        private Server server = new Server();
        private Thread serverThread;

        @BeforeEach
        void setUp() {
            serverThread = new Thread(() -> server.start(4004));
            serverThread.start();
        }

        @Test
        void testServerIsRunning() throws InterruptedException {
            Thread.sleep(1000);
            boolean isConnected = false;
            try {
                Socket socket = new Socket("localhost", 4004);
                isConnected = socket.isConnected();
            } catch (IOException e) {
            }

            assertTrue(isConnected);
        }

        @AfterEach
        void tearDown() throws Exception {
            server.close();
            serverThread.join();
        }
    }

    @Nested
    class ClientTest {

        private Server server = new Server();
        private Thread serverThread;

        @BeforeEach
        void setUp() {
            serverThread = new Thread(() -> server.start(4004));
            serverThread.start();
        }

        @Test
        void testServerIsRunning() throws InterruptedException {
            Thread.sleep(1000);
            boolean isConnected = false;
            try {
                Socket socket = new Socket("localhost", 4004);
                isConnected = socket.isConnected();
            } catch (IOException e) {
            }

            assertTrue(isConnected);
        }

        @Test
        void testClientServer() throws Exception {
            Thread.sleep(1000);

            var client = new Client();
            client.start("localhost", 4004);
            client.sendToServer("Hello world!");
            var response = client.readFromServer();
            client.sendToServer("Hello world2!");
            var response2 = client.readFromServer();

        }

        @AfterEach
        void tearDown() throws Exception {
            server.close();
            serverThread.join();
        }
    }
}
