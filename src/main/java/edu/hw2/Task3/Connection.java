package edu.hw2.Task3;

import edu.hw2.Task3.Exceptions.FaultyCommandExecute;
import edu.hw2.Task3.Interfaces.IConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection implements IConnection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        try {
            LOGGER.info("Executed command [" + command + "]");
        } catch (RuntimeException e) {
            throw new FaultyCommandExecute("Failed command execute!");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Connection successful close!");
    }
}
