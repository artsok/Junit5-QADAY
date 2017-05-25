package ru.sbt.qa.day.example1;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by sbt-sokovets-av
 */
public class Features_4_Test {

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 1),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 1)
        );
    }
}
