package org.example.CONTROLLER.EMPLOYEE;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import org.example.CONTROLLER.ConsoleTimer;
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
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmployeeMongo {
    //region MONGODB CONNECTION
    static String connectionString = System.getenv("MONGO_CONNECTION_STRING"); // Connection string from environment variable
    static MongoDatabase database; // MongoDB database
    static MongoCollection<Document> collection; // MongoDB collection
    static MongoClient mongoClient; // MongoDB client

    static ServerApi serverApi = ServerApi.builder() // Server API version
            .version(ServerApiVersion.V1) // Version 1
            .build();  // Build the server API
    static MongoClientSettings settings = MongoClientSettings.builder() // MongoDB client settings
            .applyConnectionString(new ConnectionString(connectionString)) // Connection string
            .serverApi(serverApi) // Server API
            .build(); // Build the settings
    //endregion

    //region MONGO CONNECTION
    /**
        * This method connects to MongoDB using the connection string provided in the environment variable MONGO_CONNECTION_STRING.
        * The database name is "DBTII" and the collection name is "simplepersistence_people".
        * If the connection is successful, the method pings the database and the collection.
        * If the connection is unsuccessful, the method displays an error message.
        * The method also starts a timer to measure the time taken to connect to MongoDB.
        * The timer is stopped after the connection is established or an error is displayed.
        * The method is called at the start of the application to connect to MongoDB.
     */
    public static void connectMongoDB() {
        ConsoleTimer.startTimer("MongoDBConnect");  // Start timer
        mongoClient = MongoClients.create(settings); // Create MongoDB client
        try {
            database = mongoClient.getDatabase("DBTII"); // Get database
            database.runCommand(new Document("ping", 1)); // Ping database
            GUI.pingMongoDB(database.getName()); // Display database name

            collection = database.getCollection("simplepersistence_people"); // Get collection
            database.runCommand(new Document("ping", 1)); // Ping collection
            GUI.pingCollection(collection.getNamespace().toString()); // Display collection name
        } catch (MongoException e) {
            GUI.error("Error connecting to MongoDB: " + e.getMessage()); // Display error message
        } finally {
            ConsoleTimer.stopTimer("MongoDBConnect"); // Stop timer
        }
    }

    /**
        * This method closes the MongoDB connection.
        * The method is called at the end of the application to close the MongoDB connection.
     */
    public static void closeMongoDB() {
        if (mongoClient != null) {
            mongoClient.close(); // Close MongoDB client
        }
    }
    //endregion

    //region MONGO CRUD OPERATIONS
    /**
     * This method adds the given EmployeeClass object to the MongoDB collection "simplepersistence_people".
     * The method starts a timer to measure the time taken to add the document to MongoDB.
     * The timer is stopped after adding the document or an error is displayed.
     * The method also displays the EmployeeClass object in the console.
     *
     * @param employee The EmployeeClass object to be added to MongoDB
     * @param wantDisplay A boolean value to display the EmployeeClass object in the console
     */
    public static void createEmployeeMongo(EmployeeClass employee, boolean wantDisplay) {
        ConsoleTimer.startTimer("MongoDBAdd"); // Start timer
        try {
            Document doc = new Document("id", employee.getId()) // Create document
                    .append("firstName", employee.getFName()) // Append fields
                    .append("lastName", employee.getLName()) // Append fields
                    .append("hireYear", employee.getHireYear()); // Append fields
            collection.insertOne(doc); // Insert document
            if (wantDisplay) {
                GUI.displayEmployee(employee); // Display EmployeeClass object
            }
        } catch (MongoException e) {
            GUI.error("Error adding Employee to MongoDB: " + e.getMessage()); // Display error message
        } finally {
            ConsoleTimer.stopTimer("MongoDBAdd"); // Stop timer
        }
    }

    /**
        * This method reads all the documents from the MongoDB collection "simplepersistence_people".
        * The documents are converted to EmployeeClass objects and added to the EmployeeDatabase.
        * The method also displays the documents in the console.
        * The method starts a timer to measure the time taken to read from MongoDB.
        * The timer is stopped after reading the documents or an error is displayed.
     */
    public static void readMongoDB(boolean wantDisplay) {
        ConsoleTimer.startTimer("MongoDBRead"); // Start timer
        try {
            addEmployeesFromMongoDB(wantDisplay); // Add Employees from MongoDB
            EmployeeDatabase.sortEmployees(); // Sort Employees
        } catch (MongoException e) {
            GUI.error("Error reading Employees from MongoDB: " + e.getMessage()); // Display error message
        } finally {
            ConsoleTimer.stopTimer("MongoDBRead"); // Stop timer
        }
    }

    public static void addEmployeesFromMongoDB(boolean wantDisplay) {
        for (Document doc : collection.find()) { // Iterate over documents
            EmployeeDatabase.addEmployeeFromMongoDB(new EmployeeClass(doc.getInteger("id"), doc.getString("firstName"), doc.getString("lastName"), doc.getInteger("hireYear")),wantDisplay);
            // Display EmployeeClass objects
        }
    }

    /**
        * This method updates the given EmployeeClass object in the MongoDB collection "simplepersistence_people".
        * The method starts a timer to measure the time taken to update the document in MongoDB.
        * The timer is stopped after updating the document or an error is displayed.
        *
        * @param employee The EmployeeClass object to be updated in MongoDB
     */
    public static void updateEmployeeMongo(EmployeeClass employee) {
        ConsoleTimer.startTimer("MongoDBUpdate"); // Start timer
        try {
            // Ensure MongoDB connection is established
            if (mongoClient == null) {
                connectMongoDB();
            }

            // Create filter to find the document by id
            Bson filter = Filters.eq("id", employee.getId());

            // Create update operations
            Bson updates = Updates.combine(
                    Updates.set("firstName", employee.getFName()),
                    Updates.set("lastName", employee.getLName()),
                    Updates.set("hireYear", employee.getHireYear())
            );

            // Execute update
            collection.updateOne(filter, updates); // Update document
        } catch (MongoException e) {
            GUI.error("Error updating Employee in MongoDB: " + e.getMessage()); // Display error message
        } finally {
            ConsoleTimer.stopTimer("MongoDBUpdate"); // Stop timer
        }
    }

    /**
     * This method deletes the given EmployeeClass object from the MongoDB collection "simplepersistence_people".
     * The method starts a timer to measure the time taken to delete the document from MongoDB.
     * The timer is stopped after deleting the document or an error is displayed.
     *
     * @param employee The EmployeeClass object to be deleted from MongoDB
     */
    public static void deleteEmployeeMongo(EmployeeClass employee) {
        ConsoleTimer.startTimer("MongoDBDelete"); // Start timer
        try {
            Bson filter = Filters.eq("id", employee.getId()); // Create filter
            collection.deleteOne(filter); // Delete document
        } catch (MongoException e) {
            GUI.error("Error deleting Employee from MongoDB: " + e.getMessage()); // Display error message
        } finally {
            ConsoleTimer.stopTimer("MongoDBDelete"); // Stop timer
        }
    }


    //endregion



    //region MONGODB INITIAL INSERT
    @Deprecated
    /*
        * This method is deprecated because it is not used in the application.
        * This method is used to insert all the .txt files in the given directory to MongoDB.
        * The format of the .txt files should be as follows:
        *  id, firstName, lastName, hireYear
        * 1, John, Doe, 2021
        *
        * @param directoryPath The path of the directory containing the .txt files
     **/
    public static void importRecordstoMongoDB(String directoryPath) {
        ConsoleTimer.startTimer("MongoDBInsert"); // Start timer
        connectMongoDB(); // Connect to MongoDB
        if (collection == null) {
            GUI.error("MongoDB collection is not initialized."); // Display error message
            return;
        }
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.txt"); // Get .txt files
            for (Path entry : stream) {  // Iterate over files
                String content = new String(Files.readAllBytes(entry)); // Read file content
                String[] lines = content.split(","); // Split content by comma
                int id = Integer.parseInt(lines[0].trim()); // Parse id
                String firstName = lines[1].trim(); // Parse firstName
                String lastName = lines[2].trim(); // Parse lastName
                int hireYear = Integer.parseInt(lines[3].trim()); // Parse hireYear

                Bson filter = Filters.eq("id", id); // Create filter
                if (collection.countDocuments(filter) == 0) { // Check if document exists
                    Document doc = new Document("id", id) // Create document
                            .append("firstName", firstName) // Append fields
                            .append("lastName", lastName) // Append fields
                            .append("hireYear", hireYear); // Append fields
                    collection.insertOne(doc); // Insert document
                    System.out.println("Inserted file: " + entry.getFileName()); // Display message
                } else {
                    System.out.println("Employee with ID " + id + " already exists. Skipping file: " + entry.getFileName()); // Display message
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage()); // Display error message
        } catch (MongoException e) {
            System.err.println("Error inserting into MongoDB: " + e.getMessage()); // Display error message
        } finally {
            closeMongoDB(); // Close MongoDB connection
            ConsoleTimer.stopTimer("MongoDBInsert"); // Stop timer
        }
    }
    //endregion
}