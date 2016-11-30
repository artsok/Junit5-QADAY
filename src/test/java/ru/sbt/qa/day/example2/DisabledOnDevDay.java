package ru.sbt.qa.day.example2;


import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(DisabledOnDevDayCondition.class)
@interface DisabledOnDevDay {}
