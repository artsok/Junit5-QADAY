package ru.sbt.qa.day.example1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

/**
 * Created by sbt-sokovets-av
 */
@Slf4j
class Features_1_Test {

    @Test
    void test() {
        log.info("Run test");
        Assertions.assertTrue(true, () -> "Junit 5");
    }
}
