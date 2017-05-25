package ru.sbt.qa.day.example10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by sbt-sokovets-av
 */
@Slf4j
class ArgumentsSourceTest {

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }

    static class MyArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> arguments(ContainerExtensionContext context) {
            return Stream.of("foo", "bar").map(ObjectArrayArguments::create);
        }
    }
}
