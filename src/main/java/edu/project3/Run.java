package edu.project3;

import edu.project3.src.Manager;

@SuppressWarnings({"HideUtilityClassConstructor", "UncommentedMain", "RegexpSinglelineJava", "EmptyLineSeparator"})
public class Run {
    public static void main(String[] args) {
        var manager = new Manager(args);

        var result = manager.getStats();

        System.out.println("Successful generated stats into file - " + result.getAbsolutePath());
    }
}
