package ru.sbt.qa.day.rerunner;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sbt-sokovets-av
 */
@Slf4j
class ReRunnerTest {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @RepeatedIfExceptionsTest(repeats = 3)
    void reRunTest() {
        log.debug("Запущен тестовый метод 'reRunTest()', класса '{1}'", this.getClass());
        if(random.nextInt() % 2 == 0) { //Исключение бросается рандомно
            throw new RuntimeException("Ошибочка в методе reRunTest");
        }
    }

    @RepeatedIfExceptionsTest(repeats = 5, exceptions = IOException.class)
    void reRunTest2() throws IOException {
        log.debug("Запущен тестовый метод 'reRunTest2()', класса '{1}'", this.getClass());
        throw new IOException("Проблема с записью на диск");
    }

    @RepeatedIfExceptionsTest(repeats = 5, exceptions = IOException.class,
            name = "Перезапуск теста. Попытка {currentRepetition} из {totalRepetitions}")
    void reRunTest3() throws IOException {
        log.debug("Запущен тестовый метод 'reRunTest2()', класса '{1}'", this.getClass());
        throw new IOException("Проблема с записью на диск");
    }
}
