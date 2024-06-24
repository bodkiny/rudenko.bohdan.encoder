# Caesar Cipher Swing Application

## Overview

The Caesar Cipher Swing Application is a simple desktop application that allows users to perform Caesar cipher
operations on text files. It provides an intuitive graphical interface for selecting a file, choosing an operation (
ENCRYPT, DECRYPT, or BRUTE FORCE), specifying a key, and processing the selected operation.

## Features

- User-friendly graphical interface.
- Selection of text files using a file chooser.
- Choose between ENCRYPT, DECRYPT, and BRUTE FORCE operations.
- Key input using a spinner with a range of 0 to 26.
- Displays success and error messages to the user.

## What's Implemented

- A GUI interface using Swing components.
- Operation selection using radio buttons.
- Key input validation and range restrictions.
- File selection using a file chooser.
- Process button to execute selected operations.
- Displaying success and error messages using JOptionPane.

## What's Not Implemented

- Support for non-Latin characters (e.g., Cyrillic characters).

## Test Coverage

- Unit tests have been written to cover various aspects of the project.
- Logic module of the Caesar cipher has been tested extensively, achieving a code coverage of 97%.

## How to Run

To build and run the application, follow these steps:

1. Clone this repository.
2. Open a terminal or command prompt and navigate to the project directory.
3. Build the project using Maven by running the following command: `mvn clean package`. This will create a .jar file in
   the `target` directory.
4. Run the .jar file using the command: `java -jar target/caesar-cipher-app-1.0.jar`.

## Feedback

While reviewing, please pay attention to:

- Code organization and adherence to the MVC architecture.
- Proper handling of GUI components and user interactions.
- Implementation of the Caesar cipher, decipher and brute force operations.
- The effectiveness of the unit tests and their coverage.