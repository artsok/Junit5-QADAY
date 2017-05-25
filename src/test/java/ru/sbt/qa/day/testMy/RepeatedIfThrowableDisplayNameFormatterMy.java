package ru.sbt.qa.day.testMy;

import static org.junit.jupiter.api.RepeatedTest.CURRENT_REPETITION_PLACEHOLDER;
import static org.junit.jupiter.api.RepeatedTest.DISPLAY_NAME_PLACEHOLDER;
import static org.junit.jupiter.api.RepeatedTest.TOTAL_REPETITIONS_PLACEHOLDER;

import org.junit.jupiter.api.RepeatedTest;

/**
 * Display name formatter for a {@link RepeatedTest @RepeatedTest}.
 *
 * @since 5.0
 */
class RepeatedIfThrowableDisplayNameFormatterMy {

    private final String pattern;
    private final String displayName;

    RepeatedIfThrowableDisplayNameFormatterMy(String pattern, String displayName) {
        this.pattern = pattern;
        this.displayName = displayName;
    }

    String format(int currentRepetition, int totalRepetitions) {
        return this.pattern
                .replace(DISPLAY_NAME_PLACEHOLDER, this.displayName)
                .replace(CURRENT_REPETITION_PLACEHOLDER, String.valueOf(currentRepetition))
                .replace(TOTAL_REPETITIONS_PLACEHOLDER, String.valueOf(totalRepetitions));
    }

}