package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.MODEL.EmployeeClass;
import org.example.VIEW.GUI;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class EmployeeNEO {
    private static Driver driver;


    /***
     * Connects to the NEO4J database
     */
    public static void connectNEO4J(){
        final String dbUri = "neo4j://localhost:7687";
        final String dbUser = "neo4j";
        final String dbPassword = "password";
        Config config = Config.builder()
                .withMaxConnectionPoolSize(50)
                .withConnectionAcquisitionTimeout(60, TimeUnit.SECONDS)
                .withMaxTransactionRetryTime(15, TimeUnit.SECONDS)
                .build();

        Driver _driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword), config);
        try  {
            _driver.verifyConnectivity();
            GUI.displayMessage("Connected to NEO4J database");
        } catch (Exception e) {
            GUI.error("Error connecting to NEO4J database: " + e);
        }
        driver = _driver;
    }

    /***
     * Closes the connection to the NEO4J database
     * Closes the driver if it is not null
     */
    public static void closeNEO4J() {
        ConsoleTimer.startTimer("NEO4JClose"); // Start timer
        if (driver != null) {
            driver.close();
        }
        ConsoleTimer.stopTimer("NEO4JClose"); // Stop timer
    }

    public static void ensureConnection(){
        if(driver == null){
            connectNEO4J();
        }
    }

    /***
     * Creates an employee in the NEO4J database
     * @param employee The employee to be created
     * @param wantDisplay If true, display the employee
     */
    public static void createEmployeeNEO4J(EmployeeClass employee, boolean wantDisplay) {
        ConsoleTimer.startTimer("NEO4JInsert"); // Start timer
        ensureConnection();
        // create employee in NEO4J
        try (Session session = driver.session(SessionConfig.builder().withDatabase("neo4j").build())) {
            String createPersonQuery = "CREATE (p:Person {ID: $id, firstName: $firstName, lastName: $lastName, hireYear: $hireYear})";
            session.run(createPersonQuery, Values.parameters("id", employee.getId(),
                    "firstName", employee.getFName(), // Corrected parameter name
                    "lastName", employee.getLName(),  // Corrected parameter name
                    "hireYear", employee.getHireYear()));
            if (wantDisplay) {
                GUI.arrayEmployee(employee);
            }
        } catch (Exception e) {
            GUI.error("Error creating employee in NEO4J: " + e);
        }
        ConsoleTimer.stopTimer("NEO4JInsert"); // Stop timer
    }

    /***
     * Reads the NEO4J database, retrieves all employees
     * @param wantDisplay If true, display the employee
     */
    public static void readNEO4J(boolean wantDisplay) {
        ConsoleTimer.startTimer("NEO4JRead"); // Start timer
        ensureConnection();
        // read all employees from NEO4J
        try (Session session = driver.session(SessionConfig.builder().withDatabase("neo4j").build())) {
            String readPersonQuery = "MATCH (p:Person) RETURN p.ID as ID, p.firstName as firstName, p.lastName as lastName, p.hireYear as hireYear";
            Result result = session.run(readPersonQuery);
            while (result.hasNext()) {
                Record record = result.next();
                EmployeeClass employee = new EmployeeClass(record.get("ID").asInt(), record.get("firstName").asString(), record.get("lastName").asString(), record.get("hireYear").asInt());
                if (wantDisplay) {
                    GUI.arrayEmployee(employee);
                }
                // add employee to the list
                EmployeeDatabase.addEmployeeFromNEO4J(employee, true);
            }
        } catch (Exception e) {
            GUI.error("Error reading employees from NEO4J: " + e);
        }
        ConsoleTimer.stopTimer("NEO4JRead"); // Stop timer

    }

    /***
     * Updates an employee in the NEO4J database
     * @param employee The employee to be updated
     */
    public static void updateEmployeeNEO4J(EmployeeClass employee) {
        ConsoleTimer.startTimer("NEO4JUpdate"); // Start timer
        ensureConnection();
        // update employee in NEO4J
        try (Session session = driver.session(SessionConfig.builder().withDatabase("neo4j").build())) {
            String updatePersonQuery = "MATCH (p:Person {ID: $id}) SET p.firstName = $firstName, p.lastName = $lastName, p.hireYear = $hireYear";
            session.run(updatePersonQuery, Values.parameters("id", employee.getId(),
                    "fName", employee.getFName(),
                    "lName", employee.getLName(),
                    "hireYear", employee.getHireYear()));
        } catch (Exception e) {
            GUI.error("Error updating employee in NEO4J: " + e);
        }
        ConsoleTimer.stopTimer("NEO4JUpdate"); // Stop timer
    }

    /***
     * Deletes an employee in the NEO4J database
     * @param employee The employee to be deleted
     */
    public static void deleteEmployeeNEO4J(EmployeeClass employee) {
        ConsoleTimer.startTimer("NEO4JDelete"); // Start timer
        ensureConnection();
        // delete employee in NEO4J
        try (Session session = driver.session(SessionConfig.builder().withDatabase("neo4j").build())) {
            String deletePersonQuery = "MATCH (p:Person {ID: $id}) DELETE p";
            session.run(deletePersonQuery, Values.parameters("id", employee.getId()));
        } catch (Exception e) {
            GUI.error("Error deleting employee in NEO4J: " + e);
        }
        ConsoleTimer.stopTimer("NEO4JDelete"); // Stop timer
    }

    /***
     * Imports records from a directory to the NEO4J database
     * @param directoryPath The path to the directory
     */
    public static void importRecordsNEO4J(String directoryPath) {
        ConsoleTimer.startTimer("NEO4JInsert"); // Start timer
        ensureConnection();
        // import records from Data/long (loop through .txt files) and add to NEO4J
        try {
            File dir = new File(directoryPath);
            File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (File file : files) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(", ");
                        EmployeeClass employee = new EmployeeClass(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]));
                        createEmployeeNEO4J(employee, true);
                    }
                }
            }
            setUpRelationships("src/Data/friendships.csv");
            setUpRelationships("src/Data/reportsTo.csv");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (Exception e) {
            System.out.println("Error importing records to NEO4J: " + e);
        }
        ConsoleTimer.stopTimer("NEO4JInsert"); // Stop timer
    }


    public static void setUpRelationships(String filepath) {
        ConsoleTimer.startTimer("NEO4JRelationships"); // Start timer
        if (filepath == null) {
            GUI.error("File path is null");
            return;
        }
        ensureConnection();
        // builds session to allow querying onto the database
        try (Session session = driver.session(SessionConfig.builder().withDatabase("neo4j").build())) {
            BufferedReader reader = new BufferedReader(new FileReader(filepath)); // reader to read info
            reader.readLine(); // reads the header so they are not used

            // while loop that reads file, parses to int, then creates the query to create the relationship
            while (reader.ready()) {
                String line = reader.readLine();
                int pid;
                int friendshipID;

                // need to parse ID to int because it won't query as a string
                try {
                    GUI.displayMessage("Processing line: " + line); // Debugging information
                    pid = Integer.parseInt(line.split(",")[0]);
                    friendshipID = Integer.parseInt(line.split(",")[1]);
                    GUI.displayMessage("ID1: " + pid + " ID2: " + friendshipID); // Debugging information
                } catch (NumberFormatException e) {
                    GUI.error("Error parsing ID to int: " + e);
                    continue;
                }

                // build the query
                StringBuilder relationshipQuery = new StringBuilder();
                relationshipQuery.append("MATCH (a:Person {ID:$id1}) ");
                relationshipQuery.append("MATCH (b:Person {ID:$id2}) ");
                relationshipQuery.append("MERGE (a)-[:FRIENDS_WITH]->(b)");

                // run the query, adding in the ids to their preselected spot
                session.run(relationshipQuery.toString(),
                        Values.parameters("id1", pid, "id2", friendshipID));
            }
        } catch (IOException e) {
            GUI.error("Error setting up relationships in NEO4J: " + e);
        }
        ConsoleTimer.stopTimer("NEO4JRelationships"); // Stop timer
    }
}