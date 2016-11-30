package ru.sbt.qa.day.example3;


import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(PerfomanceBenchmark.class)
@interface PerfomanceBenchmarks {}
