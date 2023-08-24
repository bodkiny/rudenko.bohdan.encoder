package ua.javarush.encoder.model.impl;

import ua.javarush.encoder.model.interfaces.Encoder;

public class CaesarCipherEncoder implements Encoder {
    private static final char LETTER_A = 'a';
    private static final char LETTER_Z = 'z';
    private static final char UPPER_LETTER_A = 'A';
    private static final int ALPHABET_SIZE = LETTER_Z - LETTER_A + 1;

    @Override
    public String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        offset %= ALPHABET_SIZE;

        if (offset < 0) {
            offset += ALPHABET_SIZE;
        }

        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char newCharacter = getNewCharacter(offset, character);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    @Override
    public String decipher(String message, int offset, boolean bruteForce) {
        return cipher(message, ALPHABET_SIZE - (offset % ALPHABET_SIZE));
    }

    private int getProbableOffset(String message) {
        throw new UnsupportedOperationException();
    }

    private static char getNewCharacter(int offset, char character) {
        char newCharacter;
        if (Character.isLowerCase(character)) {
            int originalAlphabetPosition = character - LETTER_A;
            int newAlphabetPosition = (originalAlphabetPosition + offset) % ALPHABET_SIZE;
            newCharacter = (char) (LETTER_A + newAlphabetPosition);
        } else {
            int originalAlphabetPosition = character - UPPER_LETTER_A;
            int newAlphabetPosition = (originalAlphabetPosition + offset) % ALPHABET_SIZE;
            newCharacter = (char) (UPPER_LETTER_A + newAlphabetPosition);
        }
        return newCharacter;
    }
}
