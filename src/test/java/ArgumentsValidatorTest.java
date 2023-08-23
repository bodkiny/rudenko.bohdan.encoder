import org.junit.jupiter.api.Test;
import ua.javarush.encoder.utils.ArgumentsValidator;
import ua.javarush.encoder.utils.InvalidArgumentsException;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentsValidatorTest {

    private static final String TEST_FILE_PATH = "C:\\Users\\USER\\Documents\\test.txt";

    @Test
    void testValidArguments() {
        assertDoesNotThrow(() -> ArgumentsValidator.validateArguments(new String[]{"ENCRYPT", TEST_FILE_PATH, "5"}));
        assertDoesNotThrow(() -> ArgumentsValidator.validateArguments(new String[]{"DECRYPT", TEST_FILE_PATH, "17"}));
        assertDoesNotThrow(() -> ArgumentsValidator.validateArguments(new String[]{"BRUTE_FORCE", TEST_FILE_PATH}));
    }

    @Test
    void testInvalidArguments() {
        Exception e = assertThrows(InvalidArgumentsException.class, () -> ArgumentsValidator.validateArguments(new String[]{"INVALID_COMMAND", "input.txt", "3"}));
        assertEquals("Invalid command.", e.getMessage());

        e = assertThrows(InvalidArgumentsException.class, () -> ArgumentsValidator.validateArguments(new String[]{"ENCRYPT", TEST_FILE_PATH}));
        assertEquals("Please provide a key for encryption/decryption.", e.getMessage());

        e = assertThrows(InvalidArgumentsException.class, () -> ArgumentsValidator.validateArguments(new String[]{"DECRYPT", "input.txt"}));
        assertEquals("File path must be absolute.", e.getMessage());

        e = assertThrows(InvalidArgumentsException.class, () -> ArgumentsValidator.validateArguments(new String[]{"BRUTE_FORCE"}));
        assertEquals("Usage: java -jar encoder.jar <COMMAND> <filePath> [key]", e.getMessage());

        e = assertThrows(InvalidArgumentsException.class, () -> ArgumentsValidator.validateArguments(new String[]{"ENCRYPT", TEST_FILE_PATH, "12", "extraArg"}));
        assertEquals("Usage: java -jar encoder.jar <COMMAND> <filePath> [key]", e.getMessage());

        e = assertThrows(InvalidArgumentsException.class, () -> ArgumentsValidator.validateArguments(new String[]{"DECRYPT", TEST_FILE_PATH, "twelve"}));
        assertEquals("Invalid key format.", e.getMessage());
    }
}
