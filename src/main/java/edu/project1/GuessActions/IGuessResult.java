package edu.project1.GuessActions;

import org.jetbrains.annotations.NotNull;

sealed public interface IGuessResult permits Defeat, FailedGuess, GiveUpGuess, SameGuess, StartedSessionGuess, SuccessfulGuess, Win {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

}
