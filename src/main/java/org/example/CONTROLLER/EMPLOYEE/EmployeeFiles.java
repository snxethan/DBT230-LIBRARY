package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;
import org.example.CONTROLLER.Console;
import java.io.*;

public class EmployeeFiles {
    static String uploadPath = "src/main/java/org/example/FILES/long"; // default path to the upload folder

    //TODO: Serialize employees
            //TODO: create a serialized representation of each employee from a particular directory in the respective serialized directory

    //region READ FILES
    /**
     * Reads the files from the upload path.
     * If the directory path is invalid, it will print an error message.
     * If no files are found in the directory, it will print an error message.
     * Calls the method to process the file.
     * Calls the method to sort the employees.
     * Calls the method to stop the timer.
     * @return the upload path
     */
    public static void readFile() {
        ConsoleTimer.startTimer("ReadFiles");
        GUI.readingPath("'" + uploadPath + "'");

        File directory = new File(uploadPath);
        File[] uploadFileList = directory.listFiles();

        if (uploadFileList == null) {
            GUI.errorReadingFile("The directory path is invalid.");
            return;
        }

        if (uploadFileList.length == 0) {
            GUI.errorReadingFile("No files found in the directory...");
            return;
        }

        GUI.initializedEmployees();

        for (File uploadedFile : uploadFileList) {
            processFile(uploadedFile);
        }

        GUI.arrayEmployees();
        EmployeeDatabase.sortEmployees();
        ConsoleTimer.stopTimer("ReadFiles");
    }

    /**
     * Processes the file.
     * Supported file formats: .txt, .serialized.
     * Calls the appropriate method to process the file based on the file format.
     * If the file format is not supported, it will print an error message.
     * If the file cannot be processed, it will print an error message.
     * @param file the file
     */
    private static void processFile(File file) {
        String fileName = file.getName();
        try {
            if (fileName.endsWith(".txt")) {
                readTextFile(file);
            } else if (fileName.endsWith(".ser")) {
                readSerializedFile(file);
            } else {
                GUI.errorReadingFile("Unsupported file format: " + fileName);
            }
        } catch (Exception e) {
            GUI.errorReadingFile("Error processing file " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Reads the text file.
     * Parses the line and adds the employee data.
     * If the file cannot be read, it will print an error message.
     * @param file the text file
     */
    public static void readTextFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // reads the text file
            String line;
            while ((line = reader.readLine()) != null) { // reads each line in the text file
                EmployeeDatabase.addEmployeeFromFile(line); // parses the line and adds the employee data
            }
        } catch (IOException e) {
            GUI.errorReadingFile(file.getName() + ":" + e.getMessage()); // prints out an error message
        }
    }

    /**
     * Reads the serialized file.
     * Reads the employee object and adds the employee data.
     * If the file cannot be read, it will print an error message.
     * @param file the serialized file
     */
    public static void readSerializedFile(File file) {
        //FIXME
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) { // reads the serialized file
            EmployeeClass employee = (EmployeeClass) ois.readObject(); // reads the employee object
            EmployeeDatabase.addEmployeeToArray(employee); // adds the employee data
        } catch (IOException | ClassNotFoundException e) { // catches any exceptions
            GUI.errorReadingFile(file.getName() + ":" + e.getMessage()); // prints out an error message
        }
    }
    //endregion

    //region WRITE FILES
    /**
     * Adds the employee data to a file.
     * Supported file formats: .txt, .json, .serialized.
     * Calls the appropriate method to save the file based on the user's choice.
     * If the user's choice is invalid, it will print an error message.
     * @param employee the employee object
     */
    public static void addFile(EmployeeClass employee) {
        ConsoleTimer.startTimer("AddFile"); // starts the timer
        GUI.addFile(); // prints out the add file message
        try {
            switch (Console.readInt()){
                case 1:
                    saveAsTextFile(employee); // saves the employee data to a text file
                    break;
                case 2:
                    saveAsSerializedFile(employee); // saves the employee data to a serialized file
                    break;
                default:
                    GUI.error("Invalid option!"); // prints out an error message
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [addFile]"); // prints out an error message
        }
        ConsoleTimer.stopTimer("AddFile"); // stops the timer
    }

    /**
     * Saves the employee data to a text file.
     * If the file cannot be saved, it will print an error message.
     * @param employee the employee object
     */
    private static void saveAsTextFile(EmployeeClass employee) {
        ConsoleTimer.startTimer("SaveAsTextFile");
        String fileName = uploadPath + "/" + employee.getId() + ".txt";
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String fullName = employee.getFName().trim() + " " + employee.getLName().trim();
            writer.write(employee.getId() + ", " + employee.getFName() + ", " + employee.getLName() + ", " + employee.getHireYear());
            GUI.displayMessage("Employee saved to file: " + fileName);
        } catch (IOException e) {
            GUI.errorReadingFile("Failed to save employee to file " + fileName + ": " + e.getMessage());
        }
        ConsoleTimer.stopTimer("SaveAsTextFile");
    }

    /**
     * Saves the employee data to a serialized file.
     * If the file cannot be saved, it will print an error message.
     * @param employee the employee object
     */
    private static void saveAsSerializedFile(EmployeeClass employee) {
        ConsoleTimer.startTimer("SaveAsSerializedFile");
        String fileName = uploadPath + "/" + employee.getId() + ".ser";
        File file = new File(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(employee);
            GUI.displayMessage("Employee serialized to file: " + fileName);
        } catch (IOException e) {
            GUI.errorReadingFile("Failed to serialize employee to file " + fileName + ": " + e.getMessage());
        }
        ConsoleTimer.stopTimer("SaveAsSerializedFile");
    }
    //endregion

    //region DELETE FILES

    /**
     * Deletes the employee file.
     * Supported file formats: .txt, .serialized.
     * If the file does not exist, it will print an error message.
     * If the file cannot be deleted, it will print an error message.
     * @param id the employee id
     */
    public static void deleteFile(int id) {
        ConsoleTimer.startTimer("DeleteFile"); // starts the timer
        String[] extensions = {".txt", ".ser"};
        for (String extension : extensions) { // Loop through the extensions
            File file = new File(EmployeeFiles.uploadPath + "/" + id + extension); // Create a file object
            if (file.exists() && file.delete()) { // Check if the file exists and delete it
                GUI.displayMessage("Deleted `file`: " + file.getName()); // Print a success message
                return; // Exit the method
            }
        }
        GUI.error("Failed to delete employee file."); // Print an error message
        ConsoleTimer.stopTimer("DeleteFile"); // stops the timer
    }
    //endregion

    //region PATH MANAGER

    /**
     * Initializes the upload path.
     * Calls the method to choose the path.
     * If the path is invalid, it will print an error message.
     * @return the upload path
     */
    public static void choosePath() {
        GUI.choosePath(); // Display path options to the user
        try {
            int choice = Console.readInt(); // Get user input
            switch (choice) {
                case 1: // Set the upload path to the short folder
                    uploadPath = "src/main/java/org/example/FILES/simple";
                    break;
                case 2: // Set the upload path to the long folder
                    uploadPath = "src/main/java/org/example/FILES/long";
                    break;
                case 3:  // Set the upload path to the long ser folder
                    uploadPath = "src/main/java/org/example/FILES/long serialized";
                    break;
                default:
                    GUI.error("Invalid option!"); // Print an error message
                    break;
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [initializeUploadPath]");
        }
    }
    //endregion
}
