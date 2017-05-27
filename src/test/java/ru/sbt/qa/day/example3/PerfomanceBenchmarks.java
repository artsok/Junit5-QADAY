package ru.sbt.qa.day.example3;


import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by sbt-sokovets-av
 */
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(PerfomanceBenchmark.class)
@interface PerfomanceBenchmarks {}
