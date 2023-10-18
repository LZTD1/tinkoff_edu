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

class Session {
    private final int maxAttempts;
    private final Dictionary dictionary;
    private char[] answer;
    private char[] hiddenAnswer;
    private int attempts;
    private userState currentUserState;

    public Session() {
        this.dictionary = new Dictionary();
        this.answer = this.dictionary.getRandomWord().toCharArray();
        this.hiddenAnswer = "*".repeat(this.answer.length).toCharArray();
        this.maxAttempts = 5;
        this.attempts = 0;
        this.currentUserState = userState.USER_IN_MENU;
    }

    public Session(int maxAttempts) {
        this.dictionary = new Dictionary();
        this.answer = this.dictionary.getRandomWord().toCharArray();
        this.hiddenAnswer = "*".repeat(this.answer.length).toCharArray();
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.currentUserState = userState.USER_IN_MENU;
    }

    userState getUserState() {
        return this.currentUserState;
    }

    void changeUserState(userState state) {
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
                    return new SameGuess(
                        this.hiddenAnswer,
                        this.attempts,
                        this.maxAttempts,
                        "Такая буква уже была открыта :/"
                    );
                }
            }
        }
        if (flagFinded) {
            if (Arrays.equals(this.answer, this.hiddenAnswer)) {
                return new Win(this.hiddenAnswer, this.attempts, this.maxAttempts, "Вы выиграли :)");
            }
            return new SuccessfulGuess(
                this.hiddenAnswer,
                this.attempts,
                this.maxAttempts,
                "Круто! Такая буква есть в слове."
            );
        }
        this.attempts += 1;
        if (this.attempts == this.maxAttempts) {
            return new Defeat(
                this.hiddenAnswer,
                this.attempts,
                this.maxAttempts,
                "Вы проиграли, попытки закончились :("
            );
        } else {
            return new FailedGuess(this.hiddenAnswer, this.attempts, this.maxAttempts, "Такой буквы нет в слове :|");
        }

    }
    public boolean addNewWordIntoDict(String word){
        return this.dictionary.addOwnWord(word);
    }
    public void deleteWordIntoDict(String word){
       this.dictionary.deleteWord(word);
    }
    public int getCountWordsInDict(){
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

    public enum userState {
        USER_IN_MENU,
        USER_IN_DICTIONARY_MENU,
        USER_IN_GAME,
        USER_JUST_STARTED_GAME,
        USER_ADDING_WORD,
        USER_DELETING_WORD,

    }
}
