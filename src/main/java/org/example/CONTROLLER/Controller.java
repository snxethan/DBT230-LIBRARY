package org.example.CONTROLLER;

import org.example.CONTROLLER.EMPLOYEE.EmployeeDatabase;
import org.example.CONTROLLER.EMPLOYEE.EmployeeFiles;
import org.example.VIEW.GUI;

public class Controller {
    public static void startApplication() {
        //TODO: ADD GUI & MENU OPTIONS.
        GUI.start();
        EmployeeFiles.readFile(); // reads any json file in upload folder.
        EmployeeDatabase.sortEmployees(); // sorts the employees by id.
        GUI.end();
    }
}
