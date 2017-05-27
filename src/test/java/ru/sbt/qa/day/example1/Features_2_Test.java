package ru.sbt.qa.day.example1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sbt-sokovets-av
 */
class Features_2_Test {
    @Test
    @Disabled("Тест не будет запущен")
    void ourSecondTest() {
        assertTrue(false, "QADAY");
    }
}
