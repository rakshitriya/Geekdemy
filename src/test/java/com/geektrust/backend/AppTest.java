package com.geektrust.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class AppTest {

    @TempDir
    Path tempDir;

    @Test
    public void run_ShouldProcessCommandsFromFile() throws IOException {
        // create a temp file and write some content to it
        Path file = Files.createFile(tempDir.resolve("input4.txt"));
        Files.write(file, Arrays.asList(
                "ADD_PROGRAMME CERTIFICATION 2",
                "ADD_PROGRAMME DEGREE 0",
                "ADD_PROGRAMME DIPLOMA 1",
                "APPLY_COUPON DEAL_G5",
                "ADD_PRO_MEMBERSHIP",
                "PRINT_BILL"
        ));

        // run the method with the file as argument
        App.run(new String[]{"INPUT_FILE=" + file.toString()});
    }

}
