package ru.sbt.qa.day.nicolaiparlog.disableiferror;

import org.junit.jupiter.api.extension.Extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

import java.util.Arrays;
import java.util.Optional;

public class DisabledIfTestFailedCondition implements TestExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {
        Class<? extends Exception> exceptionTypes[] = context.getTestClass()
                .flatMap(testClass -> AnnotationUtils.findAnnotation(testClass, DisabledIfTestFailedWith.class))
                .orElseThrow(() -> new IllegalStateException("The extension should not be executed "
                        + "unless the test class is annotated with @DisabledIfTestFailedWith."))
                .value();

        return disableIfExceptionWasThrown(context, exceptionTypes);
    }

    private ConditionEvaluationResult disableIfExceptionWasThrown(ExtensionContext context, Class<? extends Exception>[] exceptions) {
        return Arrays.stream(exceptions)
                .filter(ex -> wasThrown(context, ex))
                .findAny()
                .map(thrown -> ConditionEvaluationResult.disabled(thrown.getSimpleName() + " was thrown."))
                .orElseGet(() -> ConditionEvaluationResult.enabled(""));
    }

    private static boolean wasThrown(ExtensionContext context, Class<? extends Exception> exception) {
        return CollectExceptionExtension.getThrownExceptions(context)
                .map(Object::getClass)
                .anyMatch(exception::isAssignableFrom);
    }
}