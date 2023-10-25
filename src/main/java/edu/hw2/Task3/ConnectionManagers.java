package edu.hw2.Task3;

import edu.hw2.Task3.Exceptions.ConnectionExistsErrorException;
import edu.hw2.Task3.Interfaces.IConnectionManager;
import java.util.concurrent.ThreadLocalRandom;

public class ConnectionManagers {

    private static final String ERROR_CONNECTION_EXISTS = "The connection was either deleted or not created!";
    private static final int DEFAULT_FAULTY_PROPERTY = 50;
    private static final int RANDOM_GENERATE_FAULTY_MAX_BOUND = 100;

    public ConnectionManagers() {
    }

    public static class IdealConnectionManager implements IConnectionManager {

        private Connection objStableConnection;

        public IdealConnectionManager() {

        }

        @Override
        public Connection getConnection() {
            Connection stableConnection = new StableConnection().getConnection();
            this.objStableConnection = stableConnection;
            return this.objStableConnection;
        }

        @Override
        public void executeCommand(String command) {
            if (this.objStableConnection != null) {
                this.objStableConnection.execute(command);
            } else {
                throw new ConnectionExistsErrorException(ERROR_CONNECTION_EXISTS);
            }
        }

        @Override
        public void closeConnection() {
            if (this.objStableConnection != null) {
                this.objStableConnection.close();
                this.objStableConnection = null;
            } else {
                throw new ConnectionExistsErrorException(ERROR_CONNECTION_EXISTS);
            }
        }
    }

    public static class FaultyConnectionManager implements IConnectionManager {
        private Connection objFaultyConnection;

        public FaultyConnectionManager() {

        }

        @Override
        public Connection getConnection() {
            Connection faultyConnection = new FaultyConnection().getConnection();
            this.objFaultyConnection = faultyConnection;
            return this.objFaultyConnection;
        }

        @Override
        public void executeCommand(String command) {
            if (this.objFaultyConnection != null) {
                this.objFaultyConnection.execute(command);
            } else {
                throw new ConnectionExistsErrorException(ERROR_CONNECTION_EXISTS);
            }
        }

        @Override
        public void closeConnection() {
            if (this.objFaultyConnection != null) {
                this.objFaultyConnection.close();
                this.objFaultyConnection = null;
            } else {
                throw new ConnectionExistsErrorException(ERROR_CONNECTION_EXISTS);
            }
        }
    }

    public static class DefaultConnectionManager implements IConnectionManager {
        private final double faultyProbably;
        private Connection objDefaultConnection;

        public DefaultConnectionManager() {
            this.faultyProbably = DEFAULT_FAULTY_PROPERTY;
        }

        public DefaultConnectionManager(double faultyProbably) {
            this.faultyProbably = faultyProbably;
        }

        public Connection getConnection() {
            if (this.faultyProbably <= ThreadLocalRandom.current().nextInt(0, RANDOM_GENERATE_FAULTY_MAX_BOUND)) {
                Connection defaultConnection = new FaultyConnection().getConnection();
                this.objDefaultConnection = defaultConnection;
            } else {
                Connection defaultConnection = new StableConnection().getConnection();
                this.objDefaultConnection = defaultConnection;
            }
            return this.objDefaultConnection;
        }

        @Override
        public void executeCommand(String command) {
            if (this.objDefaultConnection != null) {
                this.objDefaultConnection.execute(command);
            } else {
                throw new ConnectionExistsErrorException(ERROR_CONNECTION_EXISTS);
            }
        }

        @Override
        public void closeConnection() {
            if (this.objDefaultConnection != null) {
                this.objDefaultConnection.close();
                this.objDefaultConnection = null;
            } else {
                throw new ConnectionExistsErrorException(ERROR_CONNECTION_EXISTS);
            }
        }
    }
}
