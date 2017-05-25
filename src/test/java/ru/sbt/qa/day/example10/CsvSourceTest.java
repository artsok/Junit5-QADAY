package ru.sbt.qa.day.example10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Created by sbt-sokovets-av
 * Генератор тестовых данных через параметр аннотации {@link CsvSource}. Значения разделенные запятой.
 * При условии значений с пробелом, необходимо представить, как 'value value'.
 */
@Slf4j
class CsvSourceTest {

    @ParameterizedTest
    @CsvSource({ "SberDays, 1", "SQADays, 2", "'JUG.RU, Joker', 3" })
    void testCsvSource(String conference, int position) {
        log.info("Название конференции '{}', позиция '{}'", conference, position);
        assertNotNull(conference);
        assertNotEquals(0, position);
    }
}
