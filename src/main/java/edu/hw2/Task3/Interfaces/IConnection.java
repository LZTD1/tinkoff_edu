package edu.hw2.Task3.Interfaces;

public interface IConnection extends AutoCloseable {
    void execute(String command);
}
