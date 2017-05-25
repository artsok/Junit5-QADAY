package ru.sbt.qa.day.example10;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.stream.Stream;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by sbt-sokovets-av
 *
 * Данные генерим с помощью дополнительный статический методов,
 * которые возвращают Stream, array[T], Iterable, Iterator.
 * Если у одного метода указать два провайдера, то они переберут все значение в каждом провайдере.
 * Когда у нас датапровайдер генерит два параметра, то мы должны использовать класс ObjectArrayArguments (см.пример ниже)
 */
@Slf4j
class MethodSourceTest {

    @ParameterizedTest
    @MethodSource(names = {"dataProvider", "dataProvider1"})
    void testMethodSource(String arg) {
        log.info("Полученный аргумент '{}'", arg);
        assertNotNull(arg);
    }


    @MethodSource(names = "MyDataAndIntProvider")
    @ParameterizedTest(name = "Номер теста: {index} ==> Первый параметр=''{0}'', Второй параметр=''{1}''")
    void testMethodSourceMultipleArguments(MyData data, int integer) {
        log.info("MyData, key = '{}', value = '{}'. int = {}", data.getKey(), data.getValue(), integer);
        assertAll(
                () -> assertNotNull(data.getKey()),
                () -> assertNotNull(data.getValue()),
                () -> assertTrue(integer > 0 ));
    }

    /**
     * Генератор данных (№1)
     * @return  {@link Stream<String>}
     */
    static Stream<String> dataProvider() {
        return of("S","Q", "A", "D", "A", "Y", "S");
    }

    /**
     * Генератор данных (№2)
     * @return  {@link Stream<String>}
     */
    static Stream<String> dataProvider1() {
        return of("S1","Q1", "A1", "D1", "A1", "Y1", "S1");
    }

    /**
     * Генератор данных (№3).
     * Производим значение {@link MyData} и {@link Integer}
     * @return
     */
    static Stream<Arguments> MyDataAndIntProvider() {
        return Stream.of(ObjectArrayArguments.create(new MyData(1, "Artem"), 10),
                ObjectArrayArguments.create(new MyData(2, "Sokovets"), 20));
    }
}

@AllArgsConstructor
@Getter
@ToString
class MyData {
    private int key;
    private String value;
}