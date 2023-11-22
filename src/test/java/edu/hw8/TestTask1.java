package edu.hw8;

import edu.hw8.Task1.Database;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {
    @Nested
    class TestDatabase{
        private final Database db = new Database();
        @Test
        void testGetOneQuote(){
            String category = "глупый";

            String quote = db.getQuote(category);

            assertThat(db.getAllDatabase().get(category)).contains(quote);
        }
        @Test
        void testGetAllQuotesByCategory(){
            String category = "глупый";

            List<String> quotes = db.getQuotes(category);

            assertThat(db.getAllDatabase().get(category)).isEqualTo(quotes);
        }
    }
}
