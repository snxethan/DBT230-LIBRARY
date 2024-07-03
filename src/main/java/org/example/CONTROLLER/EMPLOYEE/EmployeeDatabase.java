package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.Console;
import org.example.CONTROLLER.ConsoleTimer;
import org.example.CONTROLLER.Controller;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDatabase {
    //TODO: Index employees
            //TODO: Create an index (using a hashmap or dictionary or some other implementation) of employees with their ID as the key
            //TODO: Create an index (using a hashmap or dictionary or some other implementation) of employees with their Last Name as the key
    static ArrayList<EmployeeClass> employees = new ArrayList<>(); // array list to store employee objects
    static HashMap<Integer , EmployeeClass> employeeIdMap = new HashMap<Integer, EmployeeClass>();
    static HashMap<String , EmployeeClass> employeeLNameMap = new HashMap<String, EmployeeClass>();

    //region ADD EMPLOYEES
    /*
     * Parses the line and adds the employee data.
     * If the line does not match the pattern, it will print an error message.
     * Call the method to add the employee data to the array list.
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
//            System.out.println("Employee data found in file: " + line); // prints out the employee data
            EmployeeClass employee = new EmployeeClass(eID, eFName, eLName, eHireYear);
            addEmployeeToArray(employee); // adds the employee data
        } else {
           GUI.errorAddingEmployeeFile(); // prints out an error message
        }
    }


    //endregion

    //region ARRAY LOGIC
    /**
     * Sorts the employees by id and prints out the employee data.
     * Calls the method to sort the employees by id.
     */
    public static void sortEmployees(){
        if(employees.isEmpty()) { // if the employee array list is empty
            GUI.emptyEmployeeDatabase();
        } else {
            GUI.sortEmployeeDatabase();
            employees.sort(Comparator.comparingInt(EmployeeClass::getId)); // sorts the employees by id
        }
    }

    /**
     * Checks if the employee already exists in the array list.
     * If the employee does not exist, it will add the employee to the array list.
     * If the employee already exists, it will print an error message.
     * @param employee the employee object
     * @return true if the employee is added, false if the employee already exists
     */
    public static boolean checkArray(EmployeeClass employee) {
        for (EmployeeClass existingEmployee : employees) {
            if (existingEmployee.getFName().equalsIgnoreCase(employee.getFName()) && existingEmployee.getLName().equalsIgnoreCase(employee.getLName()) || existingEmployee.getId() == employee.getId()){
                // Employee with the same first and last name already exists
                // GUI.existingEmployee(employee); // Uncomment this if you want to display a message
                return false;
            }
        }
        // No matching employee found, add the new employee
        employees.add(employee);
        return true;
    }


        public static void displayAllEmployees(){
        ConsoleTimer.startTimer("DisplayAllEmployees");
        if(employees.isEmpty()) { // if the employee array list is empty
            GUI.emptyEmployeeDatabase();
        } else {
            GUI.displayAllEmployees();
            for (EmployeeClass employee : employees) { // for each employee in the array list
                GUI.displayEmployee(employee); // prints out the employee data
            }
        }
        ConsoleTimer.stopTimer("DisplayAllEmployees");
    }

    /**
     * Adds the employee data to the array list.
     * Prints out the employee data.
     * @param uploadedEmployee the employee object
     */
    public static boolean addEmployeeToArray(EmployeeClass uploadedEmployee) {
        if(checkArray(uploadedEmployee)){ // adds the employee data
            GUI.arrayEmployee(uploadedEmployee);
            return true; // returns true
        } else { // if the employee already exists
            return false; // returns false
        }
    }

    /**
     * Removes the employee object from the array list.
     * If the employee object is found, it will remove the employee object.
     * If the employee object is not found, it will return false.
     * @param employee the employee object
     * @return true if the employee is removed, false if the employee is not found
     */
    public static boolean removeEmployeeFromArray(EmployeeClass employee) {
        if(employees.contains(employee)) { // if the employee array list contains the employee object
            employees.remove(employee); // removes the employee object
            return true; // returns true
        } else {
            return false; // returns false
        }
    }
    //endregion

    //region SEARCH EMPLOYEES

    /**
     * Searches for an employee by ID.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param ID the employee id
     * @return the employee object
     */
    public static EmployeeClass searchEmployeeByID(int ID) {
        ConsoleTimer.startTimer("SearchEmployeeByID"); // starts the timer
        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getId() == ID) { // if the employee id matches the search id
                ConsoleTimer.stopTimer("SearchEmployeeByID"); // stops the timer
                return employee; // returns the employee object
            }
        }
        ConsoleTimer.stopTimer("SearchEmployeeByID"); // stops
        return null;
    }

    /**
     * Searches for an employee by name.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param name the employee name
     * @return the employee object
     */
    public static EmployeeClass searchEmployeeByName(String name) {
        ConsoleTimer.startTimer("SearchEmployeeByName"); // starts the timer
        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getFName().equalsIgnoreCase(name) || employee.getLName().equalsIgnoreCase(name) || (employee.getFName() + " " + employee.getLName()).equalsIgnoreCase(name)) { // if the employee name matches the search name
                ConsoleTimer.stopTimer("SearchEmployeeByName"); // stops the timer
                return employee; // returns the employee object
            }
        }
        ConsoleTimer.stopTimer("SearchEmployeeByName"); // stops the timer
        return null;
    }

    /**
     * Searches for an employee by hire year.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param hireYear the employee hire year
     * @return the employee object
     */
    public static EmployeeClass searchEmployeeByHireYear(int hireYear) {
        ConsoleTimer.startTimer("SearchEmployeeByHireYear"); // starts the timer
        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getHireYear() == hireYear) { // if the employee hire year matches the search hire year
                ConsoleTimer.stopTimer("SearchEmployeeByHireYear"); // stops the timer
                return employee; // returns the employee object
            }
        }
        ConsoleTimer.stopTimer("SearchEmployeeByHireYear"); // stops the timer
        return null;
    }

    /**
     * Finds the next available employee id.
     * If the employee id is greater than or equal to the next id, it will set the next id to the employee id plus 1.
     * @return the next id
     */
    public static int findNextID() {
        ConsoleTimer.startTimer("FindNextID");
        Controller.updateEmployeesFromFile();
        // Sort employees by ID to ensure they are in ascending order
        sortEmployees();

        int nextID = 1; // Start checking from the smallest possible positive ID

        for (EmployeeClass employee : employees) {
            if (employee.getId() == nextID) {
                // If the current ID matches the nextID, increment nextID to check for the next possible gap
                nextID++;
            } else if (employee.getId() > nextID) {
                // If a gap is found, nextID is the smallest missing ID, so break the loop
                break;
            }
        }

        ConsoleTimer.stopTimer("FindNextID");
        return nextID; // Returns the smallest missing ID or the next ID after the highest current ID
    }
    //endregion




}



