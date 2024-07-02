package org.example.VIEW;

import org.example.MODEL.EmployeeClass;

public class GUI {

    public static void start() {
        System.out.println("\nSimple Persistence Started ----------------- \n");
    }
    public static void end() {
        System.out.println("Simple Persistence Ended ----------------- \n");
    }

    public static void mainMenuGUI() {
        System.out.println("----- SIMPLE PERSISTENCE MENU -----");
        System.out.println(" 1. Display"
                        + "\n 2. Add"
                        + "\n 3. Delete"
                        + "\n 4. Update"
                        + "\n 5. Exit");
    }
    public static void displaysMenu(){
        System.out.println("----- DISPLAY MENU -----");
        System.out.println(" 1. Display All"
                        + "\n 2. Search"
                        + "\n 3. Back");
    }
    public static void addMenu(){
        System.out.println("----- ADD MENU -----");
        System.out.println(" 1. Add Employee"
                        + "\n 2. Back");
    }
    public static void deleteMenu(){
        System.out.println("----- DELETE MENU -----");
        System.out.println(" 1. Delete Employee"
                        + "\n 2. Back");
    }
    public static void updateMenu(){
        System.out.println("----- UPDATE MENU -----");
        System.out.println(" 1. Update Employee"
                        + "\n 2. Back");
    }


    public static void displayEmployee(EmployeeClass employee){
        System.out.println("Employee ID: " + employee.getId()
                        + "\nName: " + employee.getFName() + " " + employee.getLName()
                        + "\nHire Year: " + employee.getHireYear());
    }
}
