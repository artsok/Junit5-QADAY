package ru.sbt.qa.day.example11;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 * Created by sbt-sokovets-av
 */
class TimeOutTest {

    @Test
    void testWithTimeout() {
        assertTimeout(ofSeconds(5), () ->
            TimeUnit.SECONDS.sleep(10));
    }

    @Test
    void testWithTimeout2() {
        assertTimeoutPreemptively(ofMillis(1),
                () -> {
                    Thread.sleep(1000);
                    return "Execute this in new thread";
                });
    }
}
