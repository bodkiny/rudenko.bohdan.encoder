import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.javarush.encoder.controller.EncoderController;
import ua.javarush.encoder.model.impl.CaesarCipherEncoder;
import ua.javarush.encoder.model.interfaces.Encoder;
import ua.javarush.encoder.utils.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncoderControllerTest {
    private static final String INPUT_FILE_NAME = "src/test/resources/files_for_FileHandler_tests/ControllerTest_input.txt";
    private static final String INPUT_ENCRYPTED_FILE_NAME = "src/test/resources/files_for_FileHandler_tests/ControllerTest_encrypted_input.txt";
    private static final String ENCRYPTED_FILE_NAME = "src/test/resources/files_for_FileHandler_tests/ControllerTest_input[ENCRYPTED].txt";
    private static final String DECRYPTED_FILE_NAME = "src/test/resources/files_for_FileHandler_tests/ControllerTest_encrypted_input[DECRYPTED].txt";
    private Encoder encoder;
    private FileHandler fileHandler;
    private EncoderController encoderController;

    @BeforeEach
    void setUp() {
        encoder = new CaesarCipherEncoder();
        fileHandler = new FileHandler();
        encoderController = new EncoderController(encoder, fileHandler);
    }

    @AfterEach
    void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(ENCRYPTED_FILE_NAME).toAbsolutePath());
        Files.deleteIfExists(Paths.get(DECRYPTED_FILE_NAME).toAbsolutePath());
    }

    @Test
    void testRunEncryptCommand() throws IOException {
        String absolutePath = Path.of(INPUT_FILE_NAME).toAbsolutePath().toString();
        String[] args = {"ENCRYPT", absolutePath, "3"};
        String originalContent = "hello";
        Files.write(Paths.get(absolutePath), originalContent.getBytes());

        encoderController.run(args);

        Path resultPath = Path.of(ENCRYPTED_FILE_NAME).toAbsolutePath();
        assertTrue(Files.exists(resultPath));
        String encryptedContent = new String(Files.readAllBytes(resultPath));
        assertEquals("khoor", encryptedContent);
    }

    @Test
    void testRunDecryptCommand() throws IOException {
        String absolutePath = Path.of(INPUT_ENCRYPTED_FILE_NAME).toAbsolutePath().toString();
        String[] args = {"DECRYPT", absolutePath, "3"};
        String originalContent = "khoor";
        Files.write(Paths.get(absolutePath), originalContent.getBytes());

        encoderController.run(args);


        Path resultPath = Path.of(DECRYPTED_FILE_NAME).toAbsolutePath();
        assertTrue(Files.exists(resultPath));
        String decryptedContent = new String(Files.readAllBytes(resultPath));
        assertEquals("hello", decryptedContent);
    }
}
