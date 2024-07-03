package org.example.CONTROLLER;

import org.example.VIEW.GUI;

import java.util.HashMap;
import java.util.Map;

public class ConsoleTimer {
    private static final Map<String, Long> timers = new HashMap<>();

    /**
     * Starts a timer with the given name.
     * If a timer with the same name already exists, it will be overwritten.
     * If the name is null, it will print an error message.
     * @param name the name of the timer
     */
    public static synchronized void startTimer(String name) {
        timers.put(name, System.currentTimeMillis());
    }

    /**
     * Stops the timer and logs the elapsed time.
     * If the timer does not exist, it will print an error message.
     * @param name the name of the timer
     */
    public static synchronized void stopTimer(String name) {
        Long startTime = timers.remove(name);
        if (startTime != null) {
            long elapsed = System.currentTimeMillis() - startTime;
            GUI.timerMS(name, elapsed);
        } else {
            GUI.timerFailed(name);
        }
    }
}
