package org.example.CONTROLLER.EMPLOYEE;

import org.example.MODEL.EmployeeClass;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.VIEW.GUI;


public class EmployeeMongo {

    public static void connectMongoDB() {
        String connectionString = "mongodb+srv://snxethan:<MshgTUP3qnxFXIQ7>@dbtii.5r12dl8.mongodb.net/?retryWrites=true&w=majority&appName=DBTII";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                GUI.error("Error connecting to MongoDB: " + e.getMessage());
            }
        }
    }


    //region READ FILES
    /**
     * Reads the file and displays the employee data.
     */
    public static void readMongoDB() {
        //TODO: read the mongo database and update all employees from a .json object and add to local database
    }
    //endregion

    //region DELETE FILES


    /**
     * Deletes a file based on the employee's id.
     */
    public static void deleteEmployeeMongo(EmployeeClass employee) {
        //TODO: delete employee from mongo database by id

    }
    //endregion

    //region WRITE FILES
    /**
     * Adds employee
     */
    public static void addEmployeeMongo(EmployeeClass employee, boolean wantDisplay) {
        //TODO: add employee to mongo database, want display will display GUI elements

    }
    //endregion

    //region UPDATE FILE
    public static void updateEmployeeMongo(EmployeeClass employee){
        //TODO: update a specific employee based off of the employee id
    }
    //endregion
}
