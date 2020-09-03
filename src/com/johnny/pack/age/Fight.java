package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Fight {
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
    private final Dice diceRoll;

    Fight() {
        this.fightScanner = new Scanner(System.in);
        this.diceRoll = Dice.getInstance();
    }

    /**
     * Display Fight Menu options
     */
    private void getFightMenu(){
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

    void initiative(Character player, List<Character> enemiesFromLocation) {
        if(player.getInitiative() == 0){
            player.setInitiative(diceRoll.rollTheDie(20));
        }
        for(Character enemy : enemiesFromLocation){
            if (enemy.getInitiative() == 0) {
                enemy.setInitiative(diceRoll.rollTheDie(20));
            }
        }
    }

    void doFightinStuff(Player player, List<Character> enemiesFromLocation) {
        GamePlay gamePlay = GamePlay.getInstance();
        boolean quit = false;
        int round = 0;
        while(!quit){
            round++;
            if(enemiesFromLocation.size() == 0){
                quit = true;
                System.out.println("No more enemies in these lands");
            } else {
                showDisplays(player, enemiesFromLocation);
                for(int i = 20; i > 0; i--){
                    if(player.getInitiative() == i){
                        int action = getSelection();
                        quit = tryAction(action, player, enemiesFromLocation, round);
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
                    if(eatTheDead()){
                        digestTheDead(player);
                    }
                    quit = true;
                }
            }
        }
    }

    private boolean eatTheDead() {
        System.out.println("Enter \"Yes\" to eat the dead");
        Scanner scanner = new Scanner(System.in);
        String eatDead = scanner.nextLine();
        return eatDead.equalsIgnoreCase("YES");
    }

    private void digestTheDead(Player player){
        System.out.println("You devour the flesh of your enemy");
        int hp = player.getHitPoints();
        hp += 10;
        if(hp > 100){
            hp = 100;
        }
        player.setHitPoints(hp);
        System.out.println("You have " + player.getHitPoints() + " hit points");
    }



    boolean countTheDead(List<Character> enemiesFromLocation) {
        int bodies = 0;
        for(Character enemy : enemiesFromLocation){
            if(!enemy.getIsAlive()){
                bodies++;
            }
        }
        return bodies == enemiesFromLocation.size() && bodies > 0;
    }

    private void enemyAttack(Character player, Character enemy) {
        int playerHitpoint = player.getHitPoints();
        int roll = diceRoll.rollTheDie(20);
        int damage = enemyDealsDamage(roll, enemy);
        playerHitpoint -= damage;
        player.setHitPoints(playerHitpoint);
        if(player.getHitPoints() <= 0){
            player.setIsAlive(false);
            System.out.println(enemy.getName() + " killed you");
            System.out.println("Game Over");
            pressEnterKeyToContinue();
            GamePlay gamePlay = GamePlay.getInstance();
            gamePlay.quit();

        } else {
            System.out.println(player.getName() + " have " + player.getHitPoints() + " HP remaining");
        }
    }

    private void pressEnterKeyToContinue()
    {
        System.out.println("Press Enter key to quit...");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    private int enemyDealsDamage(int roll, Character enemy) {
        int damage;
        if(roll >= 18){
            damage = enemy.dealDamage() + 4;
            System.out.println(enemy.getName() + " " + enemy.getBerserkable().goBersek()
                    + " you for " + damage + " points of damage" );
        } else if(roll >= 7){
            damage = enemy.dealDamage();
            System.out.println(enemy.getName() + " " + enemy.getAttackable().attack()
                    + " you for " + damage + " points of damage" );
        }else{
            damage = 0;
            System.out.println(enemy.getName() + " totally fell on it's face" );
        }
        return damage;
    }

    private boolean tryAction(int action, Player player, List<Character> enemiesFromLocation, int round) {
        switch(action){
            case 1:
                attack(player, enemiesFromLocation);
                return false;
            case 2:
                intimidate(enemiesFromLocation);
                return false;
            case 3:
                return sneak(round, enemiesFromLocation, player);
            case 4:
                runAway();
                return true;
        }
        return false;
    }

    private boolean sneak(int round, List<Character> enemiesFromLocation, Character player) {
        if(round == 1 && haveTheDrop(player, enemiesFromLocation)){
             return attemptSneak(enemiesFromLocation);
        } else {
            System.out.println("The enemies have already seen you. You cannot sneak.");
            return false;
        }
    }

    private boolean haveTheDrop(Character player, List<Character> enemiesFromLocation) {
        for(Character enemy : enemiesFromLocation){
            if(enemy.getInitiative() > player.getInitiative()){
                return false;
            }
        }
        return true;
    }

    private boolean attemptSneak(List<Character> enemiesFromLocation) {
        for(Character enemy : enemiesFromLocation){
            if(diceRoll.rollTheDie(20) > 17){
                System.out.println(enemy.getName() + " busted you trying to sneak by");
                return false;
            }
        }
        System.out.println("You sneak passed the enemies");
        return true;
    }

    private void intimidate(List<Character> enemiesFromLocation) {
        int enemyToIntimidate = whichEnemy(enemiesFromLocation);
        if(enemyToIntimidate != -1){
            Character currentEnemy = enemiesFromLocation.get(enemyToIntimidate);
            int successRoll = diceRoll.rollTheDie(20);
            System.out.println("Success Roll: " + successRoll);
            if(successRoll > 10){
                enemiesFromLocation.remove(currentEnemy);
                currentEnemy.setLocation(diceRoll.getRandomLocation());
                System.out.println("You scared " + currentEnemy.getName() + " so " +
                        "bad it ran away");
            } else if (successRoll < 10){
                currentEnemy.setHitPoints(currentEnemy.getHitPoints() + 5);
                System.out.println("You failed at your intimidation attempt and " +
                        currentEnemy.getName() + " is definitely not scared of you");
                System.out.println(currentEnemy.getName() + " gained 5 HP");
            }


        } else {
            System.out.println("You have chosen not to intimidate and have lost your turn");
        }
    }

    private void runAway() {
        System.out.println("You run away like some kind of wuss");
    }

    private void attack(Player player, List<Character> enemiesFromLocation) {
        int enemyToAttack = whichEnemy(enemiesFromLocation);
        if(enemyToAttack != -1){
            Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
            int enemyHitpoints  = currentEnemy.getHitPoints();
            int roll = diceRoll.rollTheDie(20);
            String severity = "none";
            int damageDealt = 0;
            if(roll >= 18){
                severity = "critical";
                damageDealt = (player.dealDamage() * 2);
            } else if(roll >= 7){
                severity = "normal";
                damageDealt = player.dealDamage();
            }else if(roll >= 3){
                severity = "low";
                damageDealt = (player.dealDamage() - 3);
            }
            displayAttackDetails(player, currentEnemy, severity);
            currentEnemy.setHitPoints(enemyHitpoints - damageDealt);
            currentEnemy.setIsAlive(checkDead(currentEnemy));
        } else {
            System.out.println("You have chosen not to attack and have lost your turn");
        }
    }

    private void displayAttackDetails(Player player, Character enemy, String severity){
        String form;
        switch(severity){
            case "critical" :
                form = player.getBerserkable().goBersek();
                break;
            case "normal" :
                form = player.getAttackable().attack();
                break;
            case "low" :
                form = player.getScratchable().scratch();
                break;
            default :
                form = "missed";
        }

        System.out.println(player.getName() + " " +
                form + " " + enemy.getName() + " with your "  +
                player.getEquipable().weaponType());
    }

    private boolean checkDead(Character enemy) {
        if (enemy.getHitPoints() <= 0) {
            enemy.setHitPoints(0);
            System.out.println("You killed " + enemy.getName());
            return false;
        } else {
            System.out.println(enemy.getName() + " has "
                    + enemy.getHitPoints() + " hit points left");
            return true;
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

    private void showDisplays(Player player, List<Character> enemiesFromLocation) {
        displaySpacer();
        showPlayerStatus(player);
        displayEnemies(enemiesFromLocation);
    }

    private void displaySpacer() {
        System.out.println("**************************************************");
        System.out.println("**************************************************");
    }

    void showPlayerStatus(Player player) {
        System.out.println("");
        String message = "~Player Details~ " +
                "\nHit Points: " + player.getHitPoints() +
                "\nWeapon: " + player.getEquipable().weaponType();
        System.out.println(message);
        System.out.println(" ");
    }

    void displayEnemies(List<Character> localEnemies){
        System.out.println("Enemies in these lands:");
        for(Character enemy : localEnemies){
            System.out.println("\t" + "[" + localEnemies.indexOf(enemy) + "] " +
                    enemy.getName() + ": " + enemy.getHitPoints() + " HP");
        }
    }

}
