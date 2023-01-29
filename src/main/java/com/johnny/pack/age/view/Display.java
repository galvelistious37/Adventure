package com.johnny.pack.age.view;

import com.johnny.pack.age.model.character.Character;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.model.location.Location;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Display {

    private final static Display INSTANCE = new Display();

    private Display(){
    }

    public static Display getDisplayInstance = INSTANCE;

    /**
     * Print a greeting to the screen
     */
    public boolean displayText(String text) {
        System.out.println(text);
        return true;
    }

    public boolean showDisplays(Character player, List<Character> enemiesFromLocation) {
        displaySpacer();
        showPlayerStatus(player);
//        displayEnemies(enemiesFromLocation);
        return true;
    }

    private void displaySpacer() {
        System.out.println("**************************************************");
        System.out.println("**************************************************");
    }

    private void showPlayerStatus(Character player) {
        System.out.println(Constant.SPACE);
        String message = "~Player Details~ " +
                "\n\tHit Points: " + player.getHitPoints() +
                "\n\tWeapon: " + player.getEquipable().weaponType();
        System.out.println(message);
    }

//    private void displayEnemies(List<Character> localEnemies){
//        System.out.println("~Enemies~");
//        for(Character enemy : localEnemies){
//            System.out.println("\t" + "[" + localEnemies.indexOf(enemy) + "] " +
//                    enemy.getName() + ": " + enemy.getHitPoints() + " HP");
//        }
//        System.out.println(" ");
//    }

    public List<String> getAcceptableNumbers(){
        return Arrays.asList("0", "1", "2", "3", "4", "99");
    }

    /**
     * Display the list of available exits.
     * @param exits - Map of available exits
     */
    public void displayAvailableExits(Map<String, Integer> exits) {
        System.out.println("Available exits are: ");
        System.out.print("\t");
        Stream<String> stream = Stream.of(exits.keySet().toString());
        stream.forEach(System.out::println);
        System.out.println();
    }
}
