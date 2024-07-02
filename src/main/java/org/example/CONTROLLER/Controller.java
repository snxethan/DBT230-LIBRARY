package org.example.CONTROLLER;

import org.example.VIEW.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Controller {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void startApplication() {
        //TODO: ADD GUI & MENU OPTIONS.
        GUI.start();
//        EmployeeFiles.readFile(); // reads any json file in upload folder.
//        EmployeeDatabase.sortEmployees(); // sorts the employees by id.
        mainMenu();
        GUI.end();
    }
    public static void mainMenu() {
        try {
            while (true) {
                switch (mainMenuController()) {
                    case 1: // DISPLAY Employee
                        displayMenu();
                        break;
                    case 2: // ADD Employees
                        //TODO: ADD EMPLOYEES
                        break;
                    case 3: // DELETE Employee
                        //TODO: SEARCH FOR EMPLOYEE & DELETE
                        break;
                    case 4: // UPDATE Employee
                        //TODO: SEARCH FOR EMPLOYEE & UPDATE
                        break;
                    case 5: // EXIT Employee
                        GUI.end();
                        System.exit(0);
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
    public static int mainMenuController(){
        //READ USER INPUT
        GUI.mainMenuGUI();
        return Console.readInt();
    }

    public static void displayMenu(){
        try {
            boolean continueDisplayMenu = true;
            while (continueDisplayMenu) {
                switch (displayMenuController()) {
                    case 1: // DISPLAY ALL EMPLOYEES
                        //TODO: Implementation for displaying all employees
                        break;
                    case 2: // SEARCH EMPLOYEES
                        //TODO Implementation for searching employees
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
}
