package ru.sbt.qa.day.example1;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;


/**
 * Created by sbt-sokovets-av
 */
@Slf4j
class Features_7_Test {

    @Test
    void testTestInfo(TestInfo testInfo) {
        log.info("Название теста " + testInfo.getDisplayName());
    }

    @Test
    void testTestReporter(TestReporter testReporter) {
        testReporter.publishEntry("SQADAYS", "конфа 100%");
    }

}
