package ua.javarush.encoder.model.impl;

import ua.javarush.encoder.model.interfaces.Encoder;

public class CaesarCipherEncoder implements Encoder {
    @Override
    public String cipher(String message, int offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String decipher(String message, int offset, boolean bruteForce) {
        throw new UnsupportedOperationException();
    }

    private int getProbableOffset(String message) {
        throw new UnsupportedOperationException();
    }
}
