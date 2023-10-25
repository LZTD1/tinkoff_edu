package edu.hw2.Task3;

import edu.hw2.Task3.Exceptions.ConnectionException;
import edu.hw2.Task3.Interfaces.IConnectionManager;
import java.util.concurrent.ThreadLocalRandom;

public class FaultyConnection implements IConnectionManager {

    static final int RANDOM_ERROR_OCCURRENCE_BOUND = 4;
    static final int CHANCE_OF_ERROR_OCCURRENCE = 3;

    public FaultyConnection() {
    }

    @Override
    public Connection getConnection() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, RANDOM_ERROR_OCCURRENCE_BOUND);
        if (randomNum == CHANCE_OF_ERROR_OCCURRENCE) {
            throw new ConnectionException("Faulty connection!");
        } else {
            return new Connection();
        }
    }

    @Override
    public void executeCommand(String command) {
        this.executeCommand(command);
    }

    @Override
    public void closeConnection() {
        this.closeConnection();
    }
}
