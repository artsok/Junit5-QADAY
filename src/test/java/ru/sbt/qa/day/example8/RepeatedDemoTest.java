package ru.sbt.qa.day.example8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * Created by sbt-sokovets-av
 * Демонстрация {@link RepeatedTest}
 * Данная аннотация вешается на метод, чтобы повторить тестовый метод столько раз, сколько нам необходимо.
 * Полное описание: http://junit.org/junit5/docs/current/user-guide/#writing-tests-repeated-tests
 */
@Slf4j
class RepeatedDemoTest {

    private static AtomicInteger ai = new AtomicInteger(0);


    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getTestMethod().get().getName();
        log.info(String.format("About to execute repetition %d of %d for %s",
              currentRepetition, totalRepetitions, methodName));
    }

    @RepeatedTest(10)
    void repeatedTest() {
        log.info("Запуск теста {}",ai.incrementAndGet());
        //Проверочки
    }

    @RepeatedTest(5)
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        //Кол-во повторений сравнивается с ожидаемым результатом.
        //Кол-во запусков можно получить через параметр RepetitionInfo
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    /**
     * Кастомизация отображаемого имени
     * @param testInfo
     */
    @RepeatedTest(value = 5, name = "Название конкретного случая:  Запуск {currentRepetition} из {totalRepetitions}")
    @DisplayName("Название тестового метода")
    void customDisplayName(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        log.info("Повторение № {}", repetitionInfo.getCurrentRepetition());
        assertEquals(testInfo.getDisplayName(),
                "Название конкретного случая:  Запуск " + repetitionInfo.getCurrentRepetition()
                + " из " + repetitionInfo.getTotalRepetitions());
    }


    /**
     * Демонстрация констант в {@link RepeatedTest}
     * @param testInfo
     */
    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals(testInfo.getDisplayName(), "Details... :: repetition 1 of 1");
    }

}