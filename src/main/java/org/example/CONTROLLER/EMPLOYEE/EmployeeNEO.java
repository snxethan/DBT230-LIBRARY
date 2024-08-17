package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.MODEL.EmployeeClass;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class EmployeeNEO {
    private static final String URI = "bolt://localhost:";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static Driver driver;


    /***
     * Connects to the NEO4J database
     */
    public static void connectNEO4J() {
        ConsoleTimer.startTimer("NEO4JConnect"); // Start timer
        driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
        ConsoleTimer.stopTimer("NEO4JConnect"); // Stop timer
    }

    /***
     * Closes the connection to the NEO4J database
     * Closes the driver
     */
    public static void closeNEO4J() {
        ConsoleTimer.startTimer("NEO4JClose"); // Start timer
        if (driver != null) {
            driver.close();
        }
        ConsoleTimer.stopTimer("NEO4JClose"); // Stop timer
    }

    /***
     * Creates an employee in the NEO4J database
     * @param employee The employee to be created
     * @param wantDisplay If true, display the employee
     */
    public static void createEmployeeNEO4J(EmployeeClass employee, boolean wantDisplay) {
        ConsoleTimer.startTimer("NEO4JInsert"); // Start timer

        ConsoleTimer.stopTimer("NEO4JInsert"); // Stop timer
    }

    /***
     * Reads the NEO4J database, retrieves all employees
     * @param wantDisplay If true, display the employee
     */
    public static void readNEO4J(boolean wantDisplay) {
        ConsoleTimer.startTimer("NEO4JRead"); // Start timer

        ConsoleTimer.stopTimer("NEO4JRead"); // Stop timer

    }

    /***
     * Updates an employee in the NEO4J database
     * @param employee The employee to be updated
     */
    public static void updateEmployeeNEO4J(EmployeeClass employee) {
        ConsoleTimer.startTimer("NEO4JUpdate"); // Start timer

        ConsoleTimer.stopTimer("NEO4JUpdate"); // Stop timer
    }

    /***
     * Deletes an employee in the NEO4J database
     * @param employee The employee to be deleted
     */
    public static void deleteEmployeeNEO4J(EmployeeClass employee) {
        ConsoleTimer.startTimer("NEO4JDelete"); // Start timer

        ConsoleTimer.stopTimer("NEO4JDelete"); // Stop timer
    }

    /***
     * Imports records from a directory to the NEO4J database
     * @param directoryPath The path to the directory
     */
    public static void importRecordsNEO4J(String directoryPath) {
        ConsoleTimer.startTimer("NEO4JInsert"); // Start timer
        // import records from directoryPath to NEO4J database

        ConsoleTimer.stopTimer("NEO4JInsert"); // Stop timer
    }
}