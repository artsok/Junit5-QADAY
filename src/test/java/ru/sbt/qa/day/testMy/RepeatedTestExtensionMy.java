package ru.sbt.qa.day.testMy;

import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.StringUtils;



class RepeatedTestExtensionMy implements TestTemplateInvocationContextProvider {

    private static final Logger logger = Logger.getLogger(RepeatedTestExtensionMy.class.getName());

    @Override
    public boolean supports(ContainerExtensionContext context) {
        return isAnnotated(context.getTestMethod(), RepeatedTest.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
        Method testMethod = Preconditions.notNull(context.getTestMethod().orElse(null), "test method must not be null");
        String displayName = context.getDisplayName();
        RepeatedTest repeatedTest = AnnotationUtils.findAnnotation(testMethod, RepeatedTest.class).get();
        int totalRepetitions = totalRepetitions(repeatedTest, testMethod);
        RepeatedIfThrowableDisplayNameFormatterMy formatter =
                displayNameFormatter(repeatedTest, testMethod, displayName);

        // @formatter:off
        return IntStream
                .rangeClosed(1, totalRepetitions)
                .mapToObj(repetition -> new RepeatedTestInvocationContextMy(repetition, totalRepetitions, formatter));
        // @formatter:on
    }

    private int totalRepetitions(RepeatedTest repeatedTest, Method method) {
        int repetitions = repeatedTest.value();

        // TODO [#242] Replace logging with precondition check once we have a proper mechanism for
        // handling validation exceptions during the TestEngine discovery phase.
        if (repetitions < 1) {
            String message = "Configuration error: @RepeatedTest on method [%s] must be declared with a positive 'value'. Defaulting to 1 repetition.";
            logger.warning(String.format(message, method));
            repetitions = 1;
          }

        return repetitions;
    }

    private RepeatedIfThrowableDisplayNameFormatterMy displayNameFormatter(RepeatedTest repeatedTest, Method method, String displayName) {

        String pattern = repeatedTest.name().trim();

        // TODO [#242] Replace logging with precondition check once we have a proper mechanism for
        // handling validation exceptions during the TestEngine discovery phase.
        if (StringUtils.isBlank(pattern)) {
            logger.warning(String.format(
                    "Configuration error: @RepeatedTest on method [%s] must be declared with a non-empty name.", method));
            pattern = AnnotationUtils.getDefaultValue(repeatedTest, "name", String.class).get();
        }

        return new RepeatedIfThrowableDisplayNameFormatterMy(pattern, displayName);
    }

}