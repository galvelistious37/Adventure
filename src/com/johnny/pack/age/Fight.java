package com.johnny.pack.age;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Fight {

    /**
     * Display Fight Menu options
     */
    private void getFightMenu(){
        createOptions().stream()
                .forEach(System.out::println);
    }

    /**
     * Create Fight Menu.
     * @return List of String Fight Menu options.
     */
    private List<String> createOptions() {
        List<String> generateOptions = new ArrayList<>();
        generateOptions.add(Constant.FIGHT + Constant.COLON_SEPARATOR + Constant.DO_FIGHTIN);
        generateOptions.add(Constant.INTIMIDATE + Constant.COLON_SEPARATOR + Constant.INTIMIDATE_THEM);
        generateOptions.add(Constant.SNEAK_PAST + Constant.COLON_SEPARATOR + Constant.SNEAKY_LIKE);
        generateOptions.add(Constant.RUN_AWAY + Constant.COLON_SEPARATOR + Constant.RUN_FORREST);
        return generateOptions;
    }

    void initiative(Character player, List<Character> enemiesFromLocation) {
        List<Character> everyone = new ArrayList<>();
        everyone.add(player);
        everyone.addAll(enemiesFromLocation);
        everyone.stream()
                .filter(e -> e.getInitiative() == Constant.ZERO)
                .forEach(e -> e.setInitiative(Dice.getInstance().rollTheDie(Constant.TWENTY)));
    }

    void doFightinStuff(Character player, List<Character> enemiesFromLocation) {
        boolean quit = false;
        int round = Constant.ZERO;
        while(!quit){
            round++;
            if(enemiesFromLocation.size() == Constant.ZERO){
                quit = true;
                System.out.println("No more enemies in these lands");
            } else {
                showDisplays(player, enemiesFromLocation);
                for(int i = Constant.TWENTY; i > Constant.ZERO; i--){
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
                                attack(enemy, player);
                                if(!player.getIsAlive()){
                                    playerDied(enemy);
                                }
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

    private void playerDied(Character enemy) {
        System.out.println(enemy.getName() + " killed you");
        System.out.println("Game Over");
        pressEnterKeyToContinue();
        GamePlay.getInstance().quit();
    }

    private boolean eatTheDead() {
        System.out.println("Enter \"Yes\" to eat the dead");
        return UserInput.getUserInstance().isInputYes();
    }

    private void digestTheDead(Character player){
        System.out.println("You devour the flesh of your enemy");
        int hp = player.getHitPoints();
        hp += Constant.TEN;
        if(hp > Constant.ONE_HUNDRED){
            hp = Constant.ONE_HUNDRED;
        }
        player.setHitPoints(hp);
        System.out.println("You have " + player.getHitPoints() + " hit points");
    }



    boolean countTheDead(List<Character> enemiesFromLocation) {
        int bodies = Constant.ZERO;
        for(Character enemy : enemiesFromLocation){
            if(!enemy.getIsAlive()){
                bodies++;
            }
        }
        return bodies == enemiesFromLocation.size() && bodies > Constant.ZERO;
    }

    private void pressEnterKeyToContinue()
    {
        System.out.println("Press Enter key to quit...");
        UserInput.getUserInstance().getScanner().nextLine();
    }

    private boolean tryAction(int action, Character player, List<Character> enemiesFromLocation, int round) {
        switch(action){
            case Constant.ONE:
                attackEnemySelection(player, enemiesFromLocation);
                return false;
            case Constant.TWO:
                intimidate(enemiesFromLocation);
                return false;
            case Constant.THREE:
                return sneak(round, enemiesFromLocation, player);
            case Constant.FOUR:
                runAway();
                return true;
        }
        return false;
    }

    private boolean sneak(int round, List<Character> enemiesFromLocation, Character player) {
        if(round == Constant.ONE && haveTheDrop(player, enemiesFromLocation)){
             return attemptSneak(enemiesFromLocation);
        } else {
            System.out.println("The enemies have already seen you. You cannot sneak.");
            return false;
        }
    }

    private boolean haveTheDrop(Character player, List<Character> enemiesFromLocation) {
        return Constant.ZERO == enemiesFromLocation.stream()
                .filter(e -> e.getInitiative() > player.getInitiative())
                .count();
    }

    private boolean attemptSneak(List<Character> enemiesFromLocation) {
        for(Character enemy : enemiesFromLocation){
            if(Dice.getInstance().rollTheDie(Constant.TWENTY) > Constant.SEVENTEEN){
                System.out.println(enemy.getName() + " busted you trying to sneak by");
                return false;
            }
        }
        System.out.println("You sneak passed the enemies");
        return true;
    }

    private void intimidate(List<Character> enemiesFromLocation) {
        int enemyToIntimidate = whichEnemy(enemiesFromLocation);
        if(enemyToIntimidate != Constant.NEGATIVE_ONE){
            Character currentEnemy = enemiesFromLocation.get(enemyToIntimidate);
            int successRoll = Dice.getInstance().rollTheDie(Constant.TWENTY);
            System.out.println("Success Roll: " + successRoll);
            if(successRoll > Constant.TEN){
                enemiesFromLocation.remove(currentEnemy);
                currentEnemy.setLocation(Dice.getInstance().getRandomLocation());
                System.out.println("You scared " + currentEnemy.getName() + " so " +
                        "bad it ran away");
            } else if (successRoll < Constant.TEN){
                currentEnemy.setHitPoints(currentEnemy.getHitPoints() + Constant.FIVE);
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

    private void attackEnemySelection(Character player, List<Character> enemiesFromLocation) {
        int enemyToAttack = whichEnemy(enemiesFromLocation);
        if(enemyToAttack != Constant.NEGATIVE_ONE){
            Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
            attack(player, currentEnemy);
        } else {
            System.out.println("You did not pick an enemy and lost your turn");
        }
    }

    private void attack(Character attacker, Character victim) {
        String severity = determineSeverity(Dice.getInstance().rollTheDie(Constant.TWENTY));
        int damageDealt = determineDamage(attacker, severity);
        displayAttackDetails(attacker, victim, severity, damageDealt);
        victim.setHitPoints(victim.getHitPoints() - damageDealt);
        victim.setIsAlive(checkStillAlive(victim));
    }

    private int determineDamage(Character attacker, String severity) {
        switch(severity){
            case "critical" :
                return attacker.dealDamage() * Constant.TWO;
            case "normal" :
                return attacker.dealDamage();
            case "low" :
                return (int) Math.ceil(attacker.dealDamage() / Constant.TWO);
            default :
                return Constant.ZERO;
        }
    }

    private String determineSeverity(int roll) {
        if(roll >= Constant.EIGHTEEN){
            return "critical";
        } else if(roll >= Constant.SEVEN){
            return "normal";
        }else if(roll >= Constant.THREE){
            return "low";
        } else
            return "none";
    }

    private void displayAttackDetails(Character attacker, Character victim, String severity, int damage){
        String form;
        switch(severity){
            case "critical" :
                form = attacker.getBerserkable().goBersek();
                break;
            case "normal" :
                form = attacker.getAttackable().attack();
                break;
            case "low" :
                form = attacker.getScratchable().scratch();
                break;
            default :
                form = "missed";
        }

        System.out.println(attacker.getName() + " " +
                form + " " + victim.getName() + " for " + damage + " HP");
    }

    private boolean checkStillAlive(Character victim) {
        if (victim.getHitPoints() <= Constant.ZERO) {
            victim.setHitPoints(Constant.ZERO);
        }
        return victim.getHitPoints() > Constant.ZERO;
    }

    private int whichEnemy(List<Character> enemiesFromLocation) {
        if(moreThanOneEnemy(enemiesFromLocation)){
            return getEnemyIndex(enemiesFromLocation);
        } else {
            return Constant.ZERO;
        }
    }

    private int getEnemyIndex(List<Character> enemiesFromLocation) {
        int enemyIndex = Constant.ZERO;
        int index;
        boolean quit = false;
        while (!quit) {
            displayPrompt();
            if (UserInput.getUserInstance().scannerHasNextInt()) {
                index = Integer.parseInt(UserInput.getUserInstance().getScanner().nextLine());
                if(index == Constant.NINETY_NINE) {
                    enemyIndex = Constant.NEGATIVE_ONE;
                    quit = true;
                } else {
                    if (isValidInput(index, enemiesFromLocation)) {
                        if (!alreadyDead(index, enemiesFromLocation)) {
                            enemyIndex = index;
                            quit = true;
                        }
                    }
                }
            } else {
                System.out.println("Choose an enemy number");
                UserInput.getUserInstance().scannerNextLine();
            }
        }
        return enemyIndex;
    }

    private boolean alreadyDead(int enemyIndex, List<Character> enemiesFromLocation) {
        if(!enemiesFromLocation.get(enemyIndex).getIsAlive()){
            System.out.println("Dude, that one is already dead. Sicko!");
            return true;
        }
        return false;
    }

    private boolean isValidInput(int index, List<Character> enemiesFromLocation) {
        return index >= Constant.ZERO && index <= enemiesFromLocation.size() - Constant.ONE;
    }

    private void displayPrompt() {
        System.out.println("Which number enemy are you attacking? \n" +
                "\tExit attackEnemySelection: 99");
    }

    private boolean moreThanOneEnemy(List<Character> enemiesFromLocation) {
        return enemiesFromLocation.size() > Constant.ONE;
    }

    private int getSelection() {
        while(true) {
            getFightMenu();
            if(UserInput.getUserInstance().scannerHasNextInt()){
                int selection =  Integer.parseInt(UserInput.getUserInstance().getScanner().nextLine());
                if(selection > Constant.ZERO && selection < Constant.FIVE){
                    return selection;
                }
            }
            System.out.println("Select a valid option");
            UserInput.getUserInstance().getScanner().nextLine();
        }
    }

    private void showDisplays(Character player, List<Character> enemiesFromLocation) {
        displaySpacer();
        showPlayerStatus(player);
        displayEnemies(enemiesFromLocation);
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

    private void displayEnemies(List<Character> localEnemies){
        System.out.println("~Enemies~");
        for(Character enemy : localEnemies){
            System.out.println("\t" + "[" + localEnemies.indexOf(enemy) + "] " +
                    enemy.getName() + ": " + enemy.getHitPoints() + " HP");
        }
        System.out.println(" ");
    }
}
