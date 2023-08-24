package ua.javarush.encoder;

import ua.javarush.encoder.controller.EncoderController;
import ua.javarush.encoder.model.impl.CaesarCipherEncoder;
import ua.javarush.encoder.utils.FileHandler;

public class ApplicationRunner {
    public static void main(String[] args) {
        EncoderController encoderController = new EncoderController(new CaesarCipherEncoder(), new FileHandler());
        encoderController.run(args);
    }
}
