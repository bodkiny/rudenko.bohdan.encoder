package ua.javarush.encoder.model.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarCipherEncoderTest {

    public static final int PLUG_OFFSET_VALUE = 0;

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
    void testDecipherSimpleLowerCaseMessageWithBruteForce() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();

        String cipheredMessage = "Ro qn qjm jwhcqrwp lxwormnwcrju cx bjh, qn faxcn rc rw lryqna, cqjc rb, " +
                "kh bx lqjwprwp cqn xamna xo cqn unccnab xo cqn juyqjknc, cqjc wxc j fxam lxdum kn vjmn xdc.";

        String expectedDecipheredMessage = "If he had anything confidential to say, he wrote it in cipher, that is, " +
                "by so changing the order of the letters of the alphabet, that not a word could be made out.";

        String result = encoder.decipher(cipheredMessage, PLUG_OFFSET_VALUE, true);
        assertEquals(expectedDecipheredMessage, result);
    }
}
