package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fight {
    private static final String COLON_SEPARATOR = ": ";
    private static final String DO_FIGHTIN = "Fight";
    private static final String TAUNT_THEM = "Taunt";
    private static final String SNEAKY_LIKE = "Sneak Past";
    private static final String RUN_FORREST = "Run";
    private static final int FIGHT = 1;
    private static final int TAUNT = 2;
    private static final int SNEAK_PAST = 3;
    private static final int RUN_AWAY = 4;
    private Scanner fightScanner;
    private Dice diceRoll;

    public Fight() {
        this.fightScanner = new Scanner(System.in);
        this.diceRoll = new Dice();
    }

    /**
     * Calls a method to generate options. Display options
     */
    public void getFightMenu(){
        List<String> options = createOptions();
        for(String option : options){
            System.out.println(option);
        }
    }

    /**
     * Create the options for the Fight Menu.
     * @return List of String options.
     */
    private List<String> createOptions() {
        List<String> generateOptions = new ArrayList<>();
        generateOptions.add(FIGHT + COLON_SEPARATOR + DO_FIGHTIN);
        generateOptions.add(TAUNT + COLON_SEPARATOR + TAUNT_THEM);
        generateOptions.add(SNEAK_PAST + COLON_SEPARATOR + SNEAKY_LIKE);
        generateOptions.add(RUN_AWAY + COLON_SEPARATOR + RUN_FORREST);
        return generateOptions;
    }

    public void initiative(Character player, List<Character> enemiesFromLocation) {
        player.setInitiative(diceRoll.rollATwenty());
        for(Character enemy : enemiesFromLocation){
            enemy.setInitiative(diceRoll.rollATwenty());
        }
        System.out.println(player.displayCharacter() + ": " + player.getInitiative());
        for(Character enemy : enemiesFromLocation){
            System.out.println(enemy.displayCharacter() + ": " + enemy.getInitiative());
        }
    }

    public void doFightinStuff(Character player, List<Character> enemiesFromLocation) {
        boolean quit = false;
        while(!quit){
            System.out.println("**************************************************");
            System.out.println(player.toString());
            for(int i = 20; i > 0; i--){
                if(player.getInitiative() == i){
                    int action = getSelection();
                    quit = tryAction(action, player, enemiesFromLocation);
                }
                for(Character enemy : enemiesFromLocation){
                    if(enemy.getInitiative() == i){
                        if(enemy.getIsAlive()){
                            enemyAttack(player, enemy);
                        } else {
                            System.out.println(enemy.displayCharacter() + " is dead");
                        }
                    }
                }
            }
            System.out.println("**************************************************");
        }
    }

    private void enemyAttack(Character player, Character enemy) {
        int playerHitpoint = player.getHitpoints();
        int damage;
        if(diceRoll.rollATwenty() >= 18){
            System.out.println(enemy.displayCharacter() + " " + enemy.performBersek()
                    + " you!" );
            damage = (enemy.getStrength() * 2);
        } else if(diceRoll.rollATwenty() >= 7){
            System.out.println(enemy.displayCharacter() + " " + enemy.performAttack()
                    + " you!" );
            damage = enemy.getStrength();
        }else{
            System.out.println(enemy.displayCharacter() + " totally fell on it's face" );
            damage = 0;
        }
        playerHitpoint -= damage;
        player.setHitpoints(playerHitpoint);
    }

    private boolean tryAction(int action, Character player, List<Character> enemiesFromLocation) {
        switch(action){
            case 1:
                attack(player, enemiesFromLocation);
                return false;
            case 2:
//                taunt(player, enemiesFromLocation);
                return false;
            case 3:
//                sneak();
                return false;
            case 4:
                runAway();
                return  true;
        }
        return false;
    }

    private void runAway() {
        System.out.println("You run away like some kind of wuss");
    }

    private void attack(Character player, List<Character> enemiesFromLocation) {
        int enemyToAttack = whichEnemy(enemiesFromLocation);
        Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
        int enemyHitpoints  = currentEnemy.getHitpoints();

        if(diceRoll.rollATwenty() > 18){
            System.out.println("You " + player.performBersek() + " " + currentEnemy.displayCharacter()
            + " with your " + player.weaponType());
            currentEnemy.setHitpoints(enemyHitpoints - (player.getStrength()*2));
            if(currentEnemy.getHitpoints() <= 0){
                currentEnemy.setIsAlive(false);
            }
            System.out.println(enemiesFromLocation.get(enemyToAttack).displayCharacter()
                    + " has " + currentEnemy.getHitpoints() + " hitpoints left");
        } else if(diceRoll.rollATwenty() >= 10){
            System.out.println("You successfully " + player.performAttack() + " " + currentEnemy.displayCharacter()
            + " with your " + player.weaponType());
            currentEnemy.setHitpoints(enemyHitpoints - player.getStrength());
            if(currentEnemy.getHitpoints() <= 0){
                currentEnemy.setIsAlive(false);
            }
            System.out.println(currentEnemy.displayCharacter()
                    + " has " + currentEnemy.getHitpoints() + " hitpoints left");
        }else if(diceRoll.rollATwenty() >= 5){
            System.out.println("You scratched the " + currentEnemy.displayCharacter()
                    + " with your " + player.weaponType());
            currentEnemy.setHitpoints(enemyHitpoints - (player.getStrength()-2));
            if(currentEnemy.getHitpoints() <= 0){
                currentEnemy.setIsAlive(false);
            }
            System.out.println(currentEnemy.displayCharacter()
                    + " has " + currentEnemy.getHitpoints() + " hitpoints left");
        }else if(diceRoll.rollATwenty() >= 3){
            System.out.println(currentEnemy.displayCharacter()
                    + " blocked your " + player.weaponType() + " attack");
            System.out.println(currentEnemy.displayCharacter()
                    + " has " + currentEnemy.getHitpoints() + " hitpoints left");
        }else{
            System.out.println("You swung at " + currentEnemy.displayCharacter()
                    + " and and missed... horribly");
        }
    }

    private int whichEnemy(List<Character> enemiesFromLocation) {
        if(enemiesFromLocation.size() > 1){
            int enemyListPosition = diceRoll.getRandomNumberFromListSize(enemiesFromLocation.size()) - 1;
            if(!enemiesFromLocation.get(enemyListPosition).getIsAlive()){
                System.out.println("You attacked a dead one!");
            }
            return enemyListPosition;
        } else {
            if(!enemiesFromLocation.get(0).getIsAlive()){
                System.out.println("Dude.... it's dead");
            }
            return 0;
        }
    }

    private int getSelection() {
        while(true) {
            getFightMenu();
            if (fightScanner.hasNextInt()) {
                int fightMenuSelection = fightScanner.nextInt();
                if (fightMenuSelection >= 1 && fightMenuSelection <= 4) {
                    return fightMenuSelection;
                }
            }
            System.out.println("Select a valid option");
            fightScanner.nextLine();
        }
    }
}
