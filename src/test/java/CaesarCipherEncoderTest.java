import org.junit.jupiter.api.Test;
import ua.javarush.encoder.model.impl.CaesarCipherEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarCipherEncoderTest {

    @Test
    void testCipherLowerCaseLetters() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String result = encoder.cipher("hello", 3);
        assertEquals("khoor", result);
    }

    @Test
    void testCipherUpperCaseLetters() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String result = encoder.cipher("HELLO", 3);
        assertEquals("KHOOR", result);
    }

    @Test
    void testCipherMixedCaseLetters() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String result = encoder.cipher("HelloWoRLD", 5);
        assertEquals("MjqqtBtWQI", result);
    }

    @Test
    void testCipherWithNonLetterCharacters() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String result = encoder.cipher("Hello, World!", 7);
        assertEquals("Olssv, Dvysk!", result);
    }

    @Test
    void testCipherWithEscapeSequences() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String result = encoder.cipher("Escape sequence: \n\t some message after", 1);
        assertEquals("Ftdbqf tfrvfodf: \n\t tpnf nfttbhf bgufs", result);
    }

    @Test
    void testCipherWithLargePositiveKey() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String message = "The quick brown fox jumps over the lazy dog";
        int key = 1000;
        String encryptedMessage = encoder.cipher(message, key);
        String result = encoder.decipher(encryptedMessage, key, false);
        assertEquals(message, result);
    }

    @Test
    void testCipherWithLargeNegativeKey() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String message = "The quick brown fox jumps over the lazy dog";
        int key = -1000;
        String encryptedMessage = encoder.cipher(message, key);
        String result = encoder.decipher(encryptedMessage, key, false);
        assertEquals(message, result);
    }

    @Test
    void testCipherWithHugeKey() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String message = "The quick brown fox jumps over the lazy dog";
        String encryptedMessage = encoder.cipher(message, Integer.MAX_VALUE);
        String result = encoder.decipher(encryptedMessage, Integer.MAX_VALUE, false);
        assertEquals(message, result);
    }

    @Test
    void testCipherWithNegativeHugeKey() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String message = "The quick brown fox jumps over the lazy dog";
        String encryptedMessage = encoder.cipher(message, Integer.MIN_VALUE);
        String result = encoder.decipher(encryptedMessage, Integer.MIN_VALUE, false);
        assertEquals(message, result);
    }


    @Test
    void testDecipherWithBruteForce() {
        // TODO test brute force
//        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
//        String result = encoder.decipher("JGNNQ", 5, true);
//        assertEquals("DAKKH", result);
    }
}
