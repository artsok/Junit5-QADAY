package ru.sbt.qa.day.testMy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;


//@Tag("LoveYou")
@Slf4j
public class TestMy {


//    @BeforeEach
//    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
//        int currentRepetition = repetitionInfo.getCurrentRepetition();
//        int totalRepetitions = repetitionInfo.getTotalRepetitions();
//        String methodName = testInfo.getTestMethod().get().getName();
//        log.info(String.format("About to execute repetition %d of %d for %s", //
//                currentRepetition, totalRepetitions, methodName));
//    }


//    @RepeatedTestMy(10)
//    void templateWithDynamicParameterResolver() {
//        System.out.println("test");
//        Assertions.assertTrue(true, "много много");
//
//    }

    ThreadLocalRandom random = ThreadLocalRandom.current();

    @Disabled
    void reRunner() {

        int a = random.nextInt();
        assertTrue(5 > 0, "Простая проверочка");
        System.out.println("Test Test Запустили меняяя");
        //throw new RuntimeException("Меня выкинули");
        System.out.println(a);
        getException(a % 2);
    }

    Exception getException(int a) {
        System.out.println("a " + a);
        if(a == 1 ) {
            throw new RuntimeException("Ошибочка");
        }
        return null;
    }


//    @ExtendWith(ProvaderMy.class)
//    @TestTemplate
//    void test() {
//        System.out.println("One Two Free");
//    }


    public static class ProvaderMy implements TestTemplateInvocationContextProvider {

        @Override
        public boolean supports(ContainerExtensionContext context) {
            System.out.println(isAnnotated(context.getTestMethod(), RepeatedTestMy.class)); //Выводит, что на методе есть аннотация RepeatedTestMy
            return isAnnotated(context.getTestMethod(), RepeatedTestMy.class);
        }


        @Override
        public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
            System.out.println("Вызвали этот метод");
            Method testMethod = Preconditions.notNull(context.getTestMethod().orElse(null), "test method must not be null");
            String displayName = context.getDisplayName();
            System.out.println("displayName " + displayName);

            RepeatedTestMy repeatedTest = AnnotationUtils.findAnnotation(testMethod, RepeatedTestMy.class).get();
            int totalRepetitions = totalRepetitions(repeatedTest, testMethod);
            log.info("How many times we repear our test - " + totalRepetitions);

            RepeatedIfThrowableDisplayNameFormatterMy formatter = displayNameFormatter(repeatedTest, testMethod, displayName);

            // @formatter:off
            return IntStream
                    .rangeClosed(1, totalRepetitions)
                    .mapToObj(repetition -> new RepeatedTestInvocationContextMy(repetition, totalRepetitions, formatter));
            // @formatter:on
        }

        private int totalRepetitions(RepeatedTestMy repeatedTest, Method method) {
            int repetitions = repeatedTest.value();
            // TODO [#242] Replace logging with precondition check once we have a proper mechanism for
            // handling validation exceptions during the TestEngine discovery phase.
            if (repetitions < 1) {
                String message = "Configuration error: @RepeatedTest on method [%s] must be declared with a positive 'value'. Defaulting to 1 repetition.";
                log.info(String.format(message, method));
                repetitions = 1;
            }
            return repetitions;
        }

        private RepeatedIfThrowableDisplayNameFormatterMy displayNameFormatter(RepeatedTestMy repeatedTest, Method method, String displayName) {
            String pattern = repeatedTest.name().trim();
            // TODO [#242] Replace logging with precondition check once we have a proper mechanism for
            // handling validation exceptions during the TestEngine discovery phase.
            if (StringUtils.isBlank(pattern)) {
                log.info(String.format(
                        "Configuration error: @RepeatedTest on method [%s] must be declared with a non-empty name.", method));
                pattern = AnnotationUtils.getDefaultValue(repeatedTest, "name", String.class).get();
            }
            return new RepeatedIfThrowableDisplayNameFormatterMy(pattern, displayName);
        }
    }


    private static class StringParameterResolvingInvocationContextProvider implements TestTemplateInvocationContextProvider {

        @Override
        public boolean supports(ContainerExtensionContext context) {
            // @formatter:off
            return context.getTestMethod()
                    .map(Method::getParameterTypes)
                    .map(Arrays::stream)
                    .map(parameters -> parameters.anyMatch(Predicate.isEqual(String.class)))
                    .orElse(false);
            // @formatter:on
        }

        @Override
        public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
            return Stream.of(createContext("foo"), createContext("bar"));
        }

        private TestTemplateInvocationContext createContext(String argument) {
            return new TestTemplateInvocationContext() {

                @Override
                public String getDisplayName(int invocationIndex) {
                    return TestTemplateInvocationContext.super.getDisplayName(invocationIndex) + " " + argument;
                }

                @Override
                public List<Extension> getAdditionalExtensions() {
                    return singletonList(new ParameterResolver() {

                        @Override
                        public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                            return true;
                        }

                        @Override
                        public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                            return argument;
                        }
                    });
                }
            };
        }
    }
}
