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


public class EmployeeMongo {

    public static void connectMongoDB() {
        String connectionString = "mongodb+srv://snxethan:<password>@dbtii.5r12dl8.mongodb.net/?retryWrites=true&w=majority&appName=DBTII";
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
                e.printStackTrace();
            }
        }
    }

    //FIXME: remove serialization & txt file handling, handle to .json to mongoDB


    //region READ FILES
    /**
     * Reads the file and displays the employee data.
     */
    public static void readMongoDB() {
    }
    //endregion

    //region DELETE FILES


    /**
     * Deletes a file based on the employee's id.
     * @param id
     */
    public static void deleteEmployeeMongo(int id) {

    }
    //endregion

    //region WRITE FILES
    /**
     * Adds employee
     */
    public static void addEmployeeMongo(EmployeeClass employee, boolean wantDisplay) {

    }
    //endregion

    //region UPDATE FILE
    public static void updateEmployeeMongo(EmployeeClass employee){
        //FIXME:
    }
    //endregion
}
