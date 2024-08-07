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

    //region REDIS CONNECTION
    private static Jedis jedis;
    public static void redisConnect() {
        ConsoleTimer.startTimer("RedisDBConnect");  // Start timer
        jedis = new Jedis("localhost", 12000);
        ConsoleTimer.stopTimer("RedisDBConnect"); // Stop timer
    }


    public static void redisClose() {
        if (jedis != null) {
            jedis.close(); // Close the connection
        }
    }
    //endregion

    //region REDIS CRUD OPERATIONS

    public static void redisCreateEmployee(EmployeeClass employee, boolean wantDisplay) {
        ConsoleTimer.startTimer("RedisDBAdd"); // Start timer
        String employeeKey = "employee_" + employee.getId(); // Assuming EmployeeClass has a getId() method
        jedis.set(employeeKey, employee.toJson()); // Convert EmployeeClass to JSON string and store in Redis
        if (wantDisplay) {
            System.out.println("Employee added: " + employeeKey);
        }
        ConsoleTimer.stopTimer("RedisDBAdd"); // Stop timer
    }

    public static void redisReadDB(boolean wantDisplay) {
        ConsoleTimer.startTimer("RedisDBRead"); // Start timer
        for (String key : jedis.keys("employee_*")) {
            String employeeData = jedis.get(key);
            EmployeeClass employee = EmployeeClass.fromJson(employeeData);
            EmployeeDatabase.addEmployeeFromRedisDB(employee, true);
            if (wantDisplay) {
                GUI.displayEmployee(employee);
            }
        }
        ConsoleTimer.stopTimer("RedisDBRead"); // Stop timer
    }

    public static void redisUpdateEmployee(EmployeeClass employee) {
        ConsoleTimer.startTimer("RedisDBUpdate"); // Start timer
        String employeeKey = "employee_" + employee.getId(); // Assuming EmployeeClass has a getId() method
        jedis.set(employeeKey, employee.toJson()); // Convert EmployeeClass to JSON string
        ConsoleTimer.stopTimer("RedisDBUpdate"); // Stop timer
    }

    public static void redisDeleteEmployee(EmployeeClass employee) {
        ConsoleTimer.startTimer("RedisDBDelete"); // Start timer
        String employeeKey = "employee_" + employee.getId(); // Assuming EmployeeClass has a getId() method
        jedis.del(employeeKey);
        ConsoleTimer.stopTimer("RedisDBDelete"); // Stop timer
    }
    //endregion



    //region REDIS INITIAL INSERT
    @Deprecated
    public static void importRecordsToRedis(String directoryPath) {
        ConsoleTimer.startTimer("RedisDBInsert"); // Start timer
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
                    StringBuilder content = new StringBuilder();
                    stream.forEach(content::append);

                    String[] data = content.toString().split(",");
                    int id = Integer.parseInt(data[0].trim());
                    String firstName = data[1].trim();
                    String lastName = data[2].trim();
                    int hireYear = Integer.parseInt(data[3].trim());

                    EmployeeClass employee = new EmployeeClass(id, firstName, lastName, hireYear);
                    redisCreateEmployee(employee, false);
                } catch (IOException e) {
                    GUI.error("Error reading file: " + file.getName());
                }
            }
        }
        ConsoleTimer.stopTimer("RedisDBInsert"); // Stop timer
    }
    //endregion
}