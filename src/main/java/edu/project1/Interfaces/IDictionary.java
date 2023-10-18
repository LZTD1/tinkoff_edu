package edu.project1.Interfaces;

import org.jetbrains.annotations.NotNull;

public interface IDictionary {
    @NotNull String getRandomWord();

    boolean addOwnWord(String newWord);

    void deleteWord(String toDeleteWord);
    int getCountWords();
}
