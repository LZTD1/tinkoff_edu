package edu.hw6.Task3.AbstractFilters.FileMatchings;

import edu.hw6.Task3.AbstractFilter;
import java.io.FileInputStream;
import java.io.IOException;

public interface MagicFilter extends AbstractFilter {
    static AbstractFilter magicNumber(Object... numbers) {
        return entry -> {
            try (FileInputStream ins = new FileInputStream(entry.toFile())) {
                for (Object currentObj : numbers) {
                    int a = ins.read();
                    if ((currentObj instanceof Integer)) {
                        if ((int) currentObj != a) {
                            return false;
                        }
                    } else if ((currentObj instanceof Character)) {
                        int charValue = ((Character) currentObj).charValue();
                        if (charValue != a) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }
}
