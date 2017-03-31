package ru.sbt.qa.day.example4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
@DisplayName("СББОЛ TK API - Отправка писем в банк")
public class BDDStyleTest {

    @Test
    @DisplayName("Дано: Пользователь проходит авторизацию в системе")
    void login() { /*...*/ }


        @Nested
        @DisplayName("Когда:")
        class Send {

            @Test
            @DisplayName("Пользователь отправляет сообщение")
            void sendMess() { /*...*/ }

            @Nested
            @DisplayName("Тогда система присылает ответ")
            class AfterSending {

                @Test
                @DisplayName("Проверяем, что идентификатор уникальный")
                void checkTicket() { /*...*/ }

                @Test
                @DisplayName("Проверяем, что идентификатор заполнен корректно")
                void checkTicketField() { /*...*/ }

                @Test
                @DisplayName("Пользователь сохраняет тикет")
                void saveTicket() { /*...*/ }

                @Nested
                @DisplayName("Когда:")
                class Status {

                    @Test
                    @DisplayName("пользователь запрашивает статус по тикету")
                    void getTicketID() { /*...*/ }
                }
            }
        }

}
