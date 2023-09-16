package ua.javarush.encoder.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public String readData(String filePath) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                fileContent.append(bufferedReader.readLine()).append("\n");
            }
        }
        fileContent.setLength(fileContent.length() - 1);

        return fileContent.toString();
    }

    public void writeData(String filePath, String data) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(data);
        }
    }
}
