package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Database;
import edu.hw8.Task1.Handler;
import edu.hw8.Task1.Server;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTask1 {

    private final int TIMEOUT = 1000;
    private final int SERVER_PORT = 4004;
    private final String SERVER_HOST = "localhost";
    private final int MAX_DEFAULT_CAPACITY = 5;
    private final Handler ECHO_HANDLER = (message) -> message;

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

        private Server server = new Server(ECHO_HANDLER);
        private Thread serverThread;

        @BeforeEach
        void setUp() {
            serverThread = new Thread(() -> server.start(SERVER_PORT));
            serverThread.start();
        }

        @Test
        void testServerIsRunning() throws InterruptedException {
            Thread.sleep(TIMEOUT);
            boolean isConnected = false;
            try {
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
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

        private Server server = new Server(ECHO_HANDLER);
        private Thread serverThread;

        @BeforeEach
        void setUp() {
            serverThread = new Thread(() -> server.start(SERVER_PORT));
            serverThread.start();
        }

        @Test
        void testGetServerMessage() throws Exception {
            Thread.sleep(TIMEOUT);

            var client = new Client();
            client.start(SERVER_HOST, SERVER_PORT);

            client.sendToServer("Hello world!");
            String response = client.readFromServer();

            assertThat(response).isNotNull();
            assertThat(response).isNotEmpty();

            client.close();
        }

        @Test
        void testBlockingQueue() throws Exception {
            Thread.sleep(TIMEOUT);

            var exec = Executors.newFixedThreadPool(4);

            var tasks = Stream.generate(() -> CompletableFuture.runAsync(() -> {
                    Client myConnection = new Client();
                    myConnection.start(SERVER_HOST, SERVER_PORT);
                }, exec))
                .limit(MAX_DEFAULT_CAPACITY + 1)
                .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(tasks).join();

            var currentCapacity = server.getAvailableConsumersCount();

            assertThat(currentCapacity).isEqualTo(MAX_DEFAULT_CAPACITY);
        }

        @AfterEach
        void tearDown() throws Exception {
            server.close();
            serverThread.join();
        }
    }
    @Nested
    class testingChatWithDatabase{

        private Database db = new Database();
        private Server server;
        private Thread serverThread;
        private final String CATEGORY = "интеллект";

        @BeforeEach
        void setUp() {
            server = new Server((message) -> db.getQuote(message));
            serverThread = new Thread(() -> server.start(SERVER_PORT));
            serverThread.start();
        }
        @Test
        void testGetQuoteFromServer() throws Exception {
            Thread.sleep(TIMEOUT);
            List<String> allQuotes = db.getQuotes(CATEGORY);


            var client = new Client();
            client.start(SERVER_HOST, SERVER_PORT);

            client.sendToServer(CATEGORY);
            String response = client.readFromServer();

            assertThat(response).isNotEmpty();
            assertThat(response).isNotNull();
            assertThat(allQuotes).contains(response);

            client.close();
        }
        @AfterEach
        void tearDown() throws Exception {
            server.close();
            serverThread.join();
        }
    }

}
