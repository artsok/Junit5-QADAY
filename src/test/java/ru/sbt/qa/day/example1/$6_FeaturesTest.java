package ru.sbt.qa.day.example1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

/**
 * Created by sbt-sokovets-av
 */
@Slf4j
public class $6_FeaturesTest {

    @BeforeAll
    static void beforeAll() {
        log.info("Выполнить перед всеми тестами");
    }

    @BeforeEach
    void beforeEach() {
        log.info("Выполнить перед каждым тестом");
    }

    @Test
    @DisplayName("ПРОВЕРКА: жизненный цикл")
    void testExample1() {
        log.info("Run - 1");
    }

    @Test
    @DisplayName("ПРОВЕРКА: жизненный цикл ")
    void testExample2() {
        log.info("Run - 2");
    }

    @AfterEach
    void afterEach() {
        log.info("Выполнить после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        log.info("Выполнить после всех тестов");
    }
}
