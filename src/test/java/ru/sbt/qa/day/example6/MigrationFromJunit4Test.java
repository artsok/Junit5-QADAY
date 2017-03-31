package ru.sbt.qa.day.example6;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Created by sbt-sokovets-av
 */
@Slf4j
public class MigrationFromJunit4Test {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testJunit4() throws IOException {
        File createdFile = folder.newFile("myfile.txt");
        File createdFolder = folder.newFolder("subfolder");
        log.info("Migration test end" + createdFile.getAbsolutePath());
    }

}
