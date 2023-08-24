import org.junit.jupiter.api.Test;
import ua.javarush.encoder.utils.FileHandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileHandlerTest {
    private static final String TEST_INPUT_FILE_PATH = "src/test/resources/files_for_FileHandler_tests/FileHandler_test.txt";
    private static final String TEST_OUTPUT_FILE_PATH = "src/test/resources/files_for_FileHandler_tests/FileHandler_test_copy.txt";
    private static final String TEST_FILE_CONTENT = """
            Some file\tcontent
            for testing
            A\t\tFileHAndler methods.""";

    @Test
    void testReadWriteData() {
        FileHandler fileHandler = new FileHandler();

        String firstReadContent = assertDoesNotThrow(() -> fileHandler.readData(TEST_INPUT_FILE_PATH));

        assertEquals(TEST_FILE_CONTENT, firstReadContent);

        assertDoesNotThrow(() -> fileHandler.writeData(TEST_OUTPUT_FILE_PATH, firstReadContent));

        String secondReadContent = assertDoesNotThrow(() -> fileHandler.readData(TEST_OUTPUT_FILE_PATH));

        assertEquals(TEST_FILE_CONTENT, secondReadContent);
    }
}
