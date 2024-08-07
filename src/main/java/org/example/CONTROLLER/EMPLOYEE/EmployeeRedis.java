package org.example.CONTROLLER.EMPLOYEE;

import org.example.CONTROLLER.ConsoleTimer;
import org.example.MODEL.EmployeeClass;

public class EmployeeRedis {

    //region REDIS CONNECTION
    public static void redisConnect() {
        ConsoleTimer.startTimer("RedisDBConnect");  // Start timer


        ConsoleTimer.stopTimer("RedisDBConnect"); // Stop timer
    }


    public static void redisClose() {

    }
    //endregion

    //region REDIS CRUD OPERATIONS

    public static void redisCreateEmployee(EmployeeClass employee, boolean wantDisplay) {
        ConsoleTimer.startTimer("RedisDBAdd"); // Start timer

        ConsoleTimer.stopTimer("RedisDBAdd"); // Stop timer

    }

    public static void redisReadDB(boolean wantDisplay) {
        ConsoleTimer.startTimer("RedisDBRead"); // Start timer

        ConsoleTimer.stopTimer("RedisDBRead"); // Stop timer
    }

    public static void redisAddEmployeesFromDB(boolean wantDisplay) {

    }

    public static void redisUpdateEmployee(EmployeeClass employee) {
        ConsoleTimer.startTimer("RedisDBUpdate"); // Start timer

        ConsoleTimer.stopTimer("RedisDBUpdate"); // Stop timer

    }

    public static void redisDeleteEmployee(EmployeeClass employee) {
        ConsoleTimer.startTimer("RedisDBDelete"); // Start timer

        ConsoleTimer.stopTimer("RedisDBDelete"); // Stop timer
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
    public static void importRecordsToRedis(String directoryPath) {
        ConsoleTimer.startTimer("RedisDBInsert"); // Start timer
        ConsoleTimer.stopTimer("RedisDBInsert"); // Stop timer
    }
    //endregion
}