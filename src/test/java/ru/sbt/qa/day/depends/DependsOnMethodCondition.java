package ru.sbt.qa.day.depends;

import org.junit.jupiter.api.extension.*;

import java.util.stream.Stream;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

/**
 * Created by Sokovets-AV
 */
public class DependsOnMethodCondition implements TestTemplateInvocationContextProvider, ContainerExecutionCondition {

    @Override
    public boolean supports(ContainerExtensionContext context) {
        return isAnnotated(context.getTestMethod(), DependsOnMethod.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
        //context.getTestMethod().flatMap(testMethods -> findAnnotation(testMethods, DependsOnMethod.class)).get()

        System.out.println("Что за контейнер " +
                context.getTestMethod().flatMap(testMethods -> findAnnotation(testMethods, DependsOnMethod.class)).get());
        return Stream.of(new TestTemplateInvocationContext(){});
    }


    @Override
    public ConditionEvaluationResult evaluate(ContainerExtensionContext context) {
        System.out.println("Что за контейнер2 " +
                context.getTestMethod().flatMap(testMethods -> findAnnotation(testMethods, DependsOnMethod.class)).get());
        return ConditionEvaluationResult.enabled("yues");
    }
}
