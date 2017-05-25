package ru.sbt.qa.day.testMy;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.util.stream.Stream;

import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;


public class ProvaderMy implements TestTemplateInvocationContextProvider {


    @Override
    public boolean supports(ContainerExtensionContext context) {
        System.out.println(isAnnotated(context.getTestMethod(), RepeatedTestMy.class));
        return isAnnotated(context.getTestMethod(), RepeatedTestMy.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
        System.out.println("hello1");
        return null;
    }
}
