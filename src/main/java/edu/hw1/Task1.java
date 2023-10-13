package edu.hw1;

import edu.hw1.Exceptions.InvalidFormatTimeException;
import edu.hw1.Exceptions.InvalidSecondFormatException;

public class Task1 {
    public Task1() {
    }

    public static int minutesToSeconds(String time) throws InvalidFormatTimeException, InvalidSecondFormatException {
        String[] times = time.split(":");
        if (times.length != 2) {
            throw new InvalidFormatTimeException("Строка должна быть в формате \"HH:MM\"");
        }
        try {
            int minutes = Integer.parseInt(times[0]);
            int seconds = Integer.parseInt(times[1]);

            if (seconds > 59) {
                throw new InvalidSecondFormatException("Диапазон секунд [0:59]!");
            }
            return minutes * 60 + seconds;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Не удалось сконвертировать ваши данные в числа :(");
        }
    }
}
