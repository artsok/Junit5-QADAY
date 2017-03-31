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
public class DynamicTestExample {

    @DisplayName("Проверка отправки сообщений в mq очередь")
    @TestFactory
    public Collection<DynamicTest> testDynamicTest() {
        System.out.println("DynamicTest");
        return Arrays.asList(
                DynamicTest.dynamicTest("Тест №1",
                        () -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("Тест №2",
                        () -> Assertions.assertFalse(false)));
    }
//
//    createDynamicTestsReturnAsCollection() {
//        return Arrays.asList(
//                dynamicTest("A dynamic test",
//                        () -> assertTrue(true)),
//                dynamicTest("Another dynamic test",
//                        () -> assertEquals(6, 3 * 2))
}
