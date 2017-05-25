package ru.sbt.qa.day.video;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by sbt-sokovets-av
 */
public class VideoTest {

    private WebDriver driver;

    @BeforeEach
    void init() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resource/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Video(path = "C://tmp")
    void openYandexPage() throws InterruptedException {
        driver.get("http://www.yandex.ru");
        TimeUnit.SECONDS.sleep(10);
    }



    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}
