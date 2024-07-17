package org.example.CONTROLLER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CTRL SHIFT -  = Collapse all code
//CTRL SHIFT +  = Expand all code
@SuppressWarnings("unused")
public class ConsoleWrite {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //region TextColors (expand)
    public enum TextColor {BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE, DEFAULT}
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    //endregion

    //region BackgroundColors  (expand)
    public enum BackgroundColor {BLACK,RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE, DEFAULT}
    private static final String BLACK_BACKGROUND = "\u001B[40m";
    private static final String RED_BACKGROUND = "\u001B[41m";
    private static final String GREEN_BACKGROUND = "\u001B[42m";
    private static final String YELLOW_BACKGROUND = "\u001B[43m";
    private static final String BLUE_BACKGROUND = "\u001B[44m";
    private static final String PURPLE_BACKGROUND = "\u001B[45m";
    private static final String CYAN_BACKGROUND = "\u001B[46m";
    private static final String WHITE_BACKGROUND = "\u001B[47m";
    //endregion

    /**
     * Resets the text color and background color to console default values
     */
    private static final String RESET = "\u001B[0m";

    public static void writeLnYellow(String text){
        System.out.println(ConsoleWrite.YELLOW + text + RESET);
    }
    public static void writeLnBlue(String text){
        System.out.println(ConsoleWrite.BLUE + text + RESET);
    }
    public static void writeLnRed(String text){
        System.out.println(ConsoleWrite.RED + text + RESET);
    }
    public static void writeLnGreen(String text){
        System.out.println(ConsoleWrite.GREEN + text + RESET);
    }
    public static void writeLnPurple(String text){
        System.out.println(ConsoleWrite.PURPLE + text + RESET);
    }
    public static void writeLnCyan(String text){
        System.out.println(ConsoleWrite.CYAN + text + RESET);
    }
    public static void writeLnWhite(String text){
        System.out.println(ConsoleWrite.WHITE + text + RESET);
    }
    public static void writeLnBlack(String text){
        System.out.println(ConsoleWrite.BLACK + text + RESET);
    }

    public static String getStringWithColor(String text, TextColor color){
        return getTextColorText(color) + text + RESET;
    }

    /**
     * Gets an CHAR input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @return Returns a char value representing the users input
     */
    public static char getCharInput(String message){
        return getCharInput(message, TextColor.DEFAULT);
    }

    /**
     * Gets an CHAR input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * @param message  Message to show to the user as to what you are requesting
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns a char value representing the users input
     */
    public static char getCharInput(String message, TextColor textColor){
        char response = '\0'; //null in char
        do{
            String responseS = getStringInput(message, false, textColor);
            if (!responseS.isEmpty()) {
                response = responseS.charAt(0);
            }
        }while(response == '\0');
        return response;
    }

    /**
     * Gets an BOOLEAN input from the console.
     * sorting.library.Console will keep asking until a valid response is provided. Case-insensitive.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @param positive The positive value to ask the user for (e.g. "Yes")
     * @param negative The negative value to ask the user for (e.g. "No");
     * @return Returns an boolean value representing the users input
     */
    public static boolean getBooleanInput(String message, String positive, String negative){
        return getBooleanInput(message, positive, negative, TextColor.DEFAULT);
    }

    /**
     * Gets an BOOLEAN input from the console.
     * sorting.library.Console will keep asking until a valid response is provided. Case-insensitive.
     * @param message  Message to show to the user as to what you are requesting
     * @param positive The positive value to ask the user for (e.g. "Yes")
     * @param negative The negative value to ask the user for (e.g. "No");
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns an boolean value representing the users input
     */
    public static boolean getBooleanInput(String message, String positive, String negative, TextColor textColor){
        boolean response = false;
        boolean isValidResponse = false;
        do{
            String responseS = getStringInput(message, false, textColor);
            responseS = responseS.toLowerCase().trim();
            if(responseS.equals(positive.toLowerCase())){
                response = isValidResponse = true;
            } else if(responseS.equals(negative.toLowerCase())){
                isValidResponse = true;
                response = false;
            }
        }while(!isValidResponse);
        return response;
    }

