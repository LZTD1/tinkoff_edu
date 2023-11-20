package edu.project3.src.Exceptions;

public class FileNotExists extends RuntimeException {
    public FileNotExists(String s) {
        super(s);
    }
}
