package edu.hw2.TestTask3;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.Exceptions.ConnectionException;
import edu.hw2.Task3.Exceptions.ConnectionExistsErrorException;
import edu.hw2.Task3.Exceptions.FaultyCommandExecute;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.StableConnection;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionTest {
    @Test
    public void testConnectionCore() {
        Connection con = new Connection();
        assertNotNull(con);

        try{
            con.execute("cmd");
        }catch (FaultyCommandExecute e){
            fail("Execute command in Connection Core was failed");
        }
        try{
            con.close();
        }catch (ConnectionExistsErrorException e){
            fail("Close command in Connection Core was failed");
        }
    }
    @Test
    public void testFaultyConnection() {
        FaultyConnection fCon = new FaultyConnection();

        int susessfull = 0;
        int failed = 0;
        while(true){

            try {
                Connection connection = fCon.getConnection();
                assertNotNull(connection);

                try{
                    connection.execute("cmd");
                }catch (FaultyCommandExecute e){
                    fail("Execute command in FaultyConnection was failed");
                }
                try{
                    connection.close();
                }catch (ConnectionExistsErrorException e){
                    fail("Close command in FaultyConnection was failed");
                }

                susessfull += 1;
            } catch (ConnectionException e) {
                // Good too
                failed += 1;
            }catch (Exception e) {
                fail("Unexpected exception: " + e.getMessage());
            }

            if(failed + susessfull > 1200){
                if(failed > susessfull * 2){
                    fail("Connection error occurs too often!");
                }else{
                    break;
                }
            }
        }
    }

    @Test
    public void testStableConnection() {
        StableConnection sCon = new StableConnection();

        try {
            Connection connection = sCon.getConnection();
            assertNotNull(connection);

            try{
                connection.execute("cmd");
            }catch (FaultyCommandExecute e){
                fail("Execute command in FaultyConnection was failed");
            }
            try{
                connection.close();
            }catch (ConnectionExistsErrorException e){
                fail("Close command in FaultyConnection was failed");
            }

        }catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

}
