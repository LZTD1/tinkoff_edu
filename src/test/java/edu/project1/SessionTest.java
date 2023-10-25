package edu.project1;

import edu.project1.GuessActions.Defeat;
import edu.project1.GuessActions.FailedGuess;
import edu.project1.GuessActions.SameGuess;
import edu.project1.GuessActions.SuccessfulGuess;
import edu.project1.GuessActions.Win;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    private final int MAX_ATTEMTS_DEFAULT = 5;
    private final char UNKNOWN_CHAR = '\'';

    private Session session;

    @BeforeEach
    void initialize() {
        this.session = new Session();
    }

    @Test
    void maxAttemptsGuessTest() {
        for (int i = 0; i < MAX_ATTEMTS_DEFAULT - 1; i++) {
            this.session.guess(UNKNOWN_CHAR);
        }
        var result = this.session.guess(UNKNOWN_CHAR);

        assertThat(result).isInstanceOf(Defeat.class);
    }

    @Test
    void SuccessfulGuessTest() {
        this.session = new Session("src/test/resources/dict_testGame.json");

        assertThat(this.session.guess('а')).isInstanceOf(SuccessfulGuess.class);
    }

    @Test
    void FailedGuessTest() {
        this.session = new Session("src/test/resources/dict_testGame.json");

        assertThat(this.session.guess('б')).isInstanceOf(FailedGuess.class);
    }

    @Test
    void SameGuessTest() {
        this.session = new Session("src/test/resources/dict_testGame.json");

        this.session.guess('а');
        assertThat(this.session.guess('а')).isInstanceOf(SameGuess.class);
    }

    @Test
    void WinTest() {
        this.session = new Session("src/test/resources/dict_testGame.json");

        this.session.guess('м');
        assertThat(this.session.guess('а')).isInstanceOf(Win.class);
    }
}
