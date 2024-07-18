package org.example.VIEW;

import org.example.CONTROLLER.ConsoleWrite;
import org.example.MODEL.EmployeeClass;

import java.util.List;

public class GUI {

    //region INITIALIZATION & ERRORS
    public static void start() {
        ConsoleWrite.writeLnCyan( "\n----- SIMPLE PERSISTENCE STARTED ----- \n");
        ConsoleWrite.writeLnCyan("Adding Employees from MongoDB...\n");
    }
    public static void arrayEmployee(EmployeeClass employee){
        ConsoleWrite.writeLnCyan("Employee #" + employee.getId() + " Added to Database!");
    }
    public static void sortEmployeeDatabase(){
        ConsoleWrite.writeLnCyan("Employee Database Sorted by [ID]");
    }
    public static void emptyEmployeeDatabase(){
        ConsoleWrite.writeLnRed("Employee Database is Empty!\n");
    }
    public static void errorAddingEmployeeMongoDB(){
        ConsoleWrite.writeLnRed("Error Adding Employee from MongoDB!\n");
    }
    public static void error(String error){
        ConsoleWrite.writeLnRed("Error: " + error);
    }
    public static void invalidOption(){
        ConsoleWrite.writeLnRed("Invalid option!");
    }
    public static void end() {
        ConsoleWrite.writeLnCyan("\n----- SIMPLE PERSISTENCE ENDED ----- \n");
    }
    public static void displayMessage(String message){
        ConsoleWrite.writeLnCyan(message);
    }
    //endregion

    //region MENUS
    public static String mainMenuGUI() {
        return """
                 ----- SIMPLE PERSISTENCE MENU -----\

                 1. Display\

                 2. Add\

                 3. Delete\

                 4. Update\

                 5. Exit\
                """;
    }
    public static String displaysMenu(){
        return """
                 ----- DISPLAY MENU -----\

                 1. Display All\

                 2. Search\

                 3. Back\
                """;
    }
    public static String addMenu(){
        return """
                 ----- ADD MENU -----\

                 1. Add Employee\

                 2. Back\
                """;
    }
    public static String deleteMenu(){
        return """
                 ----- DELETE MENU -----\

                 1. Delete Employee\

                 2. Back\
                """;
    }
    public static String updateMenu(){
        return """
                 ----- UPDATE MENU -----\

                 1. Update Employee\

                 2. Update Database from MongoDB\

                 3. Back\
                """;
    }
    //endregion

    //region EMPLOYEE LOGIC
    public static void displayEmployee(EmployeeClass employee){
        ConsoleWrite.writeLnCyan( "#" + employee.getId()
                + " / \"" + employee.getFName() + " " + employee.getLName()
                + "\" / " + employee.getHireYear());
    }
    public static void displayEmployees(List<EmployeeClass> employees) {
        if (employees.isEmpty()) {
            ConsoleWrite.writeLnRed("NO Employees Found!");
        } else {
            for (EmployeeClass employee : employees) {
                displayEmployee(employee);
            }
        }
    }

    public static void displayAllEmployees(){
        ConsoleWrite.writeLnCyan ("Displaying ALL Employees...");
    }
    public static String searchMenu(){
        return """
                 ----- SEARCH MENU -----\

                 1. Search Employee by [ID]\

                 2. Search Employee by [Name]\

                 3. Search Employee by [Hire Year]\

                 4. Back\
                """;
    }
    public static String employeeSearch(String criteria){
        return "Search Employee by " + criteria + ":";
    }
    public static void totalEmployeesFound(int total){
        ConsoleWrite.writeLnCyan("TOTAL Employees Found: " + total);
    }

    public static String askName(){
        return """
                Select Employee Name:\s
                 1) First Name\s
                 2) Last Name\s
                 3) Back\s""";
    }
    public static String askFirstName() {
        return "\nEnter Employee's First Name: ";
    }
    public static String askLastName() {
        return "\nEnter Employee's Last Name: ";
    }
    public static String askFirstNameSearch(){
        return """
                Select Employee First Name: \

                 1) Search by First Name\

                 2) Search by ALL First Names\

                 3) Back""";
    }
    public static String askLastNameSearch(){
        return """
                Select Employee Last Name: \

                 1) Search by Last Name\

                 2) Search by ALL Last Names\

                 3) Back""";
    }
    public static String askHireYear() {
        return "\\nEnter Employee's Hire Year: ";
    }
    //endregion


    //region TIMER LOGIC
    public static void timerOut(String name, long elapsed, String timeType){
        ConsoleWrite.writeLnYellow("{" + name + " took " + elapsed + " " + timeType + "}");
    }
    public static void timerFailed(String name){
        ConsoleWrite.writeLnYellow("{" + name + " could NOT be started}");
    }
    //endregion
}
