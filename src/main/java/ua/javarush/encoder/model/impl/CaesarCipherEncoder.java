package ua.javarush.encoder.model.impl;

import org.apache.commons.math3.stat.inference.ChiSquareTest;
import ua.javarush.encoder.model.interfaces.Encoder;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CaesarCipherEncoder implements Encoder {
    private static final char LETTER_A = 'a';
    private static final char LETTER_Z = 'z';
    private static final char UPPER_LETTER_A = 'A';
    private static final int ALPHABET_SIZE = LETTER_Z - LETTER_A + 1;
    private final double[] ENG_LETTERS_PROBABILITIES = {
            0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035, 0.074,
            0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003, 0.077,
            0.063, 0.093, 0.027, 0.013, 0.016, 0.005, 0.019, 0.001
    };

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
        if (bruteForce) {
            return cipher(message, ALPHABET_SIZE - (getProbableOffset(message) % ALPHABET_SIZE));
        } else {
            return cipher(message, ALPHABET_SIZE - (offset % ALPHABET_SIZE));
        }
    }

    private int getProbableOffset(String message) {
        double[] chiSquares = getChiSquares(message);

        int probableOffset = 0;
        for (int offset = 0; offset < chiSquares.length; offset++) {
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }

        System.out.println("Calculated probable offset: " + probableOffset);
        return probableOffset;
    }

    private double[] getChiSquares(String message) {
        double[] expectedLettersFrequencies = getExpectedLettersFrequencies(message.length());

        double[] chiSquares = new double[ALPHABET_SIZE];

        for (int offset = 0; offset < chiSquares.length; offset++) {
            String decipheredMessage = decipher(message, offset, false);
            long[] lettersFrequencies = getObservedLettersFrequencies(decipheredMessage);
            double chiSquare = new ChiSquareTest().chiSquare(expectedLettersFrequencies, lettersFrequencies);
            chiSquares[offset] = chiSquare;
        }

        return chiSquares;
    }

    private long[] getObservedLettersFrequencies(String decipheredMessage) {
        return IntStream.rangeClosed(LETTER_A, LETTER_Z)
                .mapToLong(letter -> countLetter((char) letter, decipheredMessage.toLowerCase()))
                .toArray();
    }

    private long countLetter(char letter, String decipheredLowerCaseMessage) {
        return decipheredLowerCaseMessage.chars()
                .filter(character -> character == letter)
                .count();
    }

    private double[] getExpectedLettersFrequencies(int messageLength) {
        return Arrays.stream(ENG_LETTERS_PROBABILITIES)
                .map(probability -> probability * messageLength)
                .toArray();
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
