package ua.javarush.encoder.controller;

import ua.javarush.encoder.model.interfaces.Encoder;
import ua.javarush.encoder.utils.FileHandler;

public class EncoderController {
    private static final String ENCRYPT_COMMAND = "ENCRYPT";
    private static final String DECRYPT_COMMAND = "DECRYPT";
    private static final String BRUTE_FORCE_COMMAND = "BRUTE_FORCE";

    private Encoder encoder;
    private FileHandler fileHandler;

    public EncoderController(Encoder encoder, FileHandler fileHandler) {
        this.encoder = encoder;
        this.fileHandler = fileHandler;
    }

    public void run(String command, String filePath, int key) {
        throw new UnsupportedOperationException();
    }
}
