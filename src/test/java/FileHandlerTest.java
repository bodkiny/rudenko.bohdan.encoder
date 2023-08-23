import org.junit.jupiter.api.Test;
import ua.javarush.encoder.utils.FileHandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileHandlerTest {
    private static final String TEST_INPUT_FILE_PATH = "C:\\Users\\USER\\Documents\\test.txt";
    private static final String TEST_OUTPUT_FILE_PATH = "C:\\Users\\USER\\Documents\\test_copy.txt";
    private static final String TEST_FILE_CONTENT = """
            Some file\tcontent
            for testing
            A\t\tFileHAndler methods.""";

    @Test
    void testReadWriteData() {
        FileHandler fileHandler = new FileHandler();

        // Read data from the file
        String firstReadContent = assertDoesNotThrow(() -> fileHandler.readData(TEST_INPUT_FILE_PATH));

        // Check for validness
        assertEquals(TEST_FILE_CONTENT, firstReadContent);

        // Write data again to the file
        assertDoesNotThrow(() -> fileHandler.writeData(TEST_OUTPUT_FILE_PATH, firstReadContent));

        // Read data from the file again
        String secondReadContent = assertDoesNotThrow(() -> fileHandler.readData(TEST_OUTPUT_FILE_PATH));

        // Check if the second read content matches the original content
        assertEquals(TEST_FILE_CONTENT, secondReadContent);
    }
}
