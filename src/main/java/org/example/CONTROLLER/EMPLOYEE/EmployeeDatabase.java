package org.example.CONTROLLER.EMPLOYEE;

import org.example.MODEL.EmployeeClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDatabase {
    static ArrayList<EmployeeClass> employees = new ArrayList<>(); // array list to store employee objects

    /**
     * Parses the line and adds the employee data.
     * If the line does not match the pattern, it will print an error message.
     * Calls the method to add the employee data to the array list.
     * @param line the line from the text file
     */
    public static void addEmployeeFromFile(String line) {
        Pattern pattern = Pattern.compile("(\\d+),\\s*(\\w+),\\s*(\\w+),\\s*(\\d+)"); // regex pattern to match the line
        Matcher matcher = pattern.matcher(line); // matches the line with the pattern
        if (matcher.find()) { // if the line matches the pattern
            int eID = Integer.parseInt(matcher.group(1)); // gets the employee id
            String eFName = matcher.group(2); // gets the employee first name
            String eLName = matcher.group(3); // gets the employee last name
            int eHireYear = Integer.parseInt(matcher.group(4)); // gets the employee hire year
            System.out.println("Employee data found in file:" + line); // prints out the employee data
            addEmployeeToArray(eID, eFName, eLName, eHireYear); // adds the employee data
        } else {
            System.out.println("No Employee found for line: " + line); // prints out an error message if the line does not match the pattern
        }
    }

    /**
     * Adds the employee data to the array list.
     * Prints out the employee data.
     * @param eID the employee id
     * @param eFName the employee first name
     * @param eLName the employee last name
     * @param eHireYear the employee hire year
     */
    public static void addEmployeeToArray(int eID, String eFName, String eLName, int eHireYear) {
        EmployeeClass uploadedEmployee = new EmployeeClass(eID, eFName, eLName, eHireYear); // creates a new employee object
        employees.add(uploadedEmployee); // adds the employee object to the employee array list
        System.out.println("Employee data added to array\n"); // prints out the employee data
    }

    /**
     * Adds the employee data to the array list.
     * Prints out the employee data.
     * @param uploadedEmployee the employee object
     */
    public static void addEmployeeToArray(EmployeeClass uploadedEmployee) {
        employees.add(uploadedEmployee); // adds the employee object to the employee array list
        System.out.println("Employee data added to array\n"); // prints out the employee data
    }

    /**
     * Sorts the employees by id and prints out the employee data.
     * Calls the method to sort the employees by id.
     */
    public static void sortEmployees(){
        if(employees.isEmpty()) { // if the employee array list is empty
            System.out.println("\nNO employees found in the array list!\n"); // prints out an error message
            return;
        } else {
            System.out.println("\nEmployee Array sorted by ID: \n");
            employees.sort(Comparator.comparingInt(EmployeeClass::getId)); // sorts the employees by id
            for (EmployeeClass employee : employees) { // prints out the employee data from employee array list
                System.out.println(employee.getEmployee());
            }
        }
    }
}
