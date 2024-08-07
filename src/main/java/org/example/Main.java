package org.example;
import org.example.CONTROLLER.Controller;
import org.example.CONTROLLER.EMPLOYEE.EmployeeRedis;


public class Main {
    public static void main(String[] args) {
        EmployeeRedis.redisConnect();
        EmployeeRedis.importRecordsToRedis("src/Data/long");
//        Controller.startApplication(); // starts the application
    }
}
