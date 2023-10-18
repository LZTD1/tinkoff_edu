package edu.project1.Utils;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleManager {

    private Logger logger;
    private Scanner reader;

    public ConsoleManager() {
        this.reader = new Scanner(System.in);
        this.logger = LogManager.getLogger();
    }

    public String input(String hint) {
        try {
            this.print(hint);
            return reader.next().toLowerCase();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String input(Callback callback) {
        try {
            callback.execute();
            return reader.next().toLowerCase();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String input() {
        try {
            return reader.next().toLowerCase();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int inputInt(String hint) {
        boolean flag = true;
        try {
            this.print(hint);
            int var = reader.nextInt();
            return var;
        } catch (InputMismatchException e) {
            this.print("Давай договоримся, здесь вводить можно только цифры :)");
            reader.nextLine();
        } catch (NoSuchElementException e) {
            this.print("Спасибо за игру!");
        }
        return 0;
    }

    public void print(String message) {
        this.logger.warn(message);
    }

    public void close() {
        this.reader.close();
        this.reader = null;
        this.logger = null;
    }

    @FunctionalInterface
    public interface Callback {
        void execute();
    }
}
