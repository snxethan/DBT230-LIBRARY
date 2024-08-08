package org.example;
import org.example.CONTROLLER.ConsoleWrite;
import org.example.CONTROLLER.Controller;
import org.example.CONTROLLER.EMPLOYEE.EmployeeRedis;


public class Main {
    public static void main(String[] args) {
        if (ConsoleWrite.getIntInput("1. Import records to Redis\n2. Start application") == 1) {
            EmployeeRedis.importRecordsToRedis("src/Data/long");
        }
        Controller.startApplication();
    }
}
