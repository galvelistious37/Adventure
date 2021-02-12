package com.johnny.pack.age;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Fight {

    /**
     * Display Fight Menu options
     */
    private void getFightMenu(){
        createOptions().forEach(System.out::println);
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
                .filter(e -> e.getInitiative() == Numbers.ZERO.getValue())
                .forEach(e -> e.setInitiative(Dice.getInstance().rollTheDie(Numbers.TWENTY.getValue())));
    }

    void doFightinStuff(Character player, List<Character> enemiesFromLocation) {
        boolean quit = false;
        int round = Numbers.ZERO.getValue();
        while(!quit){
            round++;
            if(isEnemyNotPresent(enemiesFromLocation)){
                quit = true;
            } else {
                quit = handleEnemies(player, enemiesFromLocation, round);
            }
        }
    }

    private boolean handleEnemies(Character player, List<Character> enemiesFromLocation, int round) {
        boolean quit;
        showDisplays(player, enemiesFromLocation);
        quit = determineInitiativeOrder(player, enemiesFromLocation, round);
        if(isAllEnemiesAreDead(enemiesFromLocation)){
            System.out.println("You have painted these lands with blood of your enemies");
            if(eatTheDead()){
                digestTheDead(player);
            }
            quit = true;
        }
        return quit;
    }

    private boolean determineInitiativeOrder(Character player, List<Character> enemiesFromLocation, int round) {
        boolean quit = false;
        for(int i = Numbers.TWENTY.getValue(); i > Numbers.ZERO.getValue(); i--){
            if(isCharacterInitiative(player, i)){
                int action = getSelection();
                quit = tryAction(action, player, enemiesFromLocation, round);
            }
            if(quit){
                break;
            }
            for(Character enemy : enemiesFromLocation){
                if(isCharacterInitiative(enemy, i)){
                    enemyAction(player, enemy);
                }
            }
        }
        return quit;
    }

    private void enemyAction(Character player, Character enemy) {
        if(enemy.getIsAlive()){
            attack(enemy, player);
            if(!player.getIsAlive()){
                playerDied(enemy);
            }
        } else {
            System.out.println(enemy.getName() + " is dead");
        }
    }

    private boolean isCharacterInitiative(Character character, int i) {
        return character.getInitiative() == i;
    }

    private boolean isEnemyNotPresent(List<Character> enemiesFromLocation) {
        boolean enemyIsPresent = enemiesFromLocation.size() == Numbers.ZERO.getValue();
        if(enemyIsPresent){
            System.out.println("No more enemies in these lands");
        }
        return enemyIsPresent;
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
        hp += Numbers.TEN.getValue();
        if(hp > Numbers.ONE_HUNDRED.getValue()){
            hp = Numbers.ONE_HUNDRED.getValue();
        }
        player.setHitPoints(hp);
        System.out.println("You have " + player.getHitPoints() + " hit points");
    }



    boolean isAllEnemiesAreDead(List<Character> enemiesFromLocation) {
        int bodies = Numbers.ZERO.getValue();
        for(Character enemy : enemiesFromLocation){
            if(!enemy.getIsAlive()){
                bodies++;
            }
        }
        return bodies == enemiesFromLocation.size() && bodies > Numbers.ZERO.getValue();
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
        if(round == Numbers.ONE.getValue() && haveTheDrop(player, enemiesFromLocation)){
             return attemptSneak(enemiesFromLocation);
        } else {
            System.out.println("The enemies have already seen you. You cannot sneak.");
            return false;
        }
    }

    private boolean haveTheDrop(Character player, List<Character> enemiesFromLocation) {
        return Numbers.ZERO.getValue() == enemiesFromLocation.stream()
                .filter(e -> e.getInitiative() > player.getInitiative())
                .count();
    }

    private boolean attemptSneak(List<Character> enemiesFromLocation) {
        for(Character enemy : enemiesFromLocation){
            if(Dice.getInstance().rollTheDie(Numbers.TWENTY.getValue()) > Numbers.SEVENTEEN.getValue()){
                System.out.println(enemy.getName() + " busted you trying to sneak by");
                return false;
            }
        }
        System.out.println("You sneak passed the enemies");
        return true;
    }

    private void intimidate(List<Character> enemiesFromLocation) {
        int enemyToIntimidate = whichEnemy(enemiesFromLocation);
        if(enemyToIntimidate != Numbers.NEGATIVE_ONE.getValue()){
            Character currentEnemy = enemiesFromLocation.get(enemyToIntimidate);
            int successRoll = Dice.getInstance().rollTheDie(Numbers.TWENTY.getValue());
            System.out.println("Success Roll: " + successRoll);
            if(successRoll > Numbers.TEN.getValue()){
                enemiesFromLocation.remove(currentEnemy);
                currentEnemy.setLocation(Dice.getInstance().getRandomLocation());
                System.out.println("You scared " + currentEnemy.getName() + " so " +
                        "bad it ran away");
            } else if (successRoll < Numbers.TEN.getValue()){
                currentEnemy.setHitPoints(currentEnemy.getHitPoints() + Numbers.FIVE.getValue());
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
        if(enemyToAttack != Numbers.NEGATIVE_ONE.getValue()){
            Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
            attack(player, currentEnemy);
        } else {
            System.out.println("You did not pick an enemy and lost your turn");
        }
    }

    private void attack(Character attacker, Character victim) {
        String severity = determineSeverity(Dice.getInstance().rollTheDie(Numbers.TWENTY.getValue()));
        int damageDealt = determineDamage(attacker, severity);
        displayAttackDetails(attacker, victim, severity, damageDealt);
        victim.setHitPoints(victim.getHitPoints() - damageDealt);
        victim.setIsAlive(checkStillAlive(victim));
    }

    private int determineDamage(Character attacker, String severity) {
        return switch (severity) {
            case "critical" -> attacker.dealDamage() * Numbers.TWO.getValue();
            case "normal" -> attacker.dealDamage();
            case "low" -> (int) Math.ceil(attacker.dealDamage() / Numbers.TWO.getValue());
            default -> Numbers.ZERO.getValue();
        };
    }

    private String determineSeverity(int roll) {
        if(roll >= Numbers.EIGHTEEN.getValue()){
            return "critical";
        } else if(roll >= Numbers.SEVEN.getValue()){
            return "normal";
        }else if(roll >= Numbers.THREE.getValue()){
            return "low";
        } else
            return "none";
    }

    private void displayAttackDetails(Character attacker, Character victim, String severity, int damage){
        String form;
        switch(severity){
            case "critical" -> form = attacker.getBerserkable().goBersek();
            case "normal" -> form = attacker.getAttackable().attack();
            case "low" -> form = attacker.getScratchable().scratch();
            default -> form = "missed";
        }

        System.out.println(attacker.getName() + " " +
                form + " " + victim.getName() + " for " + damage + " HP");
    }

    private boolean checkStillAlive(Character victim) {
        if (victim.getHitPoints() <= Numbers.ZERO.getValue()) {
            victim.setHitPoints(Numbers.ZERO.getValue());
        }
        return victim.getHitPoints() > Numbers.ZERO.getValue();
    }

    private int whichEnemy(List<Character> enemiesFromLocation) {
        if(moreThanOneEnemy(enemiesFromLocation)){
            return getEnemyIndex(enemiesFromLocation);
        } else {
            return Numbers.ZERO.getValue();
        }
    }

    private int getEnemyIndex(List<Character> enemiesFromLocation) {
        int enemyIndex = Numbers.ZERO.getValue();
        int index;
        boolean quit = false;
        while (!quit) {
            displayPrompt();
            String test = UserInput.getUserInstance().getScanner().nextLine();
            if(getAcceptableNumbers().stream()
                    .anyMatch(input -> input.equalsIgnoreCase(test)))
//            if (UserInput.getUserInstance().scannerHasNextInt())
            {
                index = Integer.parseInt(test);
                if(index == Numbers.NINETY_NINE.getValue()) {
                    enemyIndex = Numbers.NEGATIVE_ONE.getValue();
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
//                UserInput.getUserInstance().scannerNextLine();
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
        return index >= Numbers.ZERO.getValue() && index <= enemiesFromLocation.size() - Numbers.ONE.getValue();
    }

    private void displayPrompt() {
        System.out.println("Which number enemy are you attacking? \n" +
                "\tExit attackEnemySelection: 99");
    }

    private boolean moreThanOneEnemy(List<Character> enemiesFromLocation) {
        return enemiesFromLocation.size() > Numbers.ONE.getValue();
    }

    private int getSelection() {
        while(true) {
            getFightMenu();
            if(UserInput.getUserInstance().scannerHasNextInt()){
                int selection =  Integer.parseInt(UserInput.getUserInstance().getScanner().nextLine());
                if(selection > Numbers.ZERO.getValue() && selection < Numbers.FIVE.getValue()){
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

    private List<String> getAcceptableNumbers(){
        return Arrays.asList("0", "1", "2", "3", "4", "99");
    }
}