    /**
     * Gets an DOUBLE input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @return Returns an double value representing the users input
     */
    public static double getDoubleInput(String message) {
        return getDoubleInput(message, TextColor.DEFAULT);
    }

    /**
     * Gets an DOUBLE input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * @param message  Message to show to the user as to what you are requesting
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns an double value representing the users input
     */
    public static double getDoubleInput(String message, TextColor textColor) {
        double response =-1;
        do {
            try {
                String responseS = getStringInput(message, false, textColor);
                response = Double.parseDouble(responseS);
            } catch (NumberFormatException n) {
                System.out.println("You entered an invalid number.");
            }
        }while(response == -1);
        return response;
    }

    /**
     * Gets an FLOAT input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @return Returns an float value representing the users input
     */
    public static float getFloatInput(String message) {
        return getFloatInput(message, TextColor.DEFAULT);
    }

    /**
     * Gets an FLOAT input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * @param message  Message to show to the user as to what you are requesting
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns an float value representing the users input
     */
    public static float getFloatInput(String message, TextColor textColor) {
        float response =-1;
        do {
            try {
                String responseS = getStringInput(message, false, textColor);
                response = Float.parseFloat(responseS);
            } catch (NumberFormatException n) {
                System.out.println("You entered an invalid number.");
            }
        }while(response == -1);
        return response;
    }

    /**
     * Gets an LONG input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @return Returns an long value representing the users input
     */
    public static long getLongInput(String message) {
        return getLongInput(message, TextColor.DEFAULT);
    }

    /**
     * Gets an LONG input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * @param message  Message to show to the user as to what you are requesting
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns an long value representing the users input
     */
    public static long getLongInput(String message, TextColor textColor) {
        long response = -1;
        do {
            try {
                String responseS = getStringInput(message, false, textColor);
                response = Long.parseLong(responseS);
            } catch (NumberFormatException n) {
                System.out.println("You entered an invalid number.");
            }
        }while(response == -1);
        return response;
    }

    /**
     * Gets an INT input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @return Returns an int value representing the users input
     */
    public static int getIntInput(String message) {
        return getIntInput(message, TextColor.DEFAULT);
    }
    public static final BufferedReader bread = new BufferedReader(new InputStreamReader(System.in));

    // myIO getUserInput str, int, & float
    public static String getUserStr(String prompt, boolean isRequired) {
        String strReturn = null;
        boolean bLoop = true;
        // loop until value is given if required
        while (bLoop) {
            if (strReturn == null)
                System.out.println(prompt);
            try {

                strReturn = bread.readLine();
                bLoop = (isRequired && (strReturn.isEmpty()));

            } catch (Exception ex) {
                ConsoleWrite.write("ERROR" + ex.getMessage(), TextColor.RED);
            }
            // is this required
        }
        return strReturn;
    }
    public static int getUserInt(String prompt, boolean isRequired) {
        boolean bloop = true;
        int num = 0;
        while (bloop) {
            try {
                bloop = false;
                num = Integer.parseInt(getUserStr(prompt, isRequired));
            } catch (Exception ex) {
                if (num == 0 && !isRequired) {
                    return num;

                }
                ConsoleWrite.write("{INVALID INPUT}, Please try again!\n", TextColor.RED);
            }
        }
        return num;
    }
    public static int getUserInt(String prompt, boolean isRequired, int min, int max) {
        boolean bloop = true;
        int num2 = 0;
        while (bloop) {
            num2 = getUserInt(prompt, isRequired);
            bloop = (num2 < min) || (num2 > min);
        }
        return num2;

    }
    public static int getUserInt() {
        return getUserInt(null, false);
    }
    public static float getUserFloat(String prompt, boolean isRequired) {
        boolean bloop = true;
        float num = 0;
        while (bloop) {
            try {
                bloop = false;
                num = Float.parseFloat(getUserStr(prompt, isRequired));
            } catch (Exception ex) {
                if (num == 0 && !isRequired) {
                    return num;
                }
                ConsoleWrite.write("{INVALID INPUT}, Please try again!\n", TextColor.RED);
            }
        }
        return num;
    }
    /**
     * Gets an INT input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * Uses the default text color
     * @param message  Message to show to the user as to what you are requesting
     * @param min Minimum number the user can provide
     * @param max Maximum number the user can provide
     * @return Returns an int value representing the users input
     */
    public static int getIntInput(String message, int min, int max){
        return getIntInput(message, min, max, TextColor.DEFAULT);
    }

