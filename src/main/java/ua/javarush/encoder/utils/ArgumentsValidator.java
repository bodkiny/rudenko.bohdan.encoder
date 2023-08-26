package ua.javarush.encoder.utils;

import java.io.File;
import java.nio.file.Path;

public final class ArgumentsValidator {

    public static final String ENCRYPT_COMMAND = "ENCRYPT";
    public static final String DECRYPT_COMMAND = "DECRYPT";
    public static final String BRUTE_FORCE_COMMAND = "BRUTE_FORCE";

    private ArgumentsValidator() {
    }

    public static void validateArguments(String[] args) {
        if (args.length < 2 || args.length > 3) {
            throw new InvalidArgumentsException("Usage: java -jar encoder.jar <COMMAND> <filePath> [key]");
        }

        String command = args[0];
        if (!ENCRYPT_COMMAND.equals(command) && !DECRYPT_COMMAND.equals(command) && !BRUTE_FORCE_COMMAND.equals(command)) {
            throw new InvalidArgumentsException("Invalid command.");
        }

        String filePath = args[1];
        if (!Path.of(filePath).isAbsolute()) {
            throw new InvalidArgumentsException("File path must be absolute.");
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new InvalidArgumentsException("File not found.");
        }

        if ((command.equals(ENCRYPT_COMMAND) || command.equals(DECRYPT_COMMAND)) && args.length < 3) {
            throw new InvalidArgumentsException("Please provide a key for encryption/decryption.");
        }

        if (command.equals(ENCRYPT_COMMAND) || command.equals(DECRYPT_COMMAND)) {
            try {
                Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentsException("Invalid key format.");
            }
        }
    }
}
