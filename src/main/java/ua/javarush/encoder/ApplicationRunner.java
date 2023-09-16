package ua.javarush.encoder;

import ua.javarush.encoder.model.impl.CaesarCipherEncoder;
import ua.javarush.encoder.utils.FileHandler;
import ua.javarush.encoder.view.CaesarCipherSwingApp;

import javax.swing.*;
import java.io.IOException;

public class ApplicationRunner {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            CaesarCipherSwingApp app = new CaesarCipherSwingApp(new CaesarCipherEncoder(), new FileHandler());
            app.run();
        });
    }
}