    /**
     * Gets an INT input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * @param message  Message to show to the user as to what you are requesting
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns an int value representing the users input
     */
    public static int getIntInput(String message, TextColor textColor) {
        return getIntInput(message, Integer.MIN_VALUE, Integer.MAX_VALUE, textColor);
    }

    /**
     * Gets an INT input from the console.
     * sorting.library.Console will keep asking until a valid response is provided.
     * @param message  Message to show to the user as to what you are requesting
     * @param min Minimum number the user can provide
     * @param max Maximum number the user can provide
     * @param textColor ConsoleTextColor of the text when written
     * @return Returns an int value representing the users input
     */
    public static int getIntInput(String message, int min, int max, TextColor textColor){
        int response = -1;
        do {
            try {
                String responseS = getStringInput(message, false, textColor);
                response = Integer.parseInt(responseS);
                if(response < min || response > max){
                    response = -1;
                    ConsoleWrite.writeLn("Number must be between " + min + " and " + max + "!");
                }
            } catch (NumberFormatException n) {
                System.out.println("You entered an invalid number.");
            }
        }while(response == -1);
        return response;
    }

    /**
     * Gets a String input from the console.
     * Uses the default text color and does not allow empty
     * sorting.library.Console will keep asking until a valid response is provided
     * @param message Message to show to the user as to what you are requesting
     * @return The String the user typed meeting the requirements
     */
    public static String getStringInput(String message){
        return getStringInput(message, false);
    }

    /**
     * Gets a String input from the console.
     * Uses the default text color
     * sorting.library.Console will keep asking until a valid response is provided
     * @param message Message to show to the user as to what you are requesting
     * @param allowEmpty If the method should allow empty (e.g. enter key or space)
     * @return The String the user typed meeting the requirements
     */
    public static String getStringInput(String message, boolean allowEmpty) {
        return getStringInput(message, allowEmpty, TextColor.DEFAULT);
    }

    /**
     * Gets a String input from the console.
     * sorting.library.Console will keep asking until a valid response is provided
     * @param message Message to show to the user as to what you are requesting
     * @param allowEmpty If the method should allow empty (e.g. enter key or space)
     * @param textColor ConsoleTextColor of the text when written
     * @return The String the user typed meeting the requirements
     */
    public static String getStringInput(String message, boolean allowEmpty, TextColor textColor){
        String response = null;
        do{
            try {
                writeLn(message, textColor);
                response = br.readLine();
                if (!allowEmpty && response.trim().isEmpty()) {
                    System.out.println("You must enter a non empty answer!");
                    response = null;
                }
            }catch(IOException ex){
                writeLn(ex.getMessage(), TextColor.RED);
            }
        }while(response == null);
        return response;
    }

    /**
     * Gets a date from the user by asking for each part (Month, Day, Year)
     * Makes sure that the date typed falls between the two values provided.
     * @param minDate The minimum date allowed
     * @param maxDate The maximum date allowed
     * @return returns a java.time.LocalDate object populated with the users entered date
     */
    public static LocalDate getDateInput(LocalDate minDate, LocalDate maxDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.now();
        do {
            ConsoleWrite.writeLn(String.format("Enter a date between %s and %s :", minDate.format(formatter), maxDate.format(formatter)));
            date = getDateInput();
        }while(date.isBefore(minDate) || date.isAfter(maxDate));

        return date;
    }

