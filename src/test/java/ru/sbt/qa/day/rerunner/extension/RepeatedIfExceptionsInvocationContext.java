package ru.sbt.qa.day.rerunner.extension;

import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

import static ru.sbt.qa.day.rerunner.extension.RepeatIfExceptionsCondition.isExceptionAppear;

/**
 * Created by Sokovets-AV
 */
public class RepeatedIfExceptionsInvocationContext implements TestTemplateInvocationContext {

    private final int currentRepetition;
    private final int totalRepetitions;
    private final RepeatedIfExceptionsDisplayNameFormatter formatter;

    RepeatedIfExceptionsInvocationContext(int currentRepetition, int totalRepetitions,
                                          RepeatedIfExceptionsDisplayNameFormatter formatter) {
        this.currentRepetition = currentRepetition;
        this.totalRepetitions = totalRepetitions;
        this.formatter = formatter;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return this.formatter.format(this.currentRepetition, this.totalRepetitions);
    }
}
