package edu.hw2.TestTask3;

import edu.hw2.Task3.CommandExecutor;
import edu.hw2.Task3.ConnectionManagers;
import edu.hw2.Task3.Exceptions.ConnectionExistsErrorException;
import edu.hw2.Task3.Exceptions.FaultyCommandExecute;
import edu.hw2.Task3.Exceptions.OutOfLimitMaxAttemptsExecutions;
import edu.hw2.Task3.Interfaces.IConnectionManager;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.fail;

public class ExecutorTest {
    @Test
    public void testExecuteValidCommand() {
        ConnectionManagers.IdealConnectionManager manager = new ConnectionManagers.IdealConnectionManager();
        CommandExecutor.PopularCommandExecutor executor = new CommandExecutor.PopularCommandExecutor(manager, 3);
        executor.tryExecute("ls -l");
    }

    @Test
    public void testConnectionAlreadyClosedException() {
        ConnectionManagers.IdealConnectionManager manager = new ConnectionManagers.IdealConnectionManager();
        CommandExecutor.PopularCommandExecutor executor = new CommandExecutor.PopularCommandExecutor(manager, 3);
        manager.closeConnection();
        try {
            executor.tryExecute("ls -l");
            fail("Expected ConnectionExistsErrorException");
        } catch (ConnectionExistsErrorException e) {
            // Expected exception
        }
    }
}
