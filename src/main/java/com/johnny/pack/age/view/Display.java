package com.johnny.pack.age.view;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.constant.Constant;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Display {

    private final static Display INSTANCE = new Display();
    public static final String FORMAT_ENEMY_DISPLAY = "\t[%d] %s: %d HP";
    public static final String FORMAT_PLAYER_DISPLAY = "\n~Player Details~\n\tHit Points: %d\n\tWeapon: %s";

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
        displayEnemies(enemiesFromLocation);
        return true;
    }

    private void displaySpacer() {
        System.out.println("**************************************************");
        System.out.println("**************************************************");
    }

    private void showPlayerStatus(Character player) {
        System.out.println(
                String.format(FORMAT_PLAYER_DISPLAY, player.getHitPoints(), player.getEquipable().weaponType()));
    }

    private void displayEnemies(List<Character> localEnemies){
        System.out.println("~Enemies~");
        localEnemies.forEach((e) -> System.out.println(
                String.format(FORMAT_ENEMY_DISPLAY, localEnemies.indexOf(e), e.getName(), e.getHitPoints())));
        System.out.println("\n");
    }

    public List<String> getAcceptableNumbers(){
        return Arrays.asList("0", "1", "2", "3", "4", "99");
    }

    /**
     * Display the list of available exits.
     * @param exits - Map of available exits
     */
    public boolean displayAvailableExits(Map<String, Integer> exits) {
        System.out.println("Available exits are: ");
        System.out.print("\t");
        Stream<String> stream = Stream.of(exits.keySet().toString());
        stream.forEach(System.out::println);
        System.out.println();
        return true;
    }
}
