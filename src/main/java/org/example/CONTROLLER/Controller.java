package org.example.CONTROLLER;

import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Controller {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void startApplication() {
        GUI.start(); // prints out the start message
//        EmployeeFiles.readFile(); // reads any json file in upload folder.
//        EmployeeDatabase.sortEmployees(); // sorts the employees by id.
        mainMenu(); // calls the main menu
    }

    //region MAIN MENU
    public static void mainMenu() {
        try {
            while (true) {
                switch (mainMenuController()) {
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
                        System.out.println("Invalid option"); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // prints out an error message
        }

    }
    public static int mainMenuController(){
        //READ USER INPUT
        GUI.mainMenuGUI(); // prints out the main menu
        return Console.readInt(); // returns the user input
    }
    //endregion

    //region DISPLAY EMPLOYEE MENU
    public static void displayMenu(){
        try {
            boolean continueDisplayMenu = true; // Flag to continue the loop
            while (continueDisplayMenu) { // Loop to display the menu
                switch (displayMenuController()) { // Switch statement to display the menu
                    case 1: // DISPLAY ALL EMPLOYEES
                        EmployeeDisplayALL();
                        break;
                    case 2: // SEARCH EMPLOYEES
                        GUI.displayEmployee(EmployeeSearch());
                        break;
                    case 3: // EXIT TO MAIN MENU
                        continueDisplayMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static int displayMenuController(){
        GUI.displaysMenu();
        return Console.readInt();
    }
    //endregion

    //region ADD EMPLOYEE MENU
    public static void addMenu(){
        try {
            boolean continueAddMenu = true;
            while (continueAddMenu) {
                switch (addMenuController()) {
                    case 1: // ADD EMPLOYEE
                        EmployeeAdd();
                        break;
                    case 2: // EXIT TO MAIN MENU
                        continueAddMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        System.out.println("Invalid option"); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // prints out an error message
        }
    }
    public static int addMenuController(){
        GUI.addMenu(); // prints out the add menu
        return Console.readInt(); // returns the user input
    }
    //endregion

    //region DELETE EMPLOYEE MENU
    public static void deleteMenu(){
        try {
            boolean continueDeleteMenu = true; // Flag to continue the loop
            while (continueDeleteMenu) { // Loop to display the menu
                switch (deleteMenuController()) {
                    case 1: // DELETE EMPLOYEE
                        EmployeeDelete();
                        break;
                    case 2: // EXIT TO MAIN MENU
                        continueDeleteMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        System.out.println("Invalid option"); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // prints out an error message
        }
    }
    public static int deleteMenuController(){
        GUI.deleteMenu(); // prints out the delete menu
        return Console.readInt(); // returns the user input
    }
    //endregion  MENU_

    //region UPDATE EMPLOYEE MENU
    public static void updateMenu(){
        try {
            boolean continueDeleteMenu = true; // Flag to continue the loop
            while (continueDeleteMenu) { // Loop to display the menu
                switch (updateMenuController()) {
                    case 1: // UPDATE EMPLOYEE
                        EmployeeUpdate(); // calls the update employee method
                        break;
                    case 2: // EXIT TO MAIN MENU
                        continueDeleteMenu = false; // Set flag too false to exit loop
                        break;
                    default:
                        System.out.println("Invalid option"); // prints out an error message
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // prints out an error message
        }
    }

    public static int updateMenuController(){
        GUI.updateMenu(); // prints out the update menu
        return Console.readInt(); // returns the user input
    }
    //endregion


    public static void EmployeeUpdate(){
        //TODO: Search for Employee, Display Employee Information, Allow User to Update, Confirm Update, Update Employee in DATABASE & FILE
    }

    public static void EmployeeDisplayALL(){
        //TODO: Display all Employees in the Database & FILE
    }
    public static EmployeeClass EmployeeSearch(){
        //TODO: Search for Employee based off of ID, name, etc.
        return null; //FIXME: Return Employee
    }
    public static void EmployeeAdd(){
        //TODO: Search Database to ensure employee does not exist, Add Employee to DATABASE & FILE
    }
    public static void EmployeeDelete(){
        //TODO: Search for employee, Display Employee Information, Confirm Deletion, Delete Employee from DATABASE & FILE
    }
}
