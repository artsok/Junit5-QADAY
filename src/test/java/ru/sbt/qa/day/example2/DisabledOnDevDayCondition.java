package ru.sbt.qa.day.example2;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
class DisabledOnDevDayCondition implements TestExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext testExtensionContext) {
        if (isDevDays()) {
            return disabled(testExtensionContext.getDisplayName() +  ": DevDays СБТ - Мы учимся");
        }
        return enabled("Работа кипит");
    }

    private boolean isDevDays() {
        return LocalDate.now().isEqual(LocalDate.of(2016, 11, 30))
               || LocalDate.now().isEqual(LocalDate.of(2016, 12, 1));
    }
}
