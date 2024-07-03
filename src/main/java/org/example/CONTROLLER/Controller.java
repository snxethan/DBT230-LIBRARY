package org.example.CONTROLLER;

import org.example.CONTROLLER.EMPLOYEE.EmployeeDatabase;
import org.example.CONTROLLER.EMPLOYEE.EmployeeFiles;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;

public class Controller {
    public static void startApplication() {
        GUI.start(); // prints out the start message
        updateEmployeesFromFile(); // updates the employees from the file
        mainMenu(); // calls the main menu
    }

    //region MAIN MENU
    public static void mainMenu() {
        try {
            while (true) {
                GUI.mainMenuGUI(); // prints out the main menu
                switch (Console.readInt()) {
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
                        GUI.invalidOption();
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [mainMenu]");
        }

    }

    //endregion

    //region DISPLAY EMPLOYEE MENU
    public static void EmployeeDisplayALL() {
        EmployeeDatabase.displayAllEmployees();
    }

    public static void displayMenu() {
        try {
            boolean continueDisplayMenu = true; // Flag to continue the loop
            while (continueDisplayMenu) { // Loop to display the menu
                GUI.displaysMenu();
                switch (Console.readInt()) { // Switch statement to display the menu
                    case 1: // DISPLAY ALL EMPLOYEES
                        EmployeeDisplayALL();
                        break;
                    case 2: // SEARCH EMPLOYEES
                        EmployeeClass foundEmployee = searchMenu();
                        if (foundEmployee != null) {
                            GUI.displayEmployee(foundEmployee);
                        } else {
                            GUI.displayMessage("Employee not found.");
                        }
                        break;
                    case 3: // EXIT TO MAIN MENU
                        continueDisplayMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        GUI.invalidOption();
                        break;
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [displayMenu]");
        }
    }

    //endregion

    //region SEARCH FUNCTIONALITY

    public static EmployeeClass searchMenu() {
        EmployeeClass foundEmployee = null;
        try {
            boolean continueSearchMenu = true;
            while (continueSearchMenu) {
                GUI.searchMenu();
                int choice = Console.readInt();
                switch (choice) {
                    case 1: // Search by ID
                        foundEmployee = EmployeeSearchByID();
                        break;
                    case 2: // Search by Name
                        foundEmployee = EmployeeSearchByName();
                        break;
                    case 3: // Search by Hire Year
                        foundEmployee = EmployeeSearchByHireYear();
                        break;
                    case 4: // EXIT TO MAIN MENU
                        continueSearchMenu = false;
                        break;
                    default:
                        GUI.invalidOption();
                        break;
                }
                if (choice >= 1 && choice <= 3) {
                    // Removed the display message from here to avoid duplication
                    continueSearchMenu = false; // Assuming you want to exit after a search
                }
            }
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [searchMenu]");
        }
        return foundEmployee; // This return is not used currently but could be useful for future extensions.
    }
    public static EmployeeClass EmployeeSearchByID() {
        try {
            GUI.employeeSearch("[ID]");
            int id = Console.readInt();
            return EmployeeDatabase.searchEmployeeByID(id);
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeSearchByID]");
            return null; // Return null if an error occurs
        }
    }
    public static EmployeeClass EmployeeSearchByName(){
        try {
            GUI.employeeSearch("[First name] &/OR [Last name]:");
            String name = Console.readString();
            return EmployeeDatabase.searchEmployeeByName(name);
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeSearchByName]");
            return null;
        }
    }
    public static EmployeeClass EmployeeSearchByHireYear(){
        try {
            GUI.employeeSearch("[Hire Year]");
            int hireYear = Console.readInt();
            return EmployeeDatabase.searchEmployeeByHireYear(hireYear);
        } catch (Exception e) {
            GUI.error(e.getMessage() + " [EmployeeSearchByHireYear]");
            return null;
        }
    }
    //endregion

    //region ADD EMPLOYEE MENU
    public static void addMenu() {
        try {

            boolean continueAddMenu = true;
            while (continueAddMenu) {
                GUI.addMenu();
                switch (Console.readInt()) {
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
            GUI.error(e.getMessage() + " [addMenu]");
        }
    }
    //endregion

    //region ADD EMPLOYEE FUNCTIONALITY
    public static void EmployeeAdd(){
        int ID = EmployeeDatabase.findNextID();
        GUI.askFirstName();
        String fname = Console.readString().toUpperCase();
        GUI.askLastName();
        String lname = Console.readString().toUpperCase(); // Ask for last name
        GUI.askHireYear(); // Ask for hire year
        int hireYear = Console.readInt();
        EmployeeClass employee = new EmployeeClass(ID, fname, lname, hireYear);
        if(EmployeeDatabase.addEmployeeToArray(employee)){
            //employee can be added
            EmployeeFiles.addFile(employee); // saves the employee data to a file
        } else {
            //employee already exists
            GUI.error("Employee already exists!"); // prints out an error message
        }
    }
    //endregion

    //region DELETE EMPLOYEE MENU
    public static void deleteMenu() {
        try {

            boolean continueDeleteMenu = true; // Flag to continue the loop
            while (continueDeleteMenu) { // Loop to display the menu
                GUI.deleteMenu();
                switch (Console.readInt()) {
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
    //endregion  MENU_

    //region DELETE EMPLOYEE FUNCTIONALITY
    public static void EmployeeDelete() {

        GUI.employeeSearch("[ID]");
        int id = Console.readInt(); // Ask for employee ID
        if(id < 0) {
            GUI.error("ID cannot be negative.");
            return;
        }
        EmployeeClass employee = EmployeeDatabase.searchEmployeeByID(id); // Search for employee
        if (employee != null) {
            if (EmployeeDatabase.removeEmployeeFromArray(employee)) {
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
    public static void updateMenu() {
        try {
            boolean continueDeleteMenu = true; // Flag to continue the loop
            while (continueDeleteMenu) { // Loop to display the menu
                GUI.updateMenu();
                switch (Console.readInt()) {
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
    public static void updateEmployeesFromFile(){
        EmployeeFiles.readFile(); // reads the file
        EmployeeDatabase.sortEmployees(); // sorts the employees by id
    }
    public static void EmployeeUpdate() {
        try {
            EmployeeDatabase.displayAllEmployees();
            GUI.employeeSearch("[ID]");
            int id = Console.readInt(); // Ask for employee ID
            EmployeeClass employee = EmployeeDatabase.searchEmployeeByID(id);
            if (employee != null) {
                GUI.askFirstName(); // Ask for first name
                String fname = Console.readString().toUpperCase();
                GUI.askLastName(); // Ask for last name
                String lname = Console.readString().toUpperCase();
                GUI.askHireYear(); // Ask for hire year
                int hireYear = Console.readInt();

                // Update employee details
                employee.setFName(fname); // Update first name
                employee.setLName(lname); // Update last name
                employee.setHireYear(hireYear);

                // Delete old file
                EmployeeFiles.deleteFile(id);

                // Save new details to file
                EmployeeFiles.addFile(employee); // Save new details to file

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
