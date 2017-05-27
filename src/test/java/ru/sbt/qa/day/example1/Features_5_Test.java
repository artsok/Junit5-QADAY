package ru.sbt.qa.day.example1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sbt-sokovets-av
 */
class Features_5_Test {

    private void throwException() {
        throw new RuntimeException("QADAY СБТ");
    }

    @Test
    @DisplayName("Проверка исключений")
    void testException() {
        assertThrows(RuntimeException.class, this::throwException);
        Throwable exception = assertThrows(RuntimeException.class, this::throwException);
        assertEquals("DEVDAY СБТ", exception.getMessage(), "Ошибочка");
    }

    @Test
    void expectingArithmeticException() {
        assertThrows(ArithmeticException.class,() -> {int a = 1/0; return;});
    }



}
