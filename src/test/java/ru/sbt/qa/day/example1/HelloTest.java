package ru.sbt.qa.day.example1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by User on 07.05.2017.
 */
@RunWith(value = Parameterized.class)
public class HelloTest {

    //Параметры и конструктор
        private int numberA;
        public HelloTest(int numberA) {
            this.numberA = numberA;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{{1}});
        }

        @Test
        public void compareTest() {
            assertTrue(numberA > 0);
            System.out.println(numberA);
        }
}
