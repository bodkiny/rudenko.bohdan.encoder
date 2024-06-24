package ua.javarush.encoder.model.interfaces;

public interface Encoder {
    String cipher(String message, int offset);

    String decipher(String message, int offset, boolean bruteForce);
}
