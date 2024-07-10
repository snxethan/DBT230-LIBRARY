package org.example.VIEW;

import org.example.MODEL.EmployeeClass;

import java.util.List;

public class GUI {

    //region INITIALIZATION & ERRORS
    public static void start() {
        System.out.println("\n----- SIMPLE PERSISTENCE STARTED ----- \n");
        System.out.println("Adding Employees from file...\n");
    }
    public static void readingPath(String path){
        System.out.println("Reading data from path... " + path);
    }
    public static void initializedEmployees(){
        System.out.println("Employees Initialized from File...\n");
    }
    public static void arrayEmployee(EmployeeClass employee){
        System.out.println("Employee #" + employee.getId() + " Added to Database!");
    }
    public static void arrayEmployees(){
        System.out.println("File Data Added to Database!\n");
    }
    public static void sortEmployeeDatabase(){
        System.out.println("Employee Database Sorted by [ID]");
    }
    public static void emptyEmployeeDatabase(){
        System.out.println("Employee Database is Empty!\n");
    }
    public static void errorAddingEmployeeFile(){
        System.out.println("Error Adding Employee from File!\n");
    }
    public static void errorReadingFile(String error){
        System.out.println("Error Reading File!\n" + error);
    }
    public static void error(String error){
        System.out.println("Error: " + error);
    }
    public static void invalidOption(){
        System.out.println("Invalid option!");
    }
    public static void onlyLetters(){
        System.out.println("Invalid input. ONLY Letters!");
    }
    public static void end() {
        System.out.println("\n----- SIMPLE PERSISTENCE ENDED ----- \n");
    }
    public static void displayMessage(String message){
        System.out.println(message);
    }
    //endregion

    //region MENUS
    public static void mainMenuGUI() {
        System.out.println(" ----- SIMPLE PERSISTENCE MENU -----");
        System.out.println("""
                 1. Display\

                 2. Add\

                 3. Delete\

                 4. Update\

                 5. Exit\
                """);
    }
    public static void displaysMenu(){
        System.out.println(" ----- DISPLAY MENU -----");
        System.out.println("""
                 1. Display All\

                 2. Search\

                 3. Back\
                """);
    }
    public static void addMenu(){
        System.out.println("- ---- ADD MENU -----");
        System.out.println(" 1. Add Employee"
                        + "\n 2. Back");
    }
    public static void deleteMenu(){
        System.out.println(" ----- DELETE MENU -----");
        System.out.println(" 1. Delete Employee"
                        + "\n 2. Back");
    }
    public static void updateMenu(){
        System.out.println(" ----- UPDATE MENU -----");
        System.out.println("""
                 1. Update Employee\

                 2. Update Database from File\

                 3. Back\
                """);
    }
    //endregion

    //region EMPLOYEE LOGIC
    public static void displayEmployee(EmployeeClass employee){
        System.out.println("#" + employee.getId()
                        + " / \"" + employee.getFName() + " " + employee.getLName()
                        + "\" / " + employee.getHireYear());
    }
    public static void displayEmployees(List<EmployeeClass> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (EmployeeClass employee : employees) {
                displayEmployee(employee);
            }
        }
    }

    public static void displayAllEmployees(){
        System.out.println("Displaying All Employees...");
    }
    public static void searchMenu(){
        System.out.println(" ----- SEARCH MENU -----");
        System.out.println("""
                 1. Search Employee by [ID]\

                 2. Search Employee by [Name]\

                 3. Search Employee by [Hire Year]\

                 4. Back\
                """);
    }
    public static void employeeSearch(String criteria){
        System.out.println("\nEnter Employee " + criteria + ": ");
    }
    public static void totalEmployeesFound(int total){
        System.out.println("TOTAL Employees: " + total); // Debug statement
    }

    public static void askName(){
        System.out.println("""
                Select Employee Name:\s
                 1) First Name\s
                 2) Last Name\s
                 3) Back\s""");
    }
    public static void askFirstName() {
        System.out.println("\nEnter Employee's First Name: ");
    }
    public static void askLastName() {
        System.out.println("\nEnter Employee's Last Name: ");
    }
    public static void askFirstNameSearch(){
        System.out.println("""
                Select Employee First Name:\s
                 1) Search by First Name\s
                 2) Search by ALL First Names\s
                 3) Back""");
    }
    public static void askLastNameSearch(){
        System.out.println("""
                Select Employee Last Name:\s
                 1) Search by Last Name\s
                 2) Search by ALL Last Names\s
                 3) Back""");
    }
    public static void askHireYear() {
        System.out.println("\nEnter Employee's Hire Year: ");
    }
    //endregion

    //region FILE LOGIC

    public static void choosePath() {
        System.out.println("""
                Select file path:\s
                 1) /long\s
                 2) /long serialized""");
    }

    //endregion

    //region TIMER LOGIC
    public static void timerOut(String name, long elapsed, String timeType){
        System.out.println("{" + name + " took " + elapsed + " " + timeType + "}");
    }
    public static void timerFailed(String name){
        System.out.println("{" + name + " could NOT be started}");
    }
    //endregion
}
