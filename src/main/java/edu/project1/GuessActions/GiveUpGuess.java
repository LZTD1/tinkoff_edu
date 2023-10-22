package edu.project1.GuessActions;

import org.jetbrains.annotations.NotNull;

public record GiveUpGuess(char[] state, int attempt, int maxAttempts, @NotNull String message)
    implements IGuessResult {
}
