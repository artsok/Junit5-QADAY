package ru.sbt.qa.day.example1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
public class Features2 {
    @Test
    @Disabled("Тест не будет запущен")
    void ourSecondTest() {
        assertTrue(false, "QADAY СБТ");
    }
}
