package org.example.CONTROLLER.EMPLOYEE;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.MODEL.EmployeeClass;

import java.io.*;

public class EmployeeFiles {
    static String uploadPath = "src/main/java/org/example/FILES"; // path to the upload folder
    static String errorReadFileSTR = "Error reading file "; // error message
    static String noFilesErrorSTR = "\nNO files found in the directory.\n"; // error message


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
        System.out.println("Reading data from path '" + uploadPath); // prints out the path of the upload folder

        File[] uploadFileList = new File(uploadPath).listFiles(); // gets the list of files in the upload folder

        if (uploadFileList == null) { // if there are no files in the directory
            System.out.println(noFilesErrorSTR); // prints out an error message
            return;
        } else if (uploadFileList.length == 0) { // if there are no files in the directory
            System.out.println(noFilesErrorSTR); // prints out an error message
            return;
        }


        for (File uploadedFile : uploadFileList) {
            String fileName = uploadedFile.getName(); // gets the name of the file
            if (fileName.endsWith(".txt")) { // if the file is a text file
                readTextFile(uploadedFile);
            } else if (fileName.endsWith(".json")) { // if the file is a json file
                readJsonFile(uploadedFile);
            } else if (fileName.endsWith(".serialized")) { // if the file is a serialized file
                readSerializedFile(uploadedFile);
            } else {
                System.out.println("Unsupported file format: " + fileName); // prints out an error message if the file format is not supported
            }
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
            System.out.println(errorReadFileSTR + file.getName() + ": " + e.getMessage()); // prints out an error message if the file cannot be read
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
            System.out.println(errorReadFileSTR + file.getName() + ": " + e.getMessage()); // prints out an error message if the file cannot be read
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
            EmployeeDatabase.addEmployeeToArray(employee);
        } catch (IOException | ClassNotFoundException e) { // catches any exceptions
            System.out.println(errorReadFileSTR + file.getName() + ": " + e.getMessage());
        }
    }

}
