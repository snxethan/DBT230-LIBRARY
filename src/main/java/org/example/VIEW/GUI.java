package org.example.VIEW;

public class GUI {

    public static void start() {
        System.out.println("\nSimple Persistence Started ----------------- \n");
    }
    public static void end() {
        System.out.println("Simple Persistence Ended ----------------- \n");
    }

    public static void mainMenuGUI() {
        System.out.println("----- SIMPLE PERSISTENCE MENU -----");
        System.out.println(" 1. Display"
                        + "\n 2. Add"
                        + "\n 3. Delete"
                        + "\n 4. Update"
                        + "\n 5. Exit");
    }
    public static void displaysMenu(){
        System.out.println("----- DISPLAY MENU -----");
        System.out.println(" 1. Display All"
                        + "\n 2. Search"
                        + "\n 3. Back");
    }
}
