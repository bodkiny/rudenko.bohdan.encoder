package ua.javarush.encoder.controller;

import ua.javarush.encoder.model.interfaces.Encoder;
import ua.javarush.encoder.utils.ArgumentsValidator;
import ua.javarush.encoder.utils.FileHandler;

import java.io.IOException;

import static ua.javarush.encoder.utils.ArgumentsValidator.DECRYPT_COMMAND;
import static ua.javarush.encoder.utils.ArgumentsValidator.ENCRYPT_COMMAND;

public class EncoderController {
    private static final int FILE_PATH_POSITION = 1;
    private static final int COMMAND_POSITION = 0;
    private static final int KEY_VALUE_POSITION = 2;
    private static final int BLANK_KEY_VALUE = 0;

    private final Encoder encoder;
    private final FileHandler fileHandler;

    public EncoderController(Encoder encoder, FileHandler fileHandler) {
        this.encoder = encoder;
        this.fileHandler = fileHandler;
    }

    public void run(String[] args) throws IOException {
        ArgumentsValidator.validateArguments(args);
        String command = args[COMMAND_POSITION];
        String filePath = args[FILE_PATH_POSITION];

        try {
            String textToProcess = fileHandler.readData(filePath);
            String result = getResultString(args, command, textToProcess);

            String newFilePath = generateOutputFilePath(filePath, command);
            fileHandler.writeData(newFilePath, result);
            System.out.println("Your file was successfully " + (command.equals(ENCRYPT_COMMAND) ? "[ENCRYPTED]" : "[DECRYPTED]"));
        } catch (IOException e) {
            throw e;
        }
    }

    private String getResultString(String[] args, String command, String textToProcess) {
        return switch (command) {
            case ENCRYPT_COMMAND -> {
                int key = Integer.parseInt(args[KEY_VALUE_POSITION]);
                yield encoder.cipher(textToProcess, key);
            }
            case DECRYPT_COMMAND -> {
                int key = Integer.parseInt(args[KEY_VALUE_POSITION]);
                yield encoder.decipher(textToProcess, key, false);
            }
            default -> encoder.decipher(textToProcess, BLANK_KEY_VALUE, true);
        };
    }

    private String generateOutputFilePath(String originalFilePath, String command) {
        String additionalFilePathValue = command.equals(ENCRYPT_COMMAND) ? "[ENCRYPTED]" : "[DECRYPTED]";
        return originalFilePath.replaceFirst("\\.txt$", additionalFilePathValue + ".txt");
    }
}
