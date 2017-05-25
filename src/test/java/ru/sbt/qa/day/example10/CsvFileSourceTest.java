package ru.sbt.qa.day.example10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by sbt-sokovets-av
 * Генератор данных посредством считывания их из csv файла (CSV файл = значения разделены запятыми).
 * Файл csvData.csv находится в каталоге /resource/csv/;
 * Если значения с пробелом, то они представляются, как "value, value"
 */
@Slf4j
class CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/csvData.csv")
    void testWithCsvFileSource(String name, String lastName, String percent) {
        log.info("Данные из файла. Имя - '{}', Фамилия - '{}', Процент участия'{}'",
                name, lastName, percent);
        assertAll(
                () -> assertNotNull(name),
                () -> assertNotNull(lastName),
                () -> assertNotNull(percent));
    }
}
