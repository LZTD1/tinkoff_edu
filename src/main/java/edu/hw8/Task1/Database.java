package edu.hw8.Task1;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Database {

    private final HashMap<String, List<String>> quotes;

    public Database() {
        this.quotes = new HashMap<>() {{
            put("личности", List.of(
                "Не переходи на личности там, где их нет"
            ));
            put("оскорбления", List.of(
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
            ));
            put("глупый", List.of(
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
            ));
            put("интеллект", List.of(
                "Чем ниже интеллект, тем громче оскорбления"
            ));
        }};
    }

    public List<String> getQuotes(String category) {
        String myCategory = category.trim().toLowerCase();
        return this.quotes.get(myCategory);
    }

    public String getQuote(String category) {
        String myCategory = category.trim().toLowerCase();
        var listCategory = this.quotes.get(myCategory);
        return listCategory.get(ThreadLocalRandom.current().nextInt(listCategory.size()));
    }

    public HashMap<String, List<String>> getAllDatabase() {
        return this.quotes;
    }
}
