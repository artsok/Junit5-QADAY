package ru.sbt.qa.day.example3;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExtensionContext;

import static java.lang.System.out;
import static java.util.Collections.singletonMap;

/**
 * Created by sbt-sokovets-av 30.11.2016.
 */
class PerfomanceBenchmark  implements BeforeEachCallback, AfterEachCallback {

    private long launchTime;

    @Override
    public void afterEach(TestExtensionContext testExtensionContext) throws Exception {
        launchTime = System.currentTimeMillis();
    }

    @Override
    public void beforeEach(TestExtensionContext context) throws Exception {
        launchTime = System.currentTimeMillis() - launchTime;
        report(context, launchTime);
    }

    private static void report(ExtensionContext context, long elapsedTime) {
        String message = String.format("Test '%s' took %d ms.", context.getDisplayName(), elapsedTime);
        context.publishReportEntry(singletonMap("Benchmark", message));
        out.println(message);
    }
}
