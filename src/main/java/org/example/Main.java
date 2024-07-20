package org.example;
import org.example.CONTROLLER.Controller;
import org.example.CONTROLLER.EMPLOYEE.EmployeeDatabase;
import org.example.CONTROLLER.EMPLOYEE.EmployeeMongo;


public class Main {
    public static void main(String[] args) {
//        EmployeeMongo.importRecordstoMongoDB("src/main/java/org/example/FILES/long/");
        Controller.startApplication(); // starts the application
    }
}
