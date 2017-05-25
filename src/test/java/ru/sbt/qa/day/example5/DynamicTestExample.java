package ru.sbt.qa.day.example5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by sbt-sokovets-av
 */
class DynamicTestExample {

    @DisplayName("Проверка отправки сообщений в mq очередь")
    @TestFactory
    Collection<DynamicTest> testDynamicTest() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Тест №1",
                        () -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("Тест №2",
                        () -> Assertions.assertFalse(false)));
    }
}
