package ru.sbt.qa.day.video;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created by sbt-sokovets-av
 */
@Slf4j
public class VideoCondition implements BeforeEachCallback, AfterEachCallback {

    private VideoRecorder vr;


    @Override
    public void beforeEach(TestExtensionContext context) throws Exception {
        String path = context
                .getElement()
                .get()
                .getAnnotation(Video.class).path(); //Получаем значение переменной аннотации Video
        Files.createDirectories(Paths.get(path));
        vr = new VideoRecorder(path);
        vr.startRecording();
        log.info("Запись видео начата");
    }

    @Override
    public void afterEach(TestExtensionContext context) throws Exception {
        vr.stopRecording();
        log.info("Запись видео закончена.");
    }

    public void test() {
        //AnnotationUtils.findAnnotation()
    }
}
