package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.CONTROLLER.Controller;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class EmployeeDatabase {

    //region EMPLOYEE DATABASE (Array & HashMaps)
    static ArrayList<EmployeeClass> employees = new ArrayList<>(); // array list to store employee objects
    static HashMap<Integer, EmployeeClass> employeeIdMap = new HashMap<>();
    static HashMap<String, ArrayList<EmployeeClass>> employeeLNameMap = new HashMap<>();
    static HashMap<String, ArrayList<EmployeeClass>> employeeFNameMap = new HashMap<>();
    static HashMap<Integer, EmployeeClass> employeeHireYearMap = new HashMap<>();
    //endregion

    //region ADD EMPLOYEES
    public static void addEmployeeFromRedisDB(EmployeeClass employee, boolean wantDisplay) {
        if (addEmployeeToArray(employee)) {
            if (wantDisplay)
                GUI.arrayEmployee(employee);
        } else {
            if(wantDisplay)
                GUI.errorAddingEmployeeMongoDB();
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
                return false;
            }
        }
        employees.add(employee);
        employeeIdMap.put(employee.getId(), employee);
        String lowerCaseLName = employee.getLName().toLowerCase();
        employeeLNameMap.putIfAbsent(lowerCaseLName, new ArrayList<>());
        employeeLNameMap.get(lowerCaseLName).add(employee);
        String lowerCaseFName = employee.getFName().toLowerCase();
        employeeFNameMap.putIfAbsent(lowerCaseFName, new ArrayList<>());
        employeeFNameMap.get(lowerCaseFName).add(employee);
        employeeHireYearMap.put(employee.getHireYear(), employee);
        return true;
    }

    /**
     * Displays all the employees in the array list.
     * If the employee array list is empty, it will print an error message.
     */
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
    public static EmployeeClass findEmployeeByID(int ID) {
        ConsoleTimer.startTimer("SearchEmployeeByLName");
        EmployeeClass employee = employeeIdMap.get(ID);
        ConsoleTimer.stopTimer("SearchEmployeeByLName");
        return employee;
    }

    /**
     * Searches for an employee by name.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param fname the employee name
     * @return the employee object
     */
    public static EmployeeClass findEmployeeByFName(String fname) {
        ConsoleTimer.startTimer("SearchEmployeeByName"); // starts the timer
        EmployeeClass employee = employeeFNameMap.get(fname.toLowerCase()).getFirst(); // Assuming you want the first match
        ConsoleTimer.stopTimer("SearchEmployeeByName"); // stops the timer
        return employee;
    }

    /**
     * Searches for an employee by last name.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param lName the employee last name
     * @return the employee object
     */
    public static EmployeeClass findEmployeeByLName(String lName) {
        ConsoleTimer.startTimer("SearchEmployeeByLName");
        EmployeeClass employee = employeeLNameMap.get(lName.toLowerCase()).getFirst(); // Assuming you want the first match
        ConsoleTimer.stopTimer("SearchEmployeeByLName");
        return employee;
    }

    /**
     * Searches for all employees with the given first name.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param fName the employee first name
     * @return the employee object
     */
    public static ArrayList<EmployeeClass> searchAllEmployeesFName(String fName) {
        ArrayList<EmployeeClass> employeeList = new ArrayList<>();
        String lowerCaseFName = fName.toLowerCase();

        // Search by first name
        if (employeeFNameMap.containsKey(lowerCaseFName)) {
            employeeList.addAll(employeeFNameMap.get(lowerCaseFName));
        }
        employeeList.sort(Comparator.comparingInt(EmployeeClass::getId));
        GUI.totalEmployeesFound(employeeList.size());
        return employeeList;
    }

    /**
     * Searches for all employees with the given last name.
     * If the employee is found, it will return the employee object.
     * If the employee is not found, it will return null.
     * @param lName the employee last name
     * @return the employee object
     */
    public static ArrayList<EmployeeClass> searchAllEmployeesLName(String lName) {
        ArrayList<EmployeeClass> employeeList = new ArrayList<>();
        String lowerCaseLName = lName.toLowerCase();

        // Search by last name
        if (employeeLNameMap.containsKey(lowerCaseLName)) {
            employeeList.addAll(employeeLNameMap.get(lowerCaseLName));
        }
        employeeList.sort(Comparator.comparingInt(EmployeeClass::getId));
        System.out.println("Found employees: " + employeeList.size()); // Debug statement
        return employeeList;
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
        EmployeeClass employee = employeeHireYearMap.get(hireYear);
        ConsoleTimer.stopTimer("SearchEmployeeByHireYear"); // stops the timer
        return employee;
    }

    /**
     * Finds the next available employee id.
     * If the employee id is greater than or equal to the next id, it will set the next id to the employee id plus 1.
     * @return the next id
     */
    public static int findNextID() {
        ConsoleTimer.startTimer("FindNextID");
        Controller.updateEmployeesFromMongoDB();
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