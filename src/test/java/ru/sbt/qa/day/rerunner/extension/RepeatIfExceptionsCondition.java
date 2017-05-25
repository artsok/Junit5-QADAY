package ru.sbt.qa.day.rerunner.extension;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.StringUtils;
import ru.sbt.qa.day.rerunner.RepeatedIfExceptionsTest;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Stream.of;
import static java.util.stream.StreamSupport.stream;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

/**
 * Created by Sokovets-AV
 */
@Slf4j
public class RepeatIfExceptionsCondition implements TestTemplateInvocationContextProvider, AfterTestExecutionCallback  {

    private static boolean exceptionAppear = false;

    private int totalRepeats = 0;

    private RepeatedIfExceptionsDisplayNameFormatter formatter;

    /**
     * Проверили вызываемый тестовый метод, что у него содержится аннотация {@link RepeatedIfExceptionsTest}
     * @param context
     * @return
     */
    @Override
    public boolean supports(ContainerExtensionContext context) {
        return isAnnotated(context.getTestMethod(), RepeatedIfExceptionsTest.class);
    }


    /**
     * Предоставление вызова контекста TestTemplateInvocationContext
     * @param context - Контейнер тестов (Контекст тестового класса)
     * @return
     */
    @Override
    public Stream<TestTemplateInvocationContext> provide(ContainerExtensionContext context) {
        /**Число повторений*/ totalRepeats = context.getTestMethod()
                .flatMap(testMethods -> findAnnotation(testMethods, RepeatedIfExceptionsTest.class))
                .orElseThrow(() -> new IllegalStateException("The extension should not be executed "
                        + "unless the test class is annotated with @RepeatedIfExceptionsTest."))
                .repeats();

        Method testMethod = Preconditions.notNull(context.getTestMethod().orElse(null), "test method must not be null");
        String displayName = context.getDisplayName();
        RepeatedIfExceptionsTest repeatedIfThrowableTest =
                findAnnotation(testMethod, RepeatedIfExceptionsTest.class).get();
        formatter = displayNameFormatter(repeatedIfThrowableTest, testMethod, displayName);

        //Создаем сплитератор, чтобы конвертнуть наш итератор в котором логика обработки повторения теста, вслучае ошибки
        Spliterator<TestTemplateInvocationContext> spliterator =
                spliteratorUnknownSize(new TestTemplateIterator(), Spliterator.NONNULL);
        return stream(spliterator, false);
    }


    private RepeatedIfExceptionsDisplayNameFormatter displayNameFormatter(RepeatedIfExceptionsTest test,
                                                                          Method method, String displayName) {
        String pattern = test.name().trim();
        if (StringUtils.isBlank(pattern)) {
            pattern = AnnotationUtils.getDefaultValue(test, "name", String.class).get();
        }
        return new RepeatedIfExceptionsDisplayNameFormatter(pattern, displayName);
    }

    /**
     * Создаем итератор в котором обрабатывается логика репитов {Повторить тест, если была ошибка в тестовом методе}
     */
    class TestTemplateIterator implements Iterator<TestTemplateInvocationContext> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if(currentIndex == 0) {
                return true;
            }
            if(exceptionAppear && currentIndex < totalRepeats) {
                return true;
            }
            return false;
        }

        @Override
        public TestTemplateInvocationContext next() {
            currentIndex++;
            return new RepeatedIfExceptionsInvocationContext(currentIndex, totalRepeats,  formatter);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        Class<? extends Exception>[] exceptionPool = context.getTestMethod()
                .flatMap(testMethods -> findAnnotation(testMethods, RepeatedIfExceptionsTest.class))
                .orElseThrow(() -> new IllegalStateException("The extension should not be executed "))
                .exceptions();
        Class<? extends Throwable> exception = context.getTestException()
                .orElse(new RepeatedIfException("There is no exception in context")).getClass();
        exceptionAppear = exceptionAppear || of(exceptionPool)
                .anyMatch(ex -> ex.isAssignableFrom(exception) && !RepeatedIfException.class.isAssignableFrom(exception));
    }

}
