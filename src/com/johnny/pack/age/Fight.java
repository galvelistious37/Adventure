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

    public void initiative(Player player, List<Enemy> enemiesFromLocation) {
        player.setInitiative(diceRoll.rollATwenty());
        for(Enemy enemy : enemiesFromLocation){
            enemy.setInitiative(diceRoll.rollATwenty());
        }
        System.out.println("Player: " + player.getInitiative());
        for(Enemy enemy : enemiesFromLocation){
            System.out.println(enemy.displayEnemy() + ": " + enemy.getInitiative());
        }
    }

    public void doFightinStuff(Player player, List<Enemy> enemiesFromLocation) {
        boolean quit = false;
        while(!quit){
            System.out.println("**************************************************");
            System.out.println(player.toString());
            for(int i = 20; i > 0; i--){
                if(player.getInitiative() == i){
                    int action = getSelection();
                    quit = tryAction(action, player, enemiesFromLocation);
                }
                for(Enemy enemy : enemiesFromLocation){
                    if(enemy.getInitiative() == i){
                        if(enemy.isAlive){
                            enemyAttack(player, enemy);
                        } else {
                            System.out.println(enemy.displayEnemy() + " is dead");
                        }
                    }
                }
            }
            System.out.println("**************************************************");
        }
    }

    private void enemyAttack(Player player, Enemy enemy) {
        int playerHitpoint = player.getHitpoints();
        int damage;
        if(diceRoll.rollATwenty() >= 18){
            System.out.println(enemy.displayEnemy() + " " + enemy.performBersekable()
                    + " you!" );
            damage = (enemy.strength * 2);
        } else if(diceRoll.rollATwenty() >= 7){
            System.out.println(enemy.displayEnemy() + " " + enemy.performAttack()
                    + " you!" );
            damage = enemy.strength;
        }else{
            System.out.println(enemy.displayEnemy() + " totally fell on it's face" );
            damage = 0;
        }
        playerHitpoint -= damage;
        player.setHitpoints(playerHitpoint);
    }

    private boolean tryAction(int action, Player player, List<Enemy> enemiesFromLocation) {
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

    private void attack(Player player, List<Enemy> enemiesFromLocation) {
        int enemyToAttack = whichEnemy(enemiesFromLocation);
        if(diceRoll.rollATwenty() > 18){
            System.out.println("You annihilated " + enemiesFromLocation.get(enemyToAttack).displayEnemy()
            + " with your " + player.getWeapon());
            enemiesFromLocation.get(enemyToAttack).hitpoints -= (player.getStrength()*2);
            if(enemiesFromLocation.get(enemyToAttack).hitpoints <= 0){
                enemiesFromLocation.get(enemyToAttack).isAlive = false;
            }
            System.out.println(enemiesFromLocation.get(enemyToAttack).displayEnemy()
                    + " has " + enemiesFromLocation.get(enemyToAttack).hitpoints + " hitpoints left");
        } else if(diceRoll.rollATwenty() >= 10){
            System.out.println("You successfully attacked " + enemiesFromLocation.get(enemyToAttack).displayEnemy()
            + " and cut it with your " + player.getWeapon());
            enemiesFromLocation.get(enemyToAttack).hitpoints -= (player.getStrength());
            if(enemiesFromLocation.get(enemyToAttack).hitpoints <= 0){
                enemiesFromLocation.get(enemyToAttack).isAlive = false;
            }
            System.out.println(enemiesFromLocation.get(enemyToAttack).displayEnemy()
                    + " has " + enemiesFromLocation.get(enemyToAttack).hitpoints + " hitpoints left");
        }else if(diceRoll.rollATwenty() >= 7){
            System.out.println("You scratched the " + enemiesFromLocation.get(enemyToAttack).displayEnemy()
                    + " and with your " + player.getWeapon());
            enemiesFromLocation.get(enemyToAttack).hitpoints -= (player.getStrength()-5);
            if(enemiesFromLocation.get(enemyToAttack).hitpoints <= 0){
                enemiesFromLocation.get(enemyToAttack).isAlive = false;
            }
            System.out.println(enemiesFromLocation.get(enemyToAttack).displayEnemy()
                    + " has " + enemiesFromLocation.get(enemyToAttack).hitpoints + " hitpoints left");
        }else if(diceRoll.rollATwenty() >= 3){
            System.out.println(enemiesFromLocation.get(enemyToAttack).displayEnemy()
                    + " blocked your " + player.getWeapon() + " attack");
            System.out.println(enemiesFromLocation.get(enemyToAttack).displayEnemy()
                    + " has " + enemiesFromLocation.get(enemyToAttack).hitpoints + " hitpoints left");
        }else{
            System.out.println("You swung at " + enemiesFromLocation.get(enemyToAttack)
                    + " and and missed... horribly");
        }
    }

    private int whichEnemy(List<Enemy> enemiesFromLocation) {
        if(enemiesFromLocation.size() > 1){
            int enemyListPosition = diceRoll.getRandomNumberFromListSize(enemiesFromLocation.size()) - 1;
            if(!enemiesFromLocation.get(enemyListPosition).isAlive){
                System.out.println("You attacked a dead one!");
            }
            return enemyListPosition;
        } else {
            if(!enemiesFromLocation.get(0).isAlive){
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
                if (fightMenuSelection > 1 || fightMenuSelection < 4) {
                    return fightMenuSelection;
                } else {
                    System.out.println("Select a valid option");
                }
            }
            System.out.println("Select a valid option");
            fightScanner.nextLine();
        }
    }
}
