package edu.hw2.TestTask3;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionManagers;
import edu.hw2.Task3.Exceptions.ConnectionException;
import edu.hw2.Task3.Exceptions.ConnectionExistsErrorException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagersTest {
    @Test
    public void testIdealConnectionManager() {
        ConnectionManagers.IdealConnectionManager idealManager = new ConnectionManagers.IdealConnectionManager();

        Connection connection = idealManager.getConnection();
        assertNotNull(connection);

        try {
            idealManager.executeCommand("cmd");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            idealManager.closeConnection();
        } catch (ConnectionExistsErrorException e) {
            fail("Connection should not be closed already.");
        }
        try {
            idealManager.executeCommand("another_command");
            fail("Expected ConnectionExistsErrorException but it didn't");
        } catch (ConnectionExistsErrorException e) {
        }
    }

    @Test
    public void testFaultyConnectionManager() {
        ConnectionManagers.FaultyConnectionManager faultyManager = new ConnectionManagers.FaultyConnectionManager();

        int count = 0;
        while (true) {

            try {
                Connection connection = faultyManager.getConnection();
                assertNotNull(connection);

                break;
            } catch (ConnectionException e) {
                // Good too
            } catch (Exception e) {
                fail("Unexpected exception: " + e.getMessage());
            }

            count += 1;
            if (count > 1000) {
                fail("Persistent session creation error");
            }
        }

        try {
            faultyManager.executeCommand("cmd");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            faultyManager.closeConnection();
        } catch (ConnectionExistsErrorException e) {
            fail("Connection should not be closed already.");
        }
        try {
            faultyManager.executeCommand("another_command");
            fail("Expected ConnectionExistsErrorException but it didn't");
        } catch (ConnectionExistsErrorException e) {
        }
    }

    @Test
    public void testDefaultConnectionManager() {
        ConnectionManagers.DefaultConnectionManager defaultManager = new ConnectionManagers.DefaultConnectionManager();

        int count = 0;
        while (true) {

            try {
                Connection connection = defaultManager.getConnection();
                assertNotNull(connection);

                break;
            } catch (ConnectionException e) {
                // Good too
            } catch (Exception e) {
                fail("Unexpected exception: " + e.getMessage());
            }

            count += 1;
            if (count > 1000) {
                fail("Persistent session creation error");
            }
        }

        try {
            defaultManager.executeCommand("cmd");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        try {
            defaultManager.closeConnection();
        } catch (ConnectionExistsErrorException e) {
            fail("Connection should not be closed already.");
        }
        try {
            defaultManager.executeCommand("another_command");
            fail("Expected ConnectionExistsErrorException but it didn't");
        } catch (ConnectionExistsErrorException e) {
        }
    }
}
