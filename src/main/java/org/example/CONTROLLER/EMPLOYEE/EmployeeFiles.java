package org.example.CONTROLLER.EMPLOYEE;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;
import org.example.CONTROLLER.Console;
import java.io.*;

public class EmployeeFiles {
    static String uploadPath = "src/main/java/org/example/FILES/long"; // path to the upload folder


    /**
     * Reads the files from the upload folder.
     * Supported file formats: .txt, .json, .serialized.
     * Unsupported file formats will print an error message.
     * Calls the appropriate method to read the file based on the file format.
     * If the file format is not supported, it will print an error message.
     * If there are no files in the directory, it will print an error message.
     * If the file cannot be read, it will print an error message.
     */
    public static void readFile() {
        GUI.readingPath("'" + uploadPath + "'");

        File[] uploadFileList = new File(uploadPath).listFiles(); // gets the list of files in the upload folder

        if (uploadFileList == null || uploadFileList.length == 0) { // if there are no files in the directory
            GUI.errorReadingFile("No files found in the directory...");
            return;
        }

        GUI.initializedEmployees(); // prints out the initialized employees message

        for (File uploadedFile : uploadFileList) {
            String fileName = uploadedFile.getName(); // gets the name of the file
            if (fileName.endsWith(".txt")) { // if the file is a text file
                readTextFile(uploadedFile);
            } else if (fileName.endsWith(".json")) { // if the file is a json file
                readJsonFile(uploadedFile);
            } else if (fileName.endsWith(".serialized")) { // if the file is a serialized file
                readSerializedFile(uploadedFile);
            } else {
                GUI.errorReadingFile("Unsupported file format: " + fileName);   // prints out an error message
            }
        }
        GUI.arrayEmployees();
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
     * Reads the json file.
     * Parses the json object and adds the employee data.
     * If the file cannot be read, it will print an error message.
     * @param file the json file
     */
    public static void readJsonFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // reads the json file
            Gson gson = new Gson(); // creates a new Gson object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class); // reads the json object
            int eID = jsonObject.get("id").getAsInt(); // gets the id from the json object
            //gson and jsonobject is referenced from GitHub: Copilot & https://www.baeldung.com/gson, Stackoverflow, and Gson documentation
            String eFName = jsonObject.get("fname").getAsString(); // gets the first name from the json object
            String eLName = jsonObject.get("lname").getAsString(); // gets the last name from the json object
            int eHireYear = jsonObject.get("hireYear").getAsInt(); // gets the hire year from the json object

            EmployeeDatabase.addEmployeeToArray(eID, eFName, eLName, eHireYear); // adds the employee data

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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) { // reads the serialized file
            EmployeeClass employee = (EmployeeClass) ois.readObject(); // reads the employee object
            EmployeeDatabase.addEmployeeToArray(employee); // adds the employee data
        } catch (IOException | ClassNotFoundException e) { // catches any exceptions
            GUI.errorReadingFile(file.getName() + ":" + e.getMessage()); // prints out an error message
        }
    }

    /**
     * Adds the employee data to a file.
     * Supported file formats: .txt, .json, .serialized.
     * Calls the appropriate method to save the file based on the user's choice.
     * If the user's choice is invalid, it will print an error message.
     * @param employee the employee object
     */
    public static void addFile(EmployeeClass employee) {
        GUI.addFile(); // prints out the add file message
        try {
            switch (Console.readInt()){
                case 1:
                    saveAsTextFile(employee); // saves the employee data to a text file
                    break;
/*                case 2:
                    saveAsSerializedFile(employee); // saves the employee data to a serialized file
                    break;
                case 3:
                    saveAsJsonFile(employee); // saves the employee data to a json file
                    break;*/
                default:
                    GUI.error("Invalid option!"); // prints out an error message
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [addFile]"); // prints out an error message
        }
    }

    /**
     * Saves the employee data to a text file.
     * If the file cannot be saved, it will print an error message.
     * @param employee the employee object
     */
    private static void saveAsTextFile(EmployeeClass employee) {
        String fileName = uploadPath + "/" + employee.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { // writes the employee data to the file
            // Ensure only one space between first and last name
            String fullName = employee.getFName().trim() + " " + employee.getLName().trim(); // gets the full name
            writer.write(employee.getId() + ", " + fullName + ", " + employee.getHireYear()); // writes the employee data to the file
        } catch (IOException e) { // catches any exceptions
            GUI.errorReadingFile(fileName + ":" + e.getMessage()); // prints out an error message
        }
    }

    /**
     * Saves the employee data to a serialized file.
     * If the file cannot be saved, it will print an error message.
     * @param employee the employee object
     */
    private static void saveAsSerializedFile(EmployeeClass employee) {
        String fileName = uploadPath + "/" +  employee.getId() + ".serialized";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) { // writes the employee object to the file
            oos.writeObject(employee); // writes the employee object to the file
        } catch (IOException e) { // catches any exceptions
            GUI.errorReadingFile(fileName + ":" + e.getMessage()); // prints out an error message
        }
    }

    /**
     * Saves the employee data to a json file.
     * If the file cannot be saved, it will print an error message.
     * @param employee the employee object
     */
    private static void saveAsJsonFile(EmployeeClass employee) {
        Gson gson = new Gson(); // creates a new Gson object
        String json = gson.toJson(employee); // converts the employee object to json
        String fileName = uploadPath + "/" +  employee.getId() + ".json"; // creates the file name
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { // writes the json to the file
            writer.write(json); // writes the json to the file
        } catch (IOException e) { // catches any exceptions
            GUI.errorReadingFile(fileName + ":" + e.getMessage()); // prints out an error message
        }
    }

    public static void deleteFile(int id) {
        String[] extensions = {".txt", ".serialized", ".json"};
        for (String extension : extensions) { // Loop through the extensions
            File file = new File(EmployeeFiles.uploadPath + "/" + id + extension); // Create a file object
            if (file.exists() && file.delete()) { // Check if the file exists and delete it
                GUI.displayMessage("Deleted `file`: " + file.getName()); // Print a success message
                return; // Exit the method
            }
        }
        GUI.error("Failed to delete employee file."); // Print an error message
    }
}
