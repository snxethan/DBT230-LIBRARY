package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;
import org.example.CONTROLLER.Console;
import java.io.*;
import java.util.Objects;

public class EmployeeFiles {
    //FIXME: remove serialization & txt file handling, handle to .json to mongoDB

//    //region PATHS
//    static String longPath = "src/main/java/org/example/FILES/long"; // path to long (.txt files)
//    static String serPath = "src/main/java/org/example/FILES/long serialized"; // path to long serialized (.ser files)
//    static String uploadPath = longPath; // default path (defaults to long if error)
//    //endregion

    //region READ FILES
    /**
     * Reads the files from the upload path.
     * If the directory path is invalid, it will print an error message.
     * If no files are found in the directory, it will print an error message.
     * Call the method to process the file.
     * Call the method to sort the employees.
     * Call the method to stop the timer.
     */
    //FIXME: remove serialization & txt file handling, handle to .json to mongoDB
    public static void readFile() {
//        ConsoleTimer.startTimer("ReadFiles");
//        GUI.readingPath("'" + uploadPath + "'");
//
//        File directory = new File(uploadPath);
//        File[] uploadFileList = directory.listFiles();
//
//        if (uploadFileList == null) {
//            GUI.errorReadingFile("The directory path is invalid.");
//            return;
//        }
//
//        if (uploadFileList.length == 0) {
//            GUI.errorReadingFile("No files found in the directory...");
//            return;
//        }
//
//        GUI.initializedEmployees();
//
//        for (File uploadedFile : uploadFileList) {
//            processFile(uploadedFile);
//        }
//
//        GUI.arrayEmployees();
//        EmployeeDatabase.sortEmployees();
//        ConsoleTimer.stopTimer("ReadFiles");
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
    //FIXME: remove serialization & txt file handling, handle to .json to mongoDB
    public static void deleteFile(int id) {
//        ConsoleTimer.startTimer("DeleteFile"); // starts the timer
//        File file = new File(longPath + "/" + id + ".txt"); // Create a file object
//        if (file.exists() && file.delete()) { // Check if the file exists and delete it
//            GUI.displayMessage("Deleted file: '" + file.getName() + "'"); // Print a success message
//        }
//        File fileSer = new File(serPath + "/" + id + ".ser"); // Create a ser file object
//        if (fileSer.exists() && fileSer.delete()) { // Check if the file exists and delete it
//            GUI.displayMessage("Deleted file: '" + fileSer.getName() + "'"); // Print a success message
//        }
//        ConsoleTimer.stopTimer("DeleteFile"); // stops the timer
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
    public static void addFile(EmployeeClass employee, boolean wantDisplay) {
//        ConsoleTimer.startTimer("AddFile"); // starts the timer
//        try {
//            saveAsTextFile(employee,wantDisplay); // saves the employee data to a text file
//            saveAsSerializedFile(employee,wantDisplay); // save the employee data to a .ser file
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        ConsoleTimer.stopTimer("AddFile"); // stops the timer
    }
    //endregion

    //region UPDATE FILE
    public static void updateFile(){

    }
    //endregion


//    /**
//     * Processes the file.
//     * Supported file formats: .txt, .serialized.
//     * Calls the appropriate method to process the file based on the file format.
//     * If the file format is not supported, it will print an error message.
//     * If the file cannot be processed, it will print an error message.
//     * @param file the file
//     */
//    @Deprecated
//    private static void processFile(File file) {
//        String fileName = file.getName();
//        try {
//            if (fileName.endsWith(".txt")) {
//                readTextFile(file);
//            } else if (fileName.endsWith(".ser")) {
//                readSerializedFile(file);
//            } else {
//                GUI.errorReadingFile("Unsupported file format: " + fileName);
//            }
//        } catch (Exception e) {
//            GUI.errorReadingFile("Error processing file " + fileName + ": " + e.getMessage());
//        }
//    }
//
//    /**
//     * Reads the text file.
//     * Parses the line and adds the employee data.
//     * If the file cannot be read, it will print an error message.
//     * @param file the text file
//     */
//    @Deprecated
//    public static EmployeeClass readTextFile(File file) {
//        EmployeeClass employee = null;
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                employee = EmployeeDatabase.addEmployeeFromFile(line);
//            }
//        } catch (IOException e) {
//            GUI.errorReadingFile(file.getName() + ":" + e.getMessage());
//        }
//        return employee;
//    }
//
//    /**
//     * Reads the serialized file.
//     * Reads the employee object and adds the employee data.
//     * If the file cannot be read, it will print an error message.
//     * @param file the serialized file
//     */
//    @Deprecated
//    public static EmployeeClass readSerializedFile(File file) throws IOException, ClassNotFoundException {
//        EmployeeClass employee;
//        try (FileInputStream fileIn = new FileInputStream(file);
//             CustomObjectInputStream ois = new CustomObjectInputStream(fileIn)) {
//            employee = (EmployeeClass) ois.readObject();
//            EmployeeDatabase.addEmployeeFromFile(employee);
//        }
//        return employee;
//    }
//
//    /**
//     * Custom ObjectInputStream class to handle the Employee class.
//     * Overrides the readClassDescriptor method to lookup the EmployeeClass.
//     * If the class name is "edu.neumont.dbt230.Employee", it will return the EmployeeClass.
//     */
//    @Deprecated
//    private static class CustomObjectInputStream extends ObjectInputStream {
//
//        public CustomObjectInputStream(InputStream in) throws IOException {
//            super(in);
//        }
//
//        /**
//         * Overrides the readClassDescriptor method to look up the EmployeeClass.
//         * If the class name is "edu.neumont.dbt230.Employee", it will return the EmployeeClass.
//         * @return the object stream class
//         */
//        @Override @Deprecated
//        protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
//            ObjectStreamClass desc = super.readClassDescriptor();
//            if (desc.getName().equals("edu.neumont.dbt230.Employee")) {
//                return ObjectStreamClass.lookup(EmployeeClass.class);
//            }
//            return desc;
//        }
//    }
//    //endregion



//    /**
//     * Saves the employee data to a text file.
//     * If the file cannot be saved, it will print an error message.
//     * @param employee the employee object
//     */
//    @Deprecated
//    private static void saveAsTextFile(EmployeeClass employee, boolean wantDisplay) {
//        if(wantDisplay) {
//            ConsoleTimer.startTimer("SaveAsTextFile");
//        }
//        String fileName = longPath + "/" + employee.getId() + ".txt";
//        File file = new File(fileName);
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            String fullName = employee.getFName().trim() + "," + employee.getLName().trim();
//            writer.write(employee.getId() + ", " + fullName + ", " + employee.getHireYear());
//            if(wantDisplay) {
//            GUI.displayMessage("Employee saved to file: " + fileName);
//            }
//        } catch (IOException e) {
//            GUI.errorReadingFile("Failed to save employee to file " + fileName + ": " + e.getMessage());
//        }
//        if(wantDisplay) {
//            ConsoleTimer.stopTimer("SaveAsTextFile");
//        }
//    }
//
//    /**
//     * Saves the employee data to a serialized file.
//     * If the file cannot be saved, it will print an error message.
//     * @param employee the employee object
//     */
//    @Deprecated
//    private static void saveAsSerializedFile(EmployeeClass employee, boolean wantDisplay) throws IOException {
//        if(wantDisplay){
//            ConsoleTimer.startTimer("SaveAsSerializedFile");
//        }
//
//
//        String fileName = serPath + "/" + employee.getId() + ".ser";
//        try (FileOutputStream fileOut = new FileOutputStream(fileName);
//             ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {
//            oos.writeObject(employee);
//        }
//
//        if(wantDisplay) {
//            GUI.displayMessage("Employee serialized to file: " + fileName);
//            ConsoleTimer.stopTimer("SaveAsSerializedFile");
//        }
//    }
//    //endregion


//    //region PATH MANAGER

//    /**
//     * Initializes the upload path.
//     * Calls the method to choose the path.
//     * If the path is invalid, it will print an error message.
//     */
//    @Deprecated
//    public static void choosePath() {
//        try {
//            int choice = Console.getIntInput(GUI.choosePath()); // Get user input
//            switch (choice) {
//                case 1: // Set the upload path to the long folder
//                    uploadPath = longPath;
//                    break;
//                case 2:  // Set the upload path to the long ser folder
//                    uploadPath = serPath;
//                    break;
//                default:
//                    GUI.error("Invalid option!"); // Print an error message
//                    break;
//            }
//        } catch (Exception e) {
//            GUI.error(e.getMessage() + " [initializeUploadPath]");
//        }
//    }
//    //endregion

    //region SYNC FILES

//    /**
//     * Syncs the files between the long and serialized folders.
//     * If the upload path is the long folder, it will sync the long files to the serialized folder.
//     */
//    @Deprecated
//    public static void syncFiles() {
//        ConsoleTimer.startTimer("SyncFiles");
//        if (Objects.equals(uploadPath, longPath)) {
//            // Sync long files to serialized folder
//            File directory = new File(longPath);
//            File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
//            if (files != null) {
//                for (File file : files) {
//                    try {
//                        EmployeeClass employee = readTextFile(file);
//                        if (employee != null) {
//                            saveAsSerializedFile(employee,false);
//                        }
//                    } catch (IOException e) {
//                        GUI.errorReadingFile("Error syncing file " + file.getName() + ": " + e.getMessage());
//                    }
//                }
//            }
//        } else if (Objects.equals(uploadPath, serPath)) {
//            // Sync serialized folder to long files
//            File directory = new File(serPath);
//            File[] files = directory.listFiles((dir, name) -> name.endsWith(".ser"));
//            if (files != null) {
//                for (File file : files) {
//                    try {
//                        EmployeeClass employee = readSerializedFile(file);
//                        if (employee != null) {
//                            saveAsTextFile(employee,false);
//                        }
//                    } catch (IOException | ClassNotFoundException e) {
//                        GUI.errorReadingFile("Error syncing file " + file.getName() + ": " + e.getMessage());
//                    }
//                }
//            }
//        }
//        ConsoleTimer.stopTimer("SyncFiles");
//    }
//    //endregion
}
