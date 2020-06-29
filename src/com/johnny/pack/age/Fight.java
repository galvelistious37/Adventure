package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fight {
    private static final String COLON_SEPARATOR = ": ";
    private static final String DO_FIGHTIN = "Fight";
    private static final String INTIMIDATE_THEM = "Intimidate";
    private static final String SNEAKY_LIKE = "Sneak Past";
    private static final String RUN_FORREST = "Run";
    private static final int FIGHT = 1;
    private static final int INTIMIDATE = 2;
    private static final int SNEAK_PAST = 3;
    private static final int RUN_AWAY = 4;
    private Scanner fightScanner;
    private Dice diceRoll;

    public Fight() {
        this.fightScanner = new Scanner(System.in);
        this.diceRoll = new Dice();
    }

    /**
     * Display Fight Menu options
     */
    public void getFightMenu(){
        List<String> options = createOptions();
        for(String option : options){
            System.out.println(option);
        }
    }

    /**
     * Create Fight Menu.
     * @return List of String Fight Menu options.
     */
    private List<String> createOptions() {
        List<String> generateOptions = new ArrayList<>();
        generateOptions.add(FIGHT + COLON_SEPARATOR + DO_FIGHTIN);
        generateOptions.add(INTIMIDATE + COLON_SEPARATOR + INTIMIDATE_THEM);
        generateOptions.add(SNEAK_PAST + COLON_SEPARATOR + SNEAKY_LIKE);
        generateOptions.add(RUN_AWAY + COLON_SEPARATOR + RUN_FORREST);
        return generateOptions;
    }

    public void initiative(Character player, List<Character> enemiesFromLocation) {
        player.setInitiative(diceRoll.rollATwenty());
        for(Character enemy : enemiesFromLocation){
            enemy.setInitiative(diceRoll.rollATwenty());
        }
    }

    public void doFightinStuff(Character player, List<Character> enemiesFromLocation) {
        boolean quit = false;
        int round = 0;
        while(!quit){
            round++;
            displayRound(round);
            showCharacterStatus(player);

            for(int i = 20; i > 0; i--){
                if(player.getInitiative() == i){
                    int action = getSelection();
                    quit = tryAction(action, player, enemiesFromLocation);
                }
                if(quit){
                   break;
                }
                for(Character enemy : enemiesFromLocation){
                    if(enemy.getInitiative() == i){
                        if(enemy.getIsAlive()){
                            enemyAttack(player, enemy);
                        } else {
                            System.out.println(enemy.getName() + " is dead");
                        }
                    }
                }
            }
            if(countTheDead(enemiesFromLocation)){
                quit = true;
            }
        }
    }


    private void displayRound(int round) {
        System.out.println("**************************************************");
        System.out.println("                  Round " + round);
        System.out.println("**************************************************");
    }

    private void showCharacterStatus(Character character) {
        System.out.println(character.toString());
    }

    private boolean countTheDead(List<Character> enemiesFromLocation) {
        int bodies = 0;
        for(Character enemy : enemiesFromLocation){
            if(!enemy.getIsAlive()){
                bodies++;
            }
            if(bodies == enemiesFromLocation.size()){
                System.out.println("You have painted these lands with blood of your enemies");
                return true;
            }
        }
        return false;
    }

    private void enemyAttack(Character player, Character enemy) {
        int playerHitpoint = player.getHitpoints();
        int roll = diceRoll.rollATwenty();
        int damage = enemyDealsDamage(roll, enemy);
        playerHitpoint -= damage;
        player.setHitpoints(playerHitpoint);
        if(player.getHitpoints() <= 0){
            player.setIsAlive(false);
            System.out.println(enemy.getName() + " killed you");
            System.out.println("Game Over");
            pressEnterKeyToContinue();
            GamePlay gamePlay = new GamePlay();
            gamePlay.quit();

        } else {
            System.out.println("You have " + player.getHitpoints() + " remaining");
        }
    }

    public void pressEnterKeyToContinue()
    {
        System.out.println("Press Enter key to quit...");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    private int enemyDealsDamage(int roll, Character enemy) {
        int damage;
        if(roll >= 18){
            damage = enemy.getStrength() + 4;
            System.out.println(enemy.getName() + " " + enemy.performBersek()
                    + " you for " + damage + " points of damage" );
        } else if(roll >= 7){
            damage = enemy.getStrength();
            System.out.println(enemy.getName() + " " + enemy.performAttack()
                    + " you for " + damage + " points of damage" );
        }else{
            damage = 0;
            System.out.println(enemy.getName() + " totally fell on it's face" );
        }
        return damage;
    }

    private boolean tryAction(int action, Character player, List<Character> enemiesFromLocation) {
        switch(action){
            case 1:
                attack(player, enemiesFromLocation);
                return false;
            case 2:
//                intimidate(player, enemiesFromLocation);
                return false;
            case 3:
//                sneak();
                return false;
            case 4:
                runAway();
                return true;
        }
        return false;
    }

    private void runAway() {
        System.out.println("You run away like some kind of wuss");
    }

    private void attack(Character player, List<Character> enemiesFromLocation) {
        boolean quit = false;
        int enemyToAttack = 0;
        while(!quit){
            enemyToAttack = whichEnemy(enemiesFromLocation);
            if(enemyToAttack != -1){
                quit = true;
            } else {
                System.out.println("Enter an enemy name!");
            }
        }
        Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
        int enemyHitpoints  = currentEnemy.getHitpoints();

        if(diceRoll.rollATwenty() >= 18){
            System.out.println("You " + player.performBersek() + " " + currentEnemy.getName()
            + " with your " + player.weaponType());
            currentEnemy.setHitpoints(enemyHitpoints - (player.getStrength()*2));
            if(currentEnemy.getHitpoints() <= 0){
                System.out.println("You killed " + currentEnemy.getName());
                currentEnemy.setIsAlive(false);
            } else {
                System.out.println(enemiesFromLocation.get(enemyToAttack).getName()
                        + " has " + currentEnemy.getHitpoints() + " hitpoints left");
            }
        } else if(diceRoll.rollATwenty() >= 8){
            System.out.println("You successfully " + player.performAttack() + " " + currentEnemy.getName()
            + " with your " + player.weaponType());
            currentEnemy.setHitpoints(enemyHitpoints - player.getStrength());
            if(currentEnemy.getHitpoints() <= 0){
                System.out.println("You killed " + currentEnemy.getName());
                currentEnemy.setIsAlive(false);
            } else {
                System.out.println(enemiesFromLocation.get(enemyToAttack).getName()
                        + " has " + currentEnemy.getHitpoints() + " hitpoints left");
            }
        }else if(diceRoll.rollATwenty() >= 3){
            System.out.println("You scratched the " + currentEnemy.getName()
                    + " with your " + player.weaponType());
            currentEnemy.setHitpoints(enemyHitpoints - (player.getStrength()-2));
            if(currentEnemy.getHitpoints() <= 0){
                System.out.println("You killed " + currentEnemy.getName());
                currentEnemy.setIsAlive(false);
            } else {
                System.out.println(enemiesFromLocation.get(enemyToAttack).getName()
                        + " has " + currentEnemy.getHitpoints() + " hitpoints left");
            }
        }else{
            System.out.println("You swung at " + currentEnemy.getName()
                    + " and and missed... horribly");
        }
        System.out.println("");
    }

    private int whichEnemy(List<Character> enemiesFromLocation) {
        Scanner enemySelection = new Scanner(System.in);
        boolean quit = false;
        int index = -1;
        if(enemiesFromLocation.size() == 1){
            index = 0;
        } else {
            while(!quit){
                System.out.println("Which enemy are you attacking?");
                String enemyName = enemySelection.nextLine();
                for (Character enemy : enemiesFromLocation){
                    if(enemy.getName().equalsIgnoreCase(enemyName)){
                            if(!enemy.getIsAlive()){
                                System.out.println("Dude, that one is already dead. Sicko!");
                            } else {
                                quit = true;
                            }
                        index = enemiesFromLocation.indexOf(enemy);
                    }
                }
            }
        }
        return index;
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
