package edu.hw1;

import edu.hw1.Exceptions.InvalidFormatTimeException;
import edu.hw1.Exceptions.InvalidSecondFormatException;

public class Task1 {
    private Task1() {
    }
    private static final int SECONDSPERMINUTE = 60;

    public static int minutesToSeconds(String time) throws InvalidFormatTimeException, InvalidSecondFormatException {
        String[] times = time.split(":");
        if (times.length != 2) {
            throw new InvalidFormatTimeException("Строка должна быть в формате \"HH:MM\"");
        }
        try {
            int minutes = Integer.parseInt(times[0]);
            int seconds = Integer.parseInt(times[1]);

            if (seconds > SECONDSPERMINUTE - 1) {
                throw new InvalidSecondFormatException("Диапазон секунд [0:59]!");
            }
            return minutes * SECONDSPERMINUTE + seconds;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Не удалось сконвертировать ваши данные в числа :(");
        }
    }
}
