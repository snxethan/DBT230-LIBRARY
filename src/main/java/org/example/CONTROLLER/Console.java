package org.example.CONTROLLER;

import org.example.VIEW.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    //region READ INPUT
    /**
     * Reads an integer from the console.
     * If the input is not an integer, it will print an error message.
     * @return the integer input
     */
    public static int readInt() {
        int input = -1; // Default or error value
        boolean valid = false; // Flag to continue the loop
        while (!valid) { // Loop to read the input
            try { // Try to parse the input
                String line = reader.readLine();
                input = Integer.parseInt(line); // Parse the input
                valid = true; // Input was successfully parsed
            } catch (NumberFormatException e) {
                GUI.error("Invalid input. Please enter a number."); // Print an error message
            } catch (IOException e) {
                GUI.error("Error reading input: " + e.getMessage()); // Print an error message
            }
        }
        return input; // Return the parsed input
    }

    /**
     * Reads a string from the console.
     * If the input is not a string, it will print an error message.
     * @return the string input
     */
    public static String readString() {
        String input = ""; // Default or error value
        boolean valid = false; // Flag to continue the loop
        while (!valid) { // Loop to read the input
            try { // Try to read the input
                input = reader.readLine(); // Read the input
                if(input.matches("^[a-zA-Z]+$")) { // Check if input matches the regex
                    valid = true; // Input is valid
                } else {
                    GUI.onlyLetters(); // Prompt for valid input
                }
            } catch (IOException e) {
                GUI.error("Error reading input: " + e.getMessage()); // Print an error message
            }
        }
        return input; // Return the valid input
    }
    //endregion
}