    /**
     * Gets a date from the user by asking for each part (Month, Day, Year)
     * @return returns a java.time.LocalDate object populated with the users entered date
     */
    public static LocalDate getDateInput() {
        do {
            int month = getIntInput("Enter month (1-12): ",1, 12, TextColor.DEFAULT);
            int day = getIntInput("Enter day (1-31): ", 1, 31, TextColor.DEFAULT);
            int year = getIntInput("Enter year: ", 1000, 9999, TextColor.DEFAULT);

            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException ex) {
                System.out.println("Invalid date, try again.");
            }
        }while(true);
    }


    /**
     * Writes to the console using the default text color and background color.
     * Does not append the newline. Text stays on the same line.
     * @param message The message to write to the console
     */
    public static void write(String message){write(message, TextColor.DEFAULT, BackgroundColor.DEFAULT);}

    /**
     * Writes to the console using the provided text color and default background color.
     * Does not append the newline. Text stays on the same line.
     * @param message The message to write to the console
     * @param textColor The ConsoleTextColor to write the text in
     */
    public static void write(String message, TextColor textColor){
        System.out.print(getBackgroundColorText(BackgroundColor.DEFAULT) + getTextColorText(textColor) + message + RESET);
    }

    /**
     * Writes the message to the console using the privided text and background color
     * Does not append the newline. Text stays on the same line.
     * @param message The message to write to the console
     * @param textColor The ConsoleTextColor to write the text in
     * @param backgroundColor The ConsoleBackgroundColor to place behind the text
     */
    public static void write(String message, TextColor textColor, BackgroundColor backgroundColor){
        System.out.print(getBackgroundColorText(backgroundColor) + getTextColorText(textColor) + message + RESET);
    }

    /**
     * Writes to the console using the default text color and background color.
     * Appends the newline to the end.
     * @param message The message to write to the console
     */
    public static void writeLn(String message){ writeLn(message, TextColor.DEFAULT, BackgroundColor.DEFAULT);}

    /**
     * Writes to the console using the provided text color and default background color.
     * Appends the newline to the end.
     * @param message The message to write to the console
     * @param textColor The ConsoleTextColor to write the text in
     */
    public static void writeLn(String message, TextColor textColor) { writeLn(message, textColor, BackgroundColor.DEFAULT);}

    /**
     * Writes the message to the console using the provided text and background color
     * Append the newline to the end.
     * @param message The message to write to the console
     * @param textColor The ConsoleTextColor to write the text in
     * @param backgroundColor The ConsoleBackgroundColor to place behind the text
     */
    public static void writeLn(String message, TextColor textColor, BackgroundColor backgroundColor){
        System.out.println(getBackgroundColorText(backgroundColor) + getTextColorText(textColor) + message + RESET);
    }

    //BLACK, RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE
    private static String getTextColorText(TextColor textColor){
        return switch (textColor) {
            case RED -> ConsoleWrite.RED;
            case GREEN -> ConsoleWrite.GREEN;
            case YELLOW -> ConsoleWrite.YELLOW;
            case BLUE -> ConsoleWrite.BLUE;
            case PURPLE -> ConsoleWrite.PURPLE;
            case CYAN -> ConsoleWrite.CYAN;
            case BLACK -> ConsoleWrite.BLACK;
            case WHITE -> ConsoleWrite.WHITE;
            default -> RESET;
        };
    }

    private static String getBackgroundColorText(BackgroundColor backgroundColor){
        return switch (backgroundColor) {
            case RED -> ConsoleWrite.RED_BACKGROUND;
            case GREEN -> ConsoleWrite.GREEN_BACKGROUND;
            case YELLOW -> ConsoleWrite.YELLOW_BACKGROUND;
            case BLUE -> ConsoleWrite.BLUE_BACKGROUND;
            case PURPLE -> ConsoleWrite.PURPLE_BACKGROUND;
            case CYAN -> ConsoleWrite.CYAN_BACKGROUND;
            case BLACK -> ConsoleWrite.BLACK_BACKGROUND;
            case WHITE -> ConsoleWrite.WHITE_BACKGROUND;
            default -> RESET;
        };
    }
}