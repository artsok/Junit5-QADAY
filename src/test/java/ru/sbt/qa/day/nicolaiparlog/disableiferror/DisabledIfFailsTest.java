package ru.sbt.qa.day.nicolaiparlog.disableiferror;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Tag("DisabledIfTestFailed")
@DisabledIfTestFailedWith(RuntimeException.class) /*Указываем какой класс исключений мы обрабатываем,
т.е если входе выполнения теста будет выброшен  RuntimeException, то выполнения всех остальных тестов
в данном классе прекратится.*/
class DisabledIfFailsTest {

    private static boolean ONE_TEST_FAILED = false;

    @Test
    void assertNoTestFailed_thenFail_1() {
        log.info("Run - 1");
        assertTrue(true, "Проверочка");
    }

    @Test
    void assertNoTestFailed_thenFail_2() throws IOException {
        log.info("Run - 2");
        throw new RuntimeException("I am kill u"); //Бросаем UncheckedException
    }

    @Test
    void assertNoTestFailed_thenFail_3() {
        log.info("Run - 3");
        assertThenFail();
    }

    @Test
    void assertNoTestFailed_thenFail_4() {
        log.info("Run - 4");
        assertThenFail();
    }

    private void assertThenFail() {
        assertFalse(ONE_TEST_FAILED, "No test should run after another failed!");
        ONE_TEST_FAILED = true;
    }
}