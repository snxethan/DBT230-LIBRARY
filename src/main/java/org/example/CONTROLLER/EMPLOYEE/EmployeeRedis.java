package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EmployeeRedis {

    // docker exec -it myredis bash
    // /opt/redislabs/bin/redis-cli -p 12000

    //region REDIS CONNECTION
    private static Jedis jedis; // Connection to Redis

    /**
     * Connect to Redis
     * <p> Connects to Redis on localhost:12000
     */
    public static void redisConnect() {
        ConsoleTimer.startTimer("RedisDBConnect");  // Start timer
        jedis = new Jedis("localhost", 12000); // Connect to Redis
        ConsoleTimer.stopTimer("RedisDBConnect"); // Stop timer
    }

    /**
     * Close Redis connection
     */
    public static void redisClose() {
        if (jedis != null) { // Check if connection is open
            jedis.close(); // Close the connection
        }
    }
    //endregion

    //region REDIS CRUD OPERATIONS

    /**
     * Create an employee in Redis
     * @param employee EmployeeClass object to be stored in Redis
     * @param wantDisplay boolean to display the employee data
     */
    public static void redisCreateEmployee(EmployeeClass employee, boolean wantDisplay) {
        ConsoleTimer.startTimer("RedisDBAdd"); // Start timer
        String employeeKey = "employee_" + employee.getId(); // Assuming EmployeeClass has a getId() method
        jedis.set(employeeKey, employee.toJson()); // Convert EmployeeClass to JSON string and store in Redis
        if (wantDisplay) {
            GUI.displayEmployee(employee); // Display employee data
        }
        ConsoleTimer.stopTimer("RedisDBAdd"); // Stop timer
    }

    /**
     * Read all employees from Redis
     * @param wantDisplay boolean to display the employee data
     */
    public static void redisReadDB(boolean wantDisplay) {
        ConsoleTimer.startTimer("RedisDBRead"); // Start timer
        for (String key : jedis.keys("employee_*")) { // Get all keys starting with "employee_"
            String employeeData = jedis.get(key); // Get data from Redis
            EmployeeClass employee = EmployeeClass.fromJson(employeeData); // Convert JSON string to EmployeeClass
            EmployeeDatabase.addEmployeeFromRedisDB(employee, true);
            if (wantDisplay) {
                GUI.displayEmployee(employee); // Display employee data
            }
        }
        ConsoleTimer.stopTimer("RedisDBRead"); // Stop timer
    }

    /**
     * Update an employee in Redis
     * @param employee EmployeeClass object to be updated in Redis
     */
    public static void redisUpdateEmployee(EmployeeClass employee) {
        ConsoleTimer.startTimer("RedisDBUpdate"); // Start timer
        String employeeKey = "employee_" + employee.getId(); // Assuming EmployeeClass has a getId() method
        jedis.set(employeeKey, employee.toJson()); // Convert EmployeeClass to JSON string
        ConsoleTimer.stopTimer("RedisDBUpdate"); // Stop timer
    }

    /**
     * Delete an employee from Redis
     * @param employee EmployeeClass object to be deleted from Redis
     */
    public static void redisDeleteEmployee(EmployeeClass employee) {
        ConsoleTimer.startTimer("RedisDBDelete"); // Start timer
        String employeeKey = "employee_" + employee.getId(); // Assuming EmployeeClass has a getId() method
        jedis.del(employeeKey);
        ConsoleTimer.stopTimer("RedisDBDelete"); // Stop timer
    }
    //endregion



    //region REDIS INITIAL INSERT
    /**
     * Import records to Redis
     * @param directoryPath path to the directory containing the files
     *<p> The files should be in the format: ID, First Name, Last Name, Hire Year
     */
    public static void importRecordsToRedis(String directoryPath) {
        ConsoleTimer.startTimer("RedisDBInsert"); // Start timer
        File directory = new File(directoryPath); // Create a file object
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt")); // Get all files ending with .txt

        if (files != null) {
            EmployeeRedis.redisConnect(); // Connect to Redis
            for (File file : files) {
                try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) { // Read file
                    StringBuilder content = new StringBuilder(); // Create a string builder
                    stream.forEach(content::append); // Append each line to the string builder

                    String[] data = content.toString().split(","); // Split the string by comma
                    int id = Integer.parseInt(data[0].trim()); // Assuming the first value is the ID
                    String firstName = data[1].trim(); // Assuming the second value is the first name
                    String lastName = data[2].trim(); // Assuming the third value is the last name
                    int hireYear = Integer.parseInt(data[3].trim()); // Assuming the fourth value is the hire year

                    EmployeeClass employee = new EmployeeClass(id, firstName, lastName, hireYear); // Create an employee object
                    redisCreateEmployee(employee, false); // Add employee to Redis
                    GUI.displayMessage(employee.getId() + " was imported to redis."); // Display success message
                } catch (IOException e) {
                    GUI.error("Error reading file: " + file.getName()); // Display error message
                } finally {
                    redisClose(); // Close the connection
                }
            }
            GUI.displayMessage("Records imported to Redis!"); // Display message

        }
        ConsoleTimer.stopTimer("RedisDBInsert"); // Stop timer
    }
    //endregion
}