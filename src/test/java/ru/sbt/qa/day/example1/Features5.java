package ru.sbt.qa.day.example1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
public class Features5 {

    @Test
    void testException() {
        assertThrows(RuntimeException.class, this::throwException);
        Throwable exception = expectThrows(RuntimeException.class, this::throwException);
        assertEquals("DEVDAY СБТ-today", exception.getMessage(), "Ошибочка");
    }

    private void throwException() {
        throw new RuntimeException("QADAY СБТ");
    }
}
