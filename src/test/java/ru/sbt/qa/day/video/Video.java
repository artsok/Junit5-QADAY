package ru.sbt.qa.day.video;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by sbt-sokovets-av
 */
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(VideoCondition.class)
@Test
public @interface Video {
    String path();
}
