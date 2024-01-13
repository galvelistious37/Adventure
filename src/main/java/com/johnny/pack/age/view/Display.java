package com.johnny.pack.age.view;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.constant.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Display {

    private final static Display INSTANCE = new Display();
    public static final String FORMAT_DISPLAY = "\n~%s~\n\tHit Points: %d\n\tWeapon: %s";

    private Display(){
    }

    public static Display getDisplayInstance = INSTANCE;

    /**
     * Print a greeting to the screen
     */
    public void displayText(String text) {
        System.out.println(text);
    }

    public void showDisplays(Character player, List<Character> enemiesFromLocation) {
        displaySpacer();
        showPlayerStatus(player);
        displayEnemies(enemiesFromLocation);
    }

    private void displaySpacer() {
        System.out.println("**************************************************");
        System.out.println("**************************************************");
    }

    private void showPlayerStatus(Character player) {
        String output = String.format(
                FORMAT_DISPLAY,
                    player.getName(),
                    player.getHitPoints(),
                    player.getEquipable().weaponType());

        System.out.println(output);
    }

    private void displayEnemies(List<Character> localEnemies){
        localEnemies.forEach(e -> System.out.printf(
                FORMAT_DISPLAY,
                "[" + localEnemies.indexOf(e) + "] " + e.getName(),
                e.getHitPoints(),
                e.getEquipable().weaponType()));

        System.out.println("\n");
    }

    public List<String> getAcceptableNumbers(){
        return Arrays.asList("0", "1", "2", "3", "4", "99");
    }

    /**
     * Display the list of available exits.
     * @param exits - Map of available exits
     */
    public boolean showExits(Map<String, Integer> exits) {
        System.out.println("Available exits are: ");
        System.out.print("\t");
        Stream<String> stream = Stream.of(exits.keySet().toString());
        stream.forEach(System.out::println);
        System.out.println();
        return true;
    }

    /**
     * Display Fight Menu options
     */
    public void getFightMenu(){
        List<String> menu = new ArrayList<>();
        menu.add(Constant.FIGHT + Constant.COLON_SEPARATOR + Constant.DO_FIGHTIN);
        menu.add(Constant.INTIMIDATE + Constant.COLON_SEPARATOR + Constant.INTIMIDATE_THEM);
        menu.add(Constant.SNEAK_PAST + Constant.COLON_SEPARATOR + Constant.SNEAKY_LIKE);
        menu.add(Constant.RUN_AWAY + Constant.COLON_SEPARATOR + Constant.RUN_FORREST);
        menu.forEach(System.out::println);
    }
}
