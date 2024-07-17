package org.example.CONTROLLER;

import org.example.CONTROLLER.EMPLOYEE.EmployeeDatabase;
import org.example.CONTROLLER.EMPLOYEE.EmployeeFiles;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    //region START ENTIRE APPLICATION
    /**
     * Starts the application.
     * Calls the method to choose the path.
     * Calls the method to start the GUI.
     * Calls the method to update the employees from the file.
     */
    public static void startApplication() {
        //FIXME
        ConsoleTimer.startTimer("startApplication"); // Start the timer
        GUI.start(); // prints out the start message
        updateEmployeesFromFile(); // updates the employees from the file
        ConsoleTimer.stopTimer("startApplication"); // stops the timer
        mainMenu(); // calls the main menu
    }
    //endregion

    //region MAIN MENU
    /**
     * Displays the main menu.
     * Calls the method to display the main menu GUI.
     * Displays search, add, delete, update, and exit options.
     */
    public static void mainMenu() {
        try {
            while (true) {
                switch (Console.getIntInput(GUI.mainMenuGUI())) {
                    case 1: // DISPLAY Employee
                        displayMenu(); // calls the display menu
                        break;
                    case 2: // ADD Employees
                        addMenu(); // calls the add menu
                        break;
                    case 3: // DELETE Employee
                        deleteMenu(); // calls the delete menu
                        break;
                    case 4: // UPDATE Employee
                        updateMenu(); // calls the update menu
                        break;
                    case 5: // EXIT Employee
                        GUI.end(); // prints out the end message
                        System.exit(0); // exits the application
                        break;
                    default:
                        GUI.invalidOption(); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [mainMenu]");
        }

    }
    //endregion

    //region DISPLAY EMPLOYEE MENU
    /**
     * Displays ALL employees.
     */
    public static void EmployeeDisplayALL() {
        EmployeeDatabase.displayAllEmployees();
    }

    /**
     * Displays the search/display menu.
     * Display ALL Employees, Search for Specific or all First & Last Names, Hire Year, ID.
     */
    public static void displayMenu() {
        try {
            boolean continueDisplayMenu = true; // Flag to continue the loop
            while (continueDisplayMenu) { // Loop to display the menu
                switch (Console.getIntInput(GUI.displaysMenu())) { // Switch statement to display the menu
                    case 1: // DISPLAY ALL EMPLOYEES
                        EmployeeDisplayALL();
                        break;
                    case 2: // SEARCH EMPLOYEES
                        List<EmployeeClass> foundEmployees = searchMenu(); // Search for employees
                        if (!foundEmployees.isEmpty()) { // Check if employees were found
                            GUI.displayEmployees(foundEmployees); // Display the found employees
                        } else {
                            GUI.displayMessage("Employee not found."); // Error message
                        }
                        break;
                    case 3: // EXIT TO MAIN MENU
                        continueDisplayMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        GUI.invalidOption(); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [displayMenu]");
        }
    }
    //endregion

    //region SEARCH FUNCTIONALITY
    /**
     * Displays the search menu.
     * Search by ID, Name, Hire Year, or Exit to Main Menu.
     * Calls the search methods based on the user input.
     * @return the list of found employees
     */
    public static List<EmployeeClass> searchMenu() {
        List<EmployeeClass> foundEmployees = new ArrayList<>(); // List to store found employees
        try {
            boolean continueSearchMenu = true; // Flag to continue the loop
            while (continueSearchMenu) { // Loop to display the menu
                int choice = Console.getIntInput(GUI.searchMenu()); // reads the user input
                switch (choice) { // Switch statement to display the menu
                    case 1: // Search by ID
                        EmployeeClass employeeByID = EmployeeSearchByID(); // Search for employee by ID
                        if (employeeByID != null) { // Check if employee was found
                            foundEmployees.add(employeeByID); // Add employee to the list
                        }
                        break;
                    case 2: // Search by Name
                        foundEmployees = EmployeeSearchByName(); // Search for employee by name
                        break;
                    case 3: // Search by Hire Year
                        EmployeeClass employeeByHireYear = EmployeeSearchByHireYear(); // Search for employee by hire year
                        if (employeeByHireYear != null) { // Check if employee was found
                            foundEmployees.add(employeeByHireYear); // Add employee to the list
                        }
                        break;
                    case 4: // EXIT TO MAIN MENU
                        continueSearchMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        GUI.invalidOption(); // prints out an error message
                        break;
                }
                if (choice >= 1 && choice <= 3) {
                    continueSearchMenu = false; // Assuming you want to exit after a search
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [searchMenu]"); // prints out an error message
        }
        return foundEmployees;
    }

    /**
     * Search for an employee by ID.
     * @return the employee object
     */
    public static EmployeeClass EmployeeSearchByID() {
        try {
            int id = Console.getIntInput(GUI.employeeSearch("[ID]")); // Ask for employee ID
            return EmployeeDatabase.findEmployeeByID(id); // Search for employee by ID
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeSearchByID]"); // prints out an error message
            return null; // Return null if an error occurs
        }
    }

    /**
     * Search for an employee by name.
     * Search by first & last name, ALL first & last names, or exit to display menu.
     * @return the list of found employees
     */
    public static List<EmployeeClass> EmployeeSearchByName() {
        List<EmployeeClass> foundEmployees = new ArrayList<>(); // List to store found employees
        try {
            switch (Console.getIntInput(GUI.askName())) {
                case 1:
                    switch (Console.getIntInput( GUI.askFirstNameSearch())) {
                        case 1:
                            String fname = Console.getStringInput(GUI.employeeSearch("First name:")); // Read the input
                            EmployeeClass employee = EmployeeDatabase.findEmployeeByFName(fname); // Search for employee by first name
                            if (employee != null) {  // Check if employee was found
                                foundEmployees.add(employee); // Add employee to the list
                            }
                            break;
                        case 2:
                            String fnameAll = Console.getStringInput( GUI.employeeSearch("{ALL} First name:")); // Read the input
                            foundEmployees = EmployeeDatabase.searchAllEmployeesFName(fnameAll); // Search for all employees by first name
                            break;
                        case 3: // Back option
                            break;
                        default:
                            GUI.invalidOption(); // prints out an error message
                            break;
                    }
                    break;
                case 2:
                    switch (Console.getIntInput( GUI.askLastNameSearch())) {
                        case 1:
                            String lname = Console.getStringInput( GUI.employeeSearch("Last name:")); // Read the input
                            EmployeeClass employee = EmployeeDatabase.findEmployeeByLName(lname); // Search for employee by last name
                            if (employee != null) { // Check if employee was found
                                foundEmployees.add(employee); // Add employee to the list
                            }
                            break;
                        case 2:
                            String lnameAll = Console.getStringInput(GUI.employeeSearch("{ALL} Last name:")); // Read the input
                            foundEmployees = EmployeeDatabase.searchAllEmployeesLName(lnameAll); // Search for all employees by last name
                            break;
                        case 3: // Back option
                            break;
                        default:
                            GUI.invalidOption(); // prints out an error message
                            break;
                    }
                    break;
                default:
                    GUI.invalidOption(); // prints out an error message
                    break;
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeSearchByName]"); // prints out an error message
        }
        return foundEmployees;
    }

    /**
     * Search for an employee by hire year.
     * @return the employee object
     */
    public static EmployeeClass EmployeeSearchByHireYear(){
        try {
            int hireYear = Console.getIntInput( GUI.employeeSearch("[Hire Year]")); // Read the input
            return EmployeeDatabase.searchEmployeeByHireYear(hireYear); // Search for employee by hire year
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeSearchByHireYear]"); // prints out an error message
            return null;
        }
    }
    //endregion

    //region ADD EMPLOYEE MENU
    /**
     * Displays the Add Employee menu.
     * Add Employee or Exit to Main Menu.
     */
    public static void addMenu() {
        try {

            boolean continueAddMenu = true; // Flag to continue the loop
            while (continueAddMenu) {
                switch (Console.getIntInput(GUI.addMenu())) { // Switch statement to display the menu
                    case 1: // ADD EMPLOYEE
                        EmployeeAdd();
                        break;
                    case 2: // EXIT TO MAIN MENU
                        continueAddMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        GUI.invalidOption(); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [addMenu]"); // prints out an error message
        }
    }
    //endregion

    //region ADD EMPLOYEE FUNCTIONALITY
    /**
     * Adds an employee to the database.
     * Asks for the first name, last name, and hire year.
     * Creates a new employee object and adds it to the database.
     * automatically assigns it an ID based off of database parameters
     */
    public static void EmployeeAdd(){
        int ID = EmployeeDatabase.findNextID(); // Find the next available ID
        String fname = Console.getStringInput(GUI.askFirstName()).toUpperCase(); // Ask for first name
        String lname = Console.getStringInput( GUI.askLastName()).toUpperCase(); // Ask for last name
        int hireYear = Console.getIntInput( GUI.askHireYear()); // Ask for hire year
        EmployeeClass employee = new EmployeeClass(ID, fname, lname, hireYear); // Create a new employee object
        if(EmployeeDatabase.addEmployeeToArray(employee)){
            //employee can be added
            EmployeeDatabase.sortEmployees(); // Sort the employees
            EmployeeFiles.addFile(employee,true); // saves the employee data to a file
        } else {
            //employee already exists
            GUI.error("Employee already exists!"); // prints out an error message
        }
    }
    //endregion

    //region DELETE EMPLOYEE MENU
    /**
     * Displays the deleted Menu.
     * Delete an Employee (Deletes from both long & long serialized paths, AND database)
     */
    public static void deleteMenu() {
        try {

            boolean continueDeleteMenu = true; // Flag to continue the loop
            while (continueDeleteMenu) { // Loop to display the menu
                switch (Console.getIntInput(GUI.deleteMenu())){
                    case 1: // DELETE EMPLOYEE
                        EmployeeDelete();
                        break;
                    case 2: // EXIT TO MAIN MENU
                        continueDeleteMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        GUI.invalidOption();
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [deleteMenu]");
        }
    }
    //endregion  MENU

    //region DELETE EMPLOYEE FUNCTIONALITY
    /**
     * Deletes an employee from the database.
     * Asks for the employee ID.
     * Searches for the employee in the database.
     * Removes the employee from the database.
     * Deletes the employee file.
     * Displays a success message if the employee was deleted.
     */
    public static void EmployeeDelete() {
        EmployeeDatabase.displayAllEmployees();
        int id = Console.getIntInput(GUI.employeeSearch("[ID]")); // Ask for employee ID
        if(id < 0) {
            GUI.error("ID cannot be negative.");
            return;
        }
        EmployeeClass employee = EmployeeDatabase.findEmployeeByID(id); // Search for employee
        if (employee != null) {
            if (EmployeeDatabase.removeEmployeeFromArray(employee)) {
                Console.writeLnCyan(GUI.displayAllEmployees());
                EmployeeFiles.deleteFile(employee.getId()); // Delete file
                GUI.displayMessage("Employee & file successfully deleted."); // Success message
            } else {
                GUI.error("Error deleting employee from array."); // Error message
            }
        } else {
            GUI.displayMessage("Employee not found."); // Error message
        }
    }


    //endregion

    //region UPDATE EMPLOYEE MENU
    /**
     * Displays the update menu.
     * Update Employee, Update Database from File, or Exit to Main Menu.
     * Calls the update employee method based on the user input.
     */
    public static void updateMenu() {
        try {
            boolean continueDeleteMenu = true; // Flag to continue the loop
            while (continueDeleteMenu) { // Loop to display the menu
                switch (Console.getIntInput(GUI.updateMenu())) {
                    case 1: // UPDATE EMPLOYEE
                        EmployeeUpdate(); // calls the update employee method
                        break;
                    case 2: // UPDATE DATABASE FROM FILE
                        updateEmployeesFromFile();
                        break;
                    case 3: // EXIT TO MAIN MENU
                        continueDeleteMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        GUI.invalidOption();
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [updateMenu]");
        }
    }
    //endregion

    //region UPDATE EMPLOYEE FUNCTIONALITY
    /**
     * re-reads the FILES/file path and updates database based off of saved data (.txt/.ser)
     */
    public static void updateEmployeesFromFile(){
        EmployeeFiles.readFile(); // reads the file
    }

    /**
     * Updates an employee in the database.
     * Asks for the employee ID.
     * Searches for the employee in the database.
     * Asks for the new first name, last name, and hire year.
     * Updates the employee details.
     */
    public static void EmployeeUpdate() {
        try {
            EmployeeDatabase.displayAllEmployees();
            int id = Console.getIntInput(GUI.employeeSearch("[ID]")); // Ask for employee ID
            EmployeeClass employee = EmployeeDatabase.findEmployeeByID(id);
            if (employee != null) {
                String fname = Console.getStringInput(GUI.askFirstName()).toUpperCase();
                String lname = Console.getStringInput(GUI.askLastName()).toUpperCase();
                int hireYear = Console.getIntInput(GUI.askHireYear());

                // Update employee details
                employee.setFName(fname); // Update first name
                employee.setLName(lname); // Update last name
                employee.setHireYear(hireYear);

                // Delete old file
                EmployeeFiles.deleteFile(id);

                // Save new details to file
                EmployeeFiles.addFile(employee,true); // Save new details to file

                GUI.displayMessage("Employee updated successfully.");
            } else {
                GUI.displayMessage("Employee not found.");
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeUpdate]");
        }
    }
    //endregion
}
