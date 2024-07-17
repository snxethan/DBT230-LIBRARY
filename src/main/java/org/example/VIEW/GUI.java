package org.example.VIEW;

import org.example.CONTROLLER.Console;
import org.example.MODEL.EmployeeClass;

import java.util.List;

public class GUI {

    //region INITIALIZATION & ERRORS
    public static void start() {
        Console.writeLnCyan( "\n----- SIMPLE PERSISTENCE STARTED ----- \n");
        Console.writeLnCyan("Adding Employees from file...\n");
    }
    public static void readingPath(String path){
        Console.writeLnCyan("Reading data from path... " + path);
    }
    public static void initializedEmployees(){
        Console.writeLnCyan("Employees Initialized from File...\n");
    }
    public static void arrayEmployee(EmployeeClass employee){
        Console.writeLnCyan("Employee #" + employee.getId() + " Added to Database!");
    }
    public static void arrayEmployees(){
        Console.writeLnCyan("File Data Added to Database!\n");
    }
    public static void sortEmployeeDatabase(){
        Console.writeLnCyan("Employee Database Sorted by [ID]");
    }
    public static void emptyEmployeeDatabase(){
        Console.writeLnRed("Employee Database is Empty!\n");
    }
    public static void errorAddingEmployeeFile(){
        Console.writeLnRed("Error Adding Employee from File!\n");
    }
    public static void errorReadingFile(String error){
        Console.writeLnRed("Error Reading File!\n" + error);
    }
    public static void error(String error){
        Console.writeLnRed("Error: " + error);
    }
    public static void invalidOption(){
        Console.writeLnRed("Invalid option!");
    }
    public static void end() {
        Console.writeLnCyan("\n----- SIMPLE PERSISTENCE ENDED ----- \n");
    }
    public static void displayMessage(String message){
        Console.writeLnCyan(message);
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

                 2. Update Database from File\

                 3. Back\
                """;
    }
    //endregion

    //region EMPLOYEE LOGIC
    public static void displayEmployee(EmployeeClass employee){
        Console.writeLnCyan( "#" + employee.getId()
                + " / \"" + employee.getFName() + " " + employee.getLName()
                + "\" / " + employee.getHireYear());
    }
    public static void displayEmployees(List<EmployeeClass> employees) {
        if (employees.isEmpty()) {
            Console.writeLnRed("NO Employees Found!");
        } else {
            for (EmployeeClass employee : employees) {
                displayEmployee(employee);
            }
        }
    }

    public static String displayAllEmployees(){
        return ("Displaying ALL Employees...");
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
        Console.writeLnCyan("TOTAL Employees Found: " + total);
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

    //region FILE LOGIC

    public static String choosePath() {
        return """
                Select file path: \

                 1) /long\

                 2) /long serialized""";
    }

    //endregion

    //region TIMER LOGIC
    public static void timerOut(String name, long elapsed, String timeType){
        Console.writeLnYellow("{" + name + " took " + elapsed + " " + timeType + "}");
    }
    public static void timerFailed(String name){
        Console.writeLnYellow("{" + name + " could NOT be started}");
    }
    //endregion
}
