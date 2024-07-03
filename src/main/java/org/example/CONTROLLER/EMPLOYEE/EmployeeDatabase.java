package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.Controller;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDatabase {
    static ArrayList<EmployeeClass> employees = new ArrayList<>(); // array list to store employee objects

    /**
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

    public static boolean checkArray(EmployeeClass employee){
        if(employees.contains(employee)){
            //already exists
//            GUI.existingEmployee(employee);
            return false;
        } else {
            employees.add(employee);
            GUI.arrayEmployee(employee);
            return true;
        }
    }

    public static void displayAllEmployees(){
        if(employees.isEmpty()) { // if the employee array list is empty
            GUI.emptyEmployeeDatabase();
        } else {
            GUI.displayAllEmployees();
            for (EmployeeClass employee : employees) { // for each employee in the array list
                GUI.displayEmployee(employee); // prints out the employee data
            }
        }
    }

    public static EmployeeClass searchEmployeeByID(int ID) {
        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getId() == ID) { // if the employee id matches the search id
                return employee; // returns the employee object
            }
        }
        return null;
    }

    public static EmployeeClass searchEmployeeByName(String name) {
        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getFName().equalsIgnoreCase(name) || employee.getLName().equalsIgnoreCase(name) || (employee.getFName() + " " + employee.getLName()).equalsIgnoreCase(name)) { // if the employee name matches the search name
                return employee; // returns the employee object
            }
        }
        return null;
    }

    public static EmployeeClass searchEmployeeByHireYear(int hireYear) {
        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getHireYear() == hireYear) { // if the employee hire year matches the search hire year
                return employee; // returns the employee object
            }
        }
        return null;
    }

    public static int findNextID() {
        Controller.updateEmployeesFromFile();
        int nextID = 1; // sets the next id to 1

        for (EmployeeClass employee : employees) { // for each employee in the array list
            if (employee.getId() >= nextID) { // if the employee id is greater than or equal to the next id
                nextID = employee.getId() + 1; // sets the next id to the employee id plus 1
            }
        }
        return nextID; // returns the next id
    }

    public static boolean removeEmployeeFromArray(EmployeeClass employee) {
        if(employees.contains(employee)) { // if the employee array list contains the employee object
            employees.remove(employee); // removes the employee object
            return true; // returns true
        } else {
            return false; // returns false
        }
    }
}



