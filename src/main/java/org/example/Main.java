package org.example;
import org.example.CONTROLLER.ConsoleWrite;
import org.example.CONTROLLER.Controller;
import org.example.CONTROLLER.EMPLOYEE.EmployeeNEO;


public class Main {
    public static void main(String[] args) {
        if(ConsoleWrite.getIntInput("1. Import records from to NEO4J\n2. Start application\n") == 1){
            EmployeeNEO.importRecordsNEO4J("src/main/java/org/example/FILES/long/");
        }
        Controller.startApplication(); // starts the application
    }
}
