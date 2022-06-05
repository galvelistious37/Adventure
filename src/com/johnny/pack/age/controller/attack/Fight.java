package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.controller.GamePlay;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.model.character.Character;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.view.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fight {

    /**
     * Set the initiative value for all characters
     * @param player - The player object
     * @param enemiesFromLocation - List of enemies
     */
    public void initiative(Character player, List<Character> enemiesFromLocation) {
        List<Character> everyone = new ArrayList<>();
        everyone.add(player);
        everyone.addAll(enemiesFromLocation);
        everyone.stream()
                .filter(e -> e.getInitiative() == Numbers.ZERO.getValue())
                .forEach(e -> e.setInitiative(Dice.getInstance().rollTheDie(Numbers.TWENTY.getValue())));
    }

    /**
     * Loop over the call to establish fight logic until the
     * return value to quit is true. Keep track on the number
     * of loops iterations as the rounds indicator.
     * @param player - The player object
     * @param enemies - List of enemies in this location
     */
    public void doFightinStuff(Character player, List<Character> enemies) {
        boolean quit = false;
        int round = Numbers.ZERO.getValue();
        while(!quit){
            round++;
            quit = handleEnemies(player, enemies, round);
        }
    }

    /**
     *
     * @param player
     * @param enemies
     * @param round
     * @return
     */
    private boolean handleEnemies(Character player, List<Character> enemies, int round) {
        boolean quit;
        Display.getDisplayInstance.showDisplays(player, enemies);
        quit = determineInitiativeOrder(player, enemies, round);
        if(!areEnemiesAlive(enemies) && enemies.size() > Numbers.ZERO.getValue()){
            Display.getDisplayInstance.displayText("You have painted these lands with blood of your enemies");
            if(eatTheDead()){
                digestTheDead(player);
            }
            quit = true;
        }
        return quit;
    }

    /**
     * Display Fight Menu options
     */
    private void getFightMenu(){
//        createOptions().forEach(System.out::println);
        createOptions().forEach(Display.getDisplayInstance::displayText);
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

    private boolean determineInitiativeOrder(Character player, List<Character> enemies, int round) {
        boolean quit = false;
        for(int i = Numbers.TWENTY.getValue(); i > Numbers.ZERO.getValue(); i--){
            if(isCharacterInitiative(player, i)){
                int action = getSelection();
                quit = tryAction(action, player, enemies, round);
            }
            if(quit || enemies.size() == 0){
                break;
            }
            for(Character enemy : enemies){
                if(isCharacterInitiative(enemy, i)){
                    quit = enemyAction(player, enemy);
                }
            }
        }
        return quit;
    }

    private boolean enemyAction(Character player, Character enemy) {
        if(enemy.getIsAlive()){
            attack(enemy, player);
            if(!player.getIsAlive()){
                playerDied(enemy);
                return true;
            }
        } else {
            Display.getDisplayInstance.displayText(enemy.getName() + " is dead");
        }
        return false;
    }

    private boolean isCharacterInitiative(Character character, int i) {
        return character.getInitiative() == i;
    }

    private void playerDied(Character enemy) {
        Display.getDisplayInstance.displayText(
                enemy.getName() + " killed you!\nGame Over");
        pressEnterKeyToContinue();
        GamePlay.getInstance().quit();
    }

    private boolean eatTheDead() {
        Display.getDisplayInstance.displayText("Enter \"Yes\" to eat the dead");
        return UserInput.getUserInstance().isInputYes();
    }

    private void digestTheDead(Character player){
        Display.getDisplayInstance.displayText("You devour the flesh of your enemy");
        int hp = player.getHitPoints();
        hp += Numbers.TEN.getValue();
        if(hp > Numbers.ONE_HUNDRED.getValue()){
            hp = Numbers.ONE_HUNDRED.getValue();
        }
        player.setHitPoints(hp);
        Display.getDisplayInstance.displayText("You have " +
                player.getHitPoints() + " hit points");
    }



    public boolean areEnemiesAlive(List<Character> enemies) {
        for(Character enemy : enemies){
            if(enemy.getIsAlive()){
                return true;
            }
        }
        return false;
    }

    private void pressEnterKeyToContinue()
    {
        Display.getDisplayInstance.displayText("Press Enter key to quit...");
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
                Display.getDisplayInstance.displayText("You run away like some kind of wuss");
                return true;
        }
        return false;
    }

    private boolean sneak(int round, List<Character> enemiesFromLocation, Character player) {
        if(round == Numbers.ONE.getValue() && haveTheDrop(player, enemiesFromLocation)){
             return attemptSneak(enemiesFromLocation);
        } else {
            Display.getDisplayInstance.displayText("The enemies have " +
                    "already seen you. You cannot sneak.");
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
                Display.getDisplayInstance.displayText(enemy.getName() +
                        " busted you trying to sneak by");
                return false;
            }
        }
        Display.getDisplayInstance.displayText("You sneak passed the enemies");
        return true;
    }

    private void intimidate(List<Character> enemiesFromLocation) {
        int enemyToIntimidate = whichEnemy(enemiesFromLocation);
        if(enemyToIntimidate != Numbers.NEGATIVE_ONE.getValue()){
            Character currentEnemy = enemiesFromLocation.get(enemyToIntimidate);
            int successRoll = Dice.getInstance().rollTheDie(Numbers.TWENTY.getValue());
            Display.getDisplayInstance.displayText("Success Roll: " + successRoll);
            if(successRoll > Numbers.TEN.getValue()){
                enemiesFromLocation.remove(currentEnemy);
                currentEnemy.setLocation(Dice.getInstance().getRandomLocation());
                Display.getDisplayInstance.displayText("You scared "
                        + currentEnemy.getName() + " so bad it ran away");
            } else if (successRoll < Numbers.TEN.getValue()){
                currentEnemy.setHitPoints(currentEnemy.getHitPoints() + Numbers.FIVE.getValue());
                Display.getDisplayInstance.displayText("You failed at your intimidation attempt and " +
                        currentEnemy.getName() + " is definitely not scared of you" +
                        "\n" + currentEnemy.getName() + " gained 5 HP");
            }


        } else {
            Display.getDisplayInstance.displayText("You have chosen not to " +
                    "intimidate and have lost your turn");
        }
    }

    private void attackEnemySelection(Character player, List<Character> enemiesFromLocation) {
        int enemyToAttack = whichEnemy(enemiesFromLocation);
        if(enemyToAttack != Numbers.NEGATIVE_ONE.getValue()){
            Character currentEnemy = enemiesFromLocation.get(enemyToAttack);
            attack(player, currentEnemy);
        } else {
            Display.getDisplayInstance.displayText("You did not pick an " +
                    "enemy and lost your turn");
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
        Display.getDisplayInstance.displayText(attacker.getName() + " " +
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
            Display.getDisplayInstance.displayText("Which number enemy are you " +
                    "attacking? \n\tExit attackEnemySelection: 99");
            String test = UserInput.getUserInstance().getScanner().nextLine();
            if(Display.getDisplayInstance.getAcceptableNumbers().stream()
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
                Display.getDisplayInstance.displayText("Choose an enemy number");
//                UserInput.getUserInstance().scannerNextLine();
            }
        }
        return enemyIndex;
    }

    private boolean alreadyDead(int enemyIndex, List<Character> enemiesFromLocation) {
        if(!enemiesFromLocation.get(enemyIndex).getIsAlive()){
            Display.getDisplayInstance.displayText("Dude, that one is " +
                    "already dead. Sicko!");
            return true;
        }
        return false;
    }

    private boolean isValidInput(int index, List<Character> enemiesFromLocation) {
        return index >= Numbers.ZERO.getValue() && index <= enemiesFromLocation.size() - Numbers.ONE.getValue();
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
            Display.getDisplayInstance.displayText("Select a valid option");
            UserInput.getUserInstance().getScanner().nextLine();
        }
    }
}
