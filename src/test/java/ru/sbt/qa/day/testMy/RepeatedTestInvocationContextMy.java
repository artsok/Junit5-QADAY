package ru.sbt.qa.day.testMy;

import static java.util.Collections.singletonList;

import java.util.List;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

public class RepeatedTestInvocationContextMy implements TestTemplateInvocationContext {

    private final int currentRepetition;
    private final int totalRepetitions;
    private final RepeatedIfThrowableDisplayNameFormatterMy formatter;

    public RepeatedTestInvocationContextMy(int currentRepetition, int totalRepetitions, RepeatedIfThrowableDisplayNameFormatterMy formatter) {
        this.currentRepetition = currentRepetition;
        this.totalRepetitions = totalRepetitions;
        this.formatter = formatter;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return this.formatter.format(this.currentRepetition, this.totalRepetitions);
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
        return singletonList(new RepetitionInfoParameterResolverMy(this.currentRepetition, this.totalRepetitions));
    }

}

