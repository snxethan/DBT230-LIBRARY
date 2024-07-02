package org.example.CONTROLLER;

import org.example.VIEW.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public static int readInt() {
        int input = -1; // Default or error value
        boolean valid = false;
        while (!valid) {
            try {
                String line = Controller.reader.readLine(); // Assuming Controller.reader is accessible and initialized
                input = Integer.parseInt(line);
                valid = true; // Input was successfully parsed
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IOException e) {
                System.out.println("Error reading from console. Please try again.");
            }
        }
        return input;
    }
}
