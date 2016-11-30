package ru.sbt.qa.day.example3;

import org.junit.jupiter.api.Test;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
public class RunTest {

    @Test
    @PerfomanceBenchmarks
    void testExample1() {
        System.out.println("Run 1");
    }

    @Test
    void testExample2() {
        System.out.println("Run 2");
    }

    @Test
    @PerfomanceBenchmarks
    void testExample3() {
        System.out.println("Run 3");
    }

}
