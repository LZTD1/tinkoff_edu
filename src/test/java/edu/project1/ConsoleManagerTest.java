package edu.project1;

import edu.project1.Utils.ConsoleManager;
import nl.altindag.log.LogCaptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ConsoleManagerTest {

    @Rule
    public final TextFromStandardInputStream systemInMock
        = emptyStandardInputStream();
    private ConsoleManager consoleManager;

    @Before
    public void setUp() {
        consoleManager = new ConsoleManager();
    }

    @Test
    public void testPrint() {
        LogCaptor logCaptor = LogCaptor.forClass(ConsoleManager.class);

        consoleManager.print("test");

        assertThat(logCaptor.getWarnLogs())
            .contains("test");

        logCaptor.close();
    }

    @Test
    public void testInput() {
        systemInMock.provideLines("test");
        assertEquals("test", consoleManager.input());
    }

    @Test
    public void testInputInt() {
        systemInMock.provideLines("123");
        assertEquals(123, consoleManager.inputInt("Введите число: "));
    }

    @Test
    public void testInputInt_Exception() {
        systemInMock.provideLines("not-a-number");
        assertEquals(-1, consoleManager.inputInt("Введите число: "));
    }

    @After
    public void tearDown() {
        consoleManager.close();
    }
}
