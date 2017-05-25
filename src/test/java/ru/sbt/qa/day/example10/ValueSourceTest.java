package ru.sbt.qa.day.example10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sbt-sokovets-av
 * Принимают только один параметр в метод.
 * {@link ValueSource} может содержать примитивные типы (int, long, double) и String
 */

@Slf4j
class ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSourceInt(int argument) {
        log.info("Полученный аргумент '{}'", argument);
        assertTrue(argument > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"SQADays", "SberDays"})
    void testWithValueSourceString(String argument) {
        log.info("Полученный аргумент '{}'", argument);
        assertNotNull(argument);
    }
}