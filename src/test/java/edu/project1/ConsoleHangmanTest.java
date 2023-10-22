package edu.project1;

import edu.project1.GuessActions.GiveUpGuess;
import edu.project1.GuessActions.IGuessResult;
import edu.project1.Utils.ConsoleManager;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleHangmanTest {
    @Test
    public void testTryGuessOver1Char() {
        Session session = new Session();
        ConsoleManager cm = new ConsoleManager();

        ConsoleHangman consoleHangman = new ConsoleHangman();

        IGuessResult result = ConsoleHangman.tryGuess(session, "ABC", cm);

        assertThat(result).isNull();
    }

    @Test
    public void testTryGuessOver_GiveUp() {
        Session session = new Session();
        ConsoleManager cm = new ConsoleManager();

        ConsoleHangman consoleHangman = new ConsoleHangman();

        IGuessResult result = ConsoleHangman.tryGuess(session, "пас", cm);

        assertThat(result).isInstanceOf(GiveUpGuess.class);
    }
}
