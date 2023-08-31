package ua.javarush.encoder.view;

import ua.javarush.encoder.controller.EncoderController;
import ua.javarush.encoder.model.interfaces.Encoder;
import ua.javarush.encoder.utils.FileHandler;
import ua.javarush.encoder.utils.InvalidArgumentsException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CaesarCipherSwingApp {
    public static final String ENCRYPT_COMMAND = "ENCRYPT";
    public static final String DECRYPT_COMMAND = "DECRYPT";

    private File selectedFile;
    private JFrame frame;
    private JSpinner keySpinner;
    private JRadioButton encryptRadioButton;
    private JRadioButton decryptRadioButton;
    private JRadioButton bruteForceRadioButton;

    private final EncoderController encoderController;

    public CaesarCipherSwingApp(Encoder encoder, FileHandler fileHandler) {
        encoderController = new EncoderController(encoder, fileHandler);
    }

    public void run() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Caesar cipher encoder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel fileLabel = new JLabel("Select File:");
        JButton fileChooserButton = new JButton("Choose File");
        fileChooserButton.addActionListener(e -> selectFile());

        JLabel operationLabel = new JLabel("Operation:");
        initializeRadioButtons();

        ButtonGroup operationGroup = new ButtonGroup();
        addButtonsToButtonGroup(operationGroup);

        JPanel operationPanel = new JPanel(new GridLayout(0, 1));
        addRadioButtonsToPanel(operationPanel);

        JLabel keyLabel = new JLabel("Key:");
        SpinnerNumberModel keySpinnerModel = new SpinnerNumberModel(0, 0, 26, 1);
        keySpinner = new JSpinner(keySpinnerModel);
        JFormattedTextField keyTextField = ((JSpinner.DefaultEditor) keySpinner.getEditor()).getTextField();
        keyTextField.setEditable(false);

        JButton processButton = new JButton("Process");
        processButton.addActionListener(e -> process());

        panel.add(fileLabel);
        panel.add(fileChooserButton);
        panel.add(operationLabel);
        panel.add(operationPanel);
        panel.add(keyLabel);
        panel.add(keySpinner);
        panel.add(new JLabel());
        panel.add(processButton);

        frame.add(panel);

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
    }

    private void process() {
        if (selectedFile == null) {
            showError("Please select a file!");
            return;
        } else if (getSelectedOperation() == null) {
            showError("Please select an operation!");
            return;
        }

        String filePath = selectedFile.getAbsolutePath();
        String operation = getSelectedOperation();
        int key = (int) keySpinner.getValue();
        String[] args = {operation, filePath, Integer.toString(key)};

        try {
            encoderController.run(args);
            showSuccess("Your file was successfully " + (ENCRYPT_COMMAND.equals(operation) ? "encrypted" : "decrypted") + "!");
        } catch (InvalidArgumentsException | IOException e) {
            showError(e.getMessage());
        }
    }

    private String getSelectedOperation() {
        if (encryptRadioButton.isSelected()) {
            return ENCRYPT_COMMAND;
        } else if (decryptRadioButton.isSelected()) {
            return DECRYPT_COMMAND;
        } else if (bruteForceRadioButton.isSelected()) {
            return "BRUTE_FORCE";
        } else {
            return null;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addRadioButtonsToPanel(JPanel operationPanel) {
        operationPanel.add(encryptRadioButton);
        operationPanel.add(decryptRadioButton);
        operationPanel.add(bruteForceRadioButton);
    }

    private void addButtonsToButtonGroup(ButtonGroup operationGroup) {
        operationGroup.add(encryptRadioButton);
        operationGroup.add(decryptRadioButton);
        operationGroup.add(bruteForceRadioButton);
    }

    private void initializeRadioButtons() {
        encryptRadioButton = new JRadioButton(ENCRYPT_COMMAND);
        decryptRadioButton = new JRadioButton(DECRYPT_COMMAND);
        bruteForceRadioButton = new JRadioButton("BRUTE FORCE");
    }
}
