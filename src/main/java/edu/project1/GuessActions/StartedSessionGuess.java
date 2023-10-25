package edu.project1.GuessActions;

import org.jetbrains.annotations.NotNull;

public record StartedSessionGuess(char[] state, int attempt, int maxAttempts, @NotNull String message)
    implements IGuessResult {
}
