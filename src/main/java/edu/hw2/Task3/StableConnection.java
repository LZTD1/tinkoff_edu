package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.IConnectionManager;

public class StableConnection implements IConnectionManager {
    private Connection objConnection;

    public StableConnection() {
    }

    @Override
    public Connection getConnection() {
        Connection connection = new Connection();
        this.objConnection = connection;
        return connection;
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
