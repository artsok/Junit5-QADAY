package ru.sbt.qa.day.depends;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Sokovets-AV
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@TestTemplate
@ExtendWith(DependsOnMethodCondition.class)
public @interface DependsOnMethod {

    String[] dependsOn() default "";
}
