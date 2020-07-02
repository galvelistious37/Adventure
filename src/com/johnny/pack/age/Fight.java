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
        System.out.println("");
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
        if(player.getInitiative() == 0){
            player.setInitiative(diceRoll.rollATwenty());
        }
        for(Character enemy : enemiesFromLocation){
            if (enemy.getInitiative() == 0) {
                enemy.setInitiative(diceRoll.rollATwenty());
            }
        }
    }

    public void doFightinStuff(Character player, List<Character> enemiesFromLocation) {
        GamePlay gamePlay = new GamePlay();
        boolean quit = false;
        while(!quit){
            displaySpacer();
            showCharacterStatus(player);
            gamePlay.displayEnemies(enemiesFromLocation);

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
                System.out.println("You have painted these lands with blood of your enemies");
                quit = true;
            }
        }
    }


    private void displaySpacer() {
        System.out.println("");
        System.out.println("**************************************************");
        System.out.println("**************************************************");
    }

    private void showCharacterStatus(Character character) {
        GamePlay gamePlay = new GamePlay();
        gamePlay.showCharacterStatus(character);
    }

    protected boolean countTheDead(List<Character> enemiesFromLocation) {
        int bodies = 0;
        for(Character enemy : enemiesFromLocation){
            if(!enemy.getIsAlive()){
                bodies++;
            }
            if(bodies == enemiesFromLocation.size()){
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
            System.out.println("\tYou have " + player.getHitpoints() + " HP remaining");
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
        enemyToAttack = whichEnemy(enemiesFromLocation);
        if(enemyToAttack != -1){
            Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
            int enemyHitpoints  = currentEnemy.getHitpoints();

            if(diceRoll.rollATwenty() >= 18){
                System.out.println("You " + player.performBersek() + " " + currentEnemy.getName()
                + " with your " + player.weaponType());
                currentEnemy.setHitpoints(enemyHitpoints - (player.getStrength()*2));
                if(currentEnemy.getHitpoints() <= 0){
                    currentEnemy.setHitpoints(0);
                    System.out.println("You killed " + currentEnemy.getName());
                    currentEnemy.setIsAlive(false);
                } else {
                    System.out.println(enemiesFromLocation.get(enemyToAttack).getName()
                            + " has " + currentEnemy.getHitpoints() + " hitpoints left");
                }
            } else if(diceRoll.rollATwenty() >= 7){
                System.out.println("You successfully " + player.performAttack() + " " + currentEnemy.getName()
                + " with your " + player.weaponType());
                currentEnemy.setHitpoints(enemyHitpoints - player.getStrength());
                if(currentEnemy.getHitpoints() <= 0){
                    currentEnemy.setHitpoints(0);
                    System.out.println("You killed " + currentEnemy.getName());
                    currentEnemy.setIsAlive(false);
                } else {
                    System.out.println(enemiesFromLocation.get(enemyToAttack).getName()
                            + " has " + currentEnemy.getHitpoints() + " hitpoints left");
                }
            }else if(diceRoll.rollATwenty() >= 3){
                System.out.println("You scratched " + currentEnemy.getName()
                        + " with your " + player.weaponType());
                currentEnemy.setHitpoints(enemyHitpoints - (player.getStrength()-2));
                if(currentEnemy.getHitpoints() <= 0){
                    currentEnemy.setHitpoints(0);
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
        } else {
            System.out.println("You have chosen not to attack");
        }
    }

    private int whichEnemy(List<Character> enemiesFromLocation) {
        Scanner enemySelection = new Scanner(System.in);
        boolean quit = false;
        int index = -1;
        if(enemiesFromLocation.size() == 1){
            index = 0;
        } else {
            int enemyName;
            while(!quit){
                Character enemy = null;
                System.out.println("Which number enemy are you attacking? \n" +
                        "\tExit attack: 99");
                if(enemySelection.hasNextInt()){
                    enemyName = enemySelection.nextInt();
                    if(enemyName == 99){
                        return -1;
                    }
                    if(enemyName < 0 || enemyName > enemiesFromLocation.size() - 1){
                        System.out.println("Select a valid enemy number");
                    } else {
                        enemy = enemiesFromLocation.get(enemyName);
                        if(!enemy.getIsAlive()){
                            System.out.println("Dude, that one is already dead. Sicko!");
                        } else {
                            quit = true;
                        }
                    }
                    index = enemiesFromLocation.indexOf(enemy);
                } else {
                    System.out.println("Choose an enemy number");
                    enemySelection.nextLine();
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
