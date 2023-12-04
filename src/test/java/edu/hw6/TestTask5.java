package edu.hw6;

import edu.hw6.Task5.Requester;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task5.Parser.getNewsTitle;
import static edu.hw6.Task5.Parser.parseAllNews;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {

    @Test
    void testResponseIsValid() {
        Requester rq = new Requester();

        List<Long> response = rq.hackerNewsTopStories();

        assertThat(response).isNotEmpty();
        assertThat(response).isNotNull();
    }

    @Test
    void getNewsTitleUtilTest() {
        Optional<String> title = getNewsTitle(
            "{\"by\":\"mfiguiere\",\"descendants\":327,\"id\":37570037,\"kids\":[37571340,37570526,37570764,37572319,37570601,37570723,37570473,37570974,37570942,37570499,37570993,37571050,37571247,37570385,37571268,37570771,37570699,37572964,37570459,37571905,37571462,37607483,37571028,37570905,37570766,37571188,37570620],\"score\":246,\"time\":1695132006,\"title\":\"JDK 21 Release Notes\",\"type\":\"story\",\"url\":\"https://jdk.java.net/21/release-notes\"}");

        assertThat(title.isEmpty()).isFalse();
        assertThat(title.get()).isEqualTo("JDK 21 Release Notes");
    }

    @Test
    void parseAllNewsUtilTest() {
        List<Long> news = parseAllNews("[38252566,38254866,38251957,38255004]");

        assertThat(news).isEqualTo(List.of(
            38252566L,
            38254866L,
            38251957L,
            38255004L
        ));
    }

    @Test
    void testGetCurrentNews() {
        Requester rq = new Requester();
        Optional<String> title = rq.news("38252566");

        assertThat(title).isEqualTo(
            Optional.of("Building an occupancy sensor with a $5 ESP32 and a serverless DB")
        );
    }

    @Test
    void testGetCurrentNewsEmpty() {
        Requester rq = new Requester();
        Optional<String> title = rq.news("foobar");

        assertThat(title).isEqualTo(
            Optional.empty()
        );
    }
}
