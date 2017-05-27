package ru.sbt.qa.day.example1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by sbt-sokovets-av
 */
class Features_3_Test {

    @Test
    public void ourFourthTest() {
        assertEquals(true, false, () ->
                "Ленивая инициализация сообщения "
                        + "с ипользованием " + "" +
                        "λ.");
    }
}
