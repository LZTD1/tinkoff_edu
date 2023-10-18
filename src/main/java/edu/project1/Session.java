package edu.project1;

import edu.project1.GuessActions.Defeat;
import edu.project1.GuessActions.FailedGuess;
import edu.project1.GuessActions.GiveUpGuess;
import edu.project1.GuessActions.IGuessResult;
import edu.project1.GuessActions.SameGuess;
import edu.project1.GuessActions.StartedSessionGuess;
import edu.project1.GuessActions.SuccessfulGuess;
import edu.project1.GuessActions.Win;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import static edu.project1.Session.ReturnStatement.DEFEAT;
import static edu.project1.Session.ReturnStatement.FAILED_GUESS;
import static edu.project1.Session.ReturnStatement.SAME_GUESS;
import static edu.project1.Session.ReturnStatement.SUCCESSFUL_GUESS;
import static edu.project1.Session.ReturnStatement.WIN;

class Session {
    private final static int MAX_ATTEMPTS = 5;
    private final Dictionary dictionary;
    private int maxAttempts = MAX_ATTEMPTS;
    private char[] answer;
    private char[] hiddenAnswer;
    private int attempts;
    private UserState currentUserState;
    private ReturnStatement currentReturn;

    Session() {
        this.dictionary = new Dictionary();
        this.answer = this.dictionary.getRandomWord().toCharArray();
        this.hiddenAnswer = "*".repeat(this.answer.length).toCharArray();
        this.attempts = 0;
        this.currentUserState = UserState.USER_IN_MENU;
    }

    Session(int maxAttempts) {
        this.dictionary = new Dictionary();
        this.answer = this.dictionary.getRandomWord().toCharArray();
        this.hiddenAnswer = "*".repeat(this.answer.length).toCharArray();
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.currentUserState = UserState.USER_IN_MENU;
    }

    UserState getUserState() {
        return this.currentUserState;
    }

    void changeUserState(UserState state) {
        this.currentUserState = state;
    }

    void regenerateAnswer() {
        this.answer = new Dictionary().getRandomWord().toCharArray();
        this.hiddenAnswer = "*".repeat(this.answer.length).toCharArray();
        this.attempts = 0;
    }

    @NotNull IGuessResult guess(char guess) {
        boolean flagFinded = false;

        for (int i = 0; i < this.answer.length; i++) {
            if (this.answer[i] == guess) {
                if (this.hiddenAnswer[i] != guess) { // Если уже угадывали букву такую
                    this.hiddenAnswer[i] = guess;
                    flagFinded = true;
                } else {
                    this.currentReturn = SAME_GUESS;
                }
            }
        }
        if (flagFinded) {
            if (Arrays.equals(this.answer, this.hiddenAnswer)) {
                this.currentReturn = WIN;
            } else {
                this.currentReturn = SUCCESSFUL_GUESS;
            }
        } else {
            if (this.currentReturn == null) {
                this.attempts += 1;
                if (this.attempts == this.maxAttempts) {
                    this.currentReturn = DEFEAT;
                } else {
                    this.currentReturn = FAILED_GUESS;
                }
            }

        }

        var variableReturn = switch (this.currentReturn) {
            case GIVE_UP_GUESS -> null;
            case STARTED_SESSION_GUESS -> null;
            case FAILED_GUESS ->
                new FailedGuess(this.hiddenAnswer, this.attempts, this.maxAttempts, "Такой буквы нет в слове :|");
            case DEFEAT ->
                new Defeat(this.hiddenAnswer, this.attempts, this.maxAttempts, "Вы проиграли, попытки закончились :(");
            case WIN -> new Win(this.hiddenAnswer, this.attempts, this.maxAttempts, "Вы выиграли :)");
            case SUCCESSFUL_GUESS -> new SuccessfulGuess(
                this.hiddenAnswer,
                this.attempts,
                this.maxAttempts,
                "Круто! Такая буква есть в слове."
            );
            case SAME_GUESS -> new SameGuess(
                this.hiddenAnswer,
                this.attempts,
                this.maxAttempts,
                "Такая буква уже была открыта :/"
            );
        };
        this.currentReturn = null;
        return variableReturn;
    }

    public boolean addNewWordIntoDict(String word) {
        return this.dictionary.addOwnWord(word);
    }

    public void deleteWordIntoDict(String word) {
        this.dictionary.deleteWord(word);
    }

    public int getCountWordsInDict() {
        return this.dictionary.getCountWords();
    }

    public IGuessResult getStarted() {
        return new StartedSessionGuess(
            this.hiddenAnswer,
            this.attempts,
            this.maxAttempts,
            "Пиши букву, которая по твоему мнению, находится в слове!\nЧто-бы сдаться, напиши \"пас\""
        );
    }

    @NotNull IGuessResult giveUp() {
        return new GiveUpGuess(
            this.answer,
            this.attempts,
            this.maxAttempts,
            "Ты закончил игру, результаты ниже."
        );
    }

    public enum UserState {
        USER_IN_MENU,
        USER_IN_DICTIONARY_MENU,
        USER_IN_GAME,
        USER_JUST_STARTED_GAME,
        USER_ADDING_WORD,
        USER_DELETING_WORD,
    }

    enum ReturnStatement {
        GIVE_UP_GUESS,
        STARTED_SESSION_GUESS,
        FAILED_GUESS,
        DEFEAT,
        WIN,
        SUCCESSFUL_GUESS,
        SAME_GUESS,
    }
}
