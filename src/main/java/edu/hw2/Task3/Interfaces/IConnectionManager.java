package edu.hw2.Task3.Interfaces;

import edu.hw2.Task3.Connection;

public interface IConnectionManager {
    Connection getConnection();

    void executeCommand(String command);

    void closeConnection();
}
