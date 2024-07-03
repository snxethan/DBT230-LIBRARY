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
        timers.put(name, System.nanoTime());
    }

    /**
     * Stops the timer and logs the elapsed time.
     * If the timer does not exist, it will print an error message.
     * @param name the name of the timer
     */
    public static synchronized void stopTimer(String name) {
        Long startTime = timers.remove(name);
        String timeType = "Nano";
        if (startTime != null) {
            long elapsed = System.nanoTime() - startTime;

            if (elapsed > 1_000_000){
                elapsed /= 1_000_000;// nano to milliseconds
                timeType = "MS";

                if (elapsed > 1_000){
                    elapsed /= 1_000;//milliseconds to seconds
                    timeType = "Sec";

                    if (elapsed > 60){
                        elapsed /= 60;//second to minutes
                        timeType = "Min";

                        if (elapsed > 60){
                            elapsed /= 60;//minutes to hours
                            timeType = "Hr";

                            if (elapsed > 24){
                                elapsed /= 24;//hours to days
                                timeType = "Days";
                            }
                        }
                    }
                }
            }
            GUI.timerOut(name, elapsed, timeType);
        } else {
            GUI.timerFailed(name);
        }
    }
}
