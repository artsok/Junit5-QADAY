package ru.sbt.qa.day.depends;

import org.junit.jupiter.api.DisplayName;

/**
 * Created by Sokovets-AV
 */
class DepTest {

    @DisplayName("Метод 2")
    @DependsOnMethod(dependsOn = {"method1"})
    void method2() {
        System.out.println("This is method 2");
    }

    @DisplayName("Метод 1")
    @DependsOnMethod()
    void method1() {
        System.out.println("This is method 1");
    }

}
