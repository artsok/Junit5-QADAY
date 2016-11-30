package ru.sbt.qa.day.example1;

import org.junit.jupiter.api.*;

import static java.lang.System.out;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
public class Features6 {

    private static int a;

    @BeforeAll
    static void beforeAll() {
        out.println("Выполнить перед всеми тестами");
        a = 10;
    }

    @BeforeEach
    void beforeEach() {
        out.println("Выполнить перед каждым тестом");
    }

    @Test
    void testExample1() {
        out.println("Run - 1");
    }

    @Test
    void testExample2() {
        out.println("Run -2");
    }

    @AfterEach
    void afterEach() {
        out.println("Выполнить после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        out.println("Выполнить после всех тестов");
    }
}
