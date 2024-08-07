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
        ConsoleTimer.startTimer("RedisDBAdd"); // Start timer

        ConsoleTimer.stopTimer("RedisDBAdd"); // Stop timer
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



    //region REDIS INITIAL INSERT
    @Deprecated
    public static void importRecordsToRedis(String directoryPath) {
        ConsoleTimer.startTimer("RedisDBInsert"); // Start timer
        ConsoleTimer.stopTimer("RedisDBInsert"); // Stop timer
    }
    //endregion
}