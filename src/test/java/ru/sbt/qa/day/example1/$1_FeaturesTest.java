package ru.sbt.qa.day.example1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
@Slf4j
public class $1_FeaturesTest {

    @Test
    void test() {
        log.info("Run test");
        assertTrue(true, "Junit 5 - Like from me!");
    }
}
