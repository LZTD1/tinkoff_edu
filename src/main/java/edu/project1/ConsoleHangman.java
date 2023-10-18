package edu.project1;

import edu.project1.GuessActions.Defeat;
import edu.project1.GuessActions.IGuessResult;
import edu.project1.GuessActions.Win;
import edu.project1.Utils.ConsoleManager;
import static edu.project1.Utils.ConcatenateArrayWithQuestionMarks.concatenateArrayWithQuestionMarks;

class ConsoleHangman {
    public static void main(String[] args) {
        ConsoleHangman ch = new ConsoleHangman();
        ch.run();
    }

    public void run() {
        var cm = new ConsoleManager();
        Session myOwnSession = new Session();

        do {
            if (myOwnSession.getUserState() == Session.userState.USER_IN_MENU) {
                int action =
                    cm.inputInt("\nПривет! Желаешь поиграть в виселицу?\n1 - Да\n2 - Меню словаря\n3 - Выход из игры");
                otherInput(action, myOwnSession);
            } else if (myOwnSession.getUserState() == Session.userState.USER_IN_GAME ||
                myOwnSession.getUserState() == Session.userState.USER_JUST_STARTED_GAME) {
                String myChar;
                if (myOwnSession.getUserState() ==
                    Session.userState.USER_JUST_STARTED_GAME) { // Вся эта заварушка нужна что бы коллбек слать тогда когда надо
                    myChar = cm.input(() -> printState(
                        myOwnSession.getStarted(),
                        cm
                    )); //А коллбек нужен для того, что бы в первый запуск игры, у нас выводилась статистика, тобишь IGuessResult
                    myOwnSession.changeUserState(Session.userState.USER_IN_GAME);
                } else {
                    myChar = cm.input();
                }
                IGuessResult guessResult = tryGuess(myOwnSession, myChar, cm);
                if (guessResult instanceof Win || guessResult instanceof Defeat) {
                    printState(guessResult, cm);
                    myOwnSession.regenerateAnswer();
                    myOwnSession.changeUserState(Session.userState.USER_IN_MENU);
                } else if (guessResult == null) {
                    if(myOwnSession.getUserState()== Session.userState.USER_IN_GAME){
                        cm.print("Напишите только 1 букву!");
                    }
                } else {
                    printState(guessResult, cm);
                }
            }else if(myOwnSession.getUserState() == Session.userState.USER_IN_DICTIONARY_MENU){
                int action = cm.inputInt("В словаре сейчас: "+myOwnSession.getCountWordsInDict()+" слов\n1 - Добавить слово\n2-Удалить слово\n3-Выход в меню");
                otherInput(action, myOwnSession);
            }else if(myOwnSession.getUserState() == Session.userState.USER_ADDING_WORD){
                String action = cm.input("Напишите слово, которое собираетесь добавить\nДля выхода напишите 1");
                if(otherInput(action, myOwnSession)){
                    cm.print("Успешно добавлено!");
                }else{
                    cm.print("Не удалось добавить.");
                }
            }else if(myOwnSession.getUserState() == Session.userState.USER_DELETING_WORD){
                String action = cm.input("Напишите слово, которое собираетесь удалить\nДля выхода напишите 1");
                otherInput(action, myOwnSession);
                cm.print("Успешно удалено!");
            }
        } while (true);
    }
    private boolean otherInput(String action, Session session) {
        if(action.equals("1")){
            session.changeUserState(Session.userState.USER_IN_DICTIONARY_MENU);
        }
        else if (session.getUserState() == Session.userState.USER_ADDING_WORD) {
            return session.addNewWordIntoDict(action);
        }
        else if (session.getUserState() == Session.userState.USER_DELETING_WORD) {
            session.deleteWordIntoDict(action);
            return true;
        }
        return false;
    }
    private void otherInput(int action, Session session) {
        if (session.getUserState() == Session.userState.USER_IN_MENU) {
            if (action == 1) {
                session.changeUserState(Session.userState.USER_JUST_STARTED_GAME);
            }
            else if (action == 2) {
                session.changeUserState(Session.userState.USER_IN_DICTIONARY_MENU);
            }
            else if (action == 3) {
                System.exit(200);
            }
            else if(action == 0){
                System.exit(200);
            }
        }else if (session.getUserState() == Session.userState.USER_IN_DICTIONARY_MENU){
            if (action == 1) {
                session.changeUserState(Session.userState.USER_ADDING_WORD);
            }
            if (action == 2) {
                session.changeUserState(Session.userState.USER_DELETING_WORD);
            }
            if (action == 3) {
                session.changeUserState(Session.userState.USER_IN_MENU);
            }
        }else if(session.getUserState() == Session.userState.USER_ADDING_WORD || session.getUserState() == Session.userState.USER_DELETING_WORD ) {
            if (action == 1) {
                session.changeUserState(Session.userState.USER_IN_DICTIONARY_MENU);
            }
        }
    }

    private IGuessResult tryGuess(Session session, String input, ConsoleManager cm) {
        if(input != null) {
            char[] charArray = input.toCharArray();
            if (charArray.length > 1) {
                if (input.equals("пас")) {
                    session.changeUserState(Session.userState.USER_IN_MENU);

                    IGuessResult giveUp = session.giveUp();
                    session.regenerateAnswer();
                    return giveUp;
                }
                return null;
            }
            return session.guess(charArray[0]);
        }else{
            session.changeUserState(Session.userState.USER_IN_MENU);
            return null;
        }
    }

    private void printState(IGuessResult guess, ConsoleManager cm) {
        cm.print(guess.message());
        cm.print("Попыток: " + guess.attempt() + " из " + guess.maxAttempts());
        cm.print(concatenateArrayWithQuestionMarks(guess.state()));
    }
}
