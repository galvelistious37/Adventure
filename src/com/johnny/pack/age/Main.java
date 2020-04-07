package com.johnny.pack.age;

import java.io.*;
import java.util.*;

public class Main {
    private static final int NEW = 1;
    private static final int LOAD = 2;
    private static final int QUIT = 3;

    public static void main(String[] args) {
	    play();

//        String[] road = "You are standing at the end of a road before a small brick building".split(" ");
//        for(String i : road){
//            System.out.println(i);
//        }
//
//        System.out.println("===============================================================");
//
//        String[] building = "You are inside a building, a well house for a spring".split(", ");
//        for(String i : building){
//            System.out.println(i);
//        }
    }

    private static void play() {
        System.out.println(greet());
        System.out.println(playOptions());
        continuePlay();
//        List<String> details = scenario(player);
//        System.out.println(details.get(0));
//        System.out.println(details.get(1));
//        System.out.println(details.get(2));

    }

    /**
     * Print a greeting
     * @return a String greeting
     */
    private static String greet() {
        return "Make your selection:";
    }

    /**
     * Display the opening menu options
     * @return a String of options
     */
    private static String playOptions() {
        return "1 - New Game \n2 - Load Saved Game \n3 - Quit";
    }

    /**
     * Take player input selection for opening menu options
     */
    private static void continuePlay() {
        GamePlay gamePlay = new GamePlay();
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(scanner.hasNextInt()){
                int value = scanner.nextInt();
                if(value == NEW){
                    gamePlay.playNewGame();
                    break;
                } else if (value == LOAD){
                    gamePlay.loadGame();
                    break;
                } else if (value == QUIT){
                    quit();
                    break;
                } else {
                    System.out.println("Select a valid option");
                }
            } else {
                System.out.println("Select a valid option");
            }
            scanner.nextLine();
        }
    }

    public static void quit() {
        System.out.println("Shutting down...");
        System.exit(0);
    }

//    private static void saveObject(ISaveable objectToSave) {
//        for(int i = 0; i < objectToSave.write().size(); i++){
//            System.out.println("Saving " + objectToSave.write().get(i));
//        }
//    }


    /**
     * Print scenario details
     * @param player object
     * @return a list of strings detailing the scenario
     */
    private static List<String> scenario(Player player) {
        List<String> playerDetails = new ArrayList<String>();
        playerDetails.add("Your name is " + player.getName());
        playerDetails.add("You have " + player.getHitpoints() + " points of health");
        playerDetails.add("You can deal up to " + player.getStrength() + " points of " +
                "damage with your " + player.getWeapon().toLowerCase());
        return playerDetails;
    }
}
