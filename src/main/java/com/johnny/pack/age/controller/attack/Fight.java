package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.builder.LocationBuilder;
import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.controller.GamePlay;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.view.Display;

import java.util.ArrayList;
import java.util.List;

public class Fight {

    private Player player = Player.getInstance();
    private List<Character> enemies;

    public Fight(List<Character> enemies){
        this.enemies = enemies;
    }

    /**
     * Display Fight Menu options
     */
    private void getFightMenu(){
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

    /**
     * stream through enemies and find any alive
     * @return - boolean an enemy is alive
     */
    public boolean areEnemiesAlive() {
        return enemies.stream().anyMatch(Character::getIsAlive);
    }

    /**
     * Loop over the call to determine fight order and the fight
     * logic until the return value to quit is true. Keep track
     * on the number of loops iterations as the rounds indicator.
     */
    public void doFightinStuff() {
        int round = Numbers.ZERO.getValue();
        boolean quit = false;
        while(!quit){
            Display.getDisplayInstance.showDisplays(player, enemies);
            round++;
            quit = goThroughFightOrder(round);

            // if there are no enemies left
            if(enemies.size() == Numbers.ZERO.getValue()){
                quit = true;
            } else if (!areEnemiesAlive()){
                Display.getDisplayInstance.displayText("You have painted these lands " +
                        "with blood of your enemies");
                if(eatTheDead()){
                    digestTheDead(player);
                }
                quit = true;
            }
        }
    }

    /**
     * Countdown from 20 to determine initiative order for player and
     * @param round - int value of round number
     * @return - boolean quit
     */
    private boolean goThroughFightOrder(int round) {
        boolean quit = false;

        // Counting down from 20, check player initiative
        for(int i = Numbers.TWENTY.getValue(); i > Numbers.ZERO.getValue(); i--){
            if(player.getInitiative() == i){
                int action = getSelection();
                quit = tryAction(action, player, enemies, round);
            }
            // Did player quit or enemies leave?
            if(quit || enemies.size() == 0){
                break;
            }
            // Get enemies initiative
            for(Character enemy : enemies){
                if(enemy.getInitiative() == i){
                    quit = enemyAction(player, enemy);
                }
            }
        }
        return quit;
    }

    /**
     * Check if the enemy is alive and attack player if true.
     * @param player - player object
     * @param enemy - enemy object
     * @return - boolean is player dead
     */
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

    /**
     * Logic to end game when player dies
     * @param enemy - enemy object
     */
    private void playerDied(Character enemy) {
        Display.getDisplayInstance.displayText(
                enemy.getName() + " killed you!\nGame Over");
        pressEnterKeyToContinue();
        GamePlay.getInstance().quit();
    }

    /**
     * Get user input on whether they should eat the dead
     * @return - boolean yes
     */
    private boolean eatTheDead() {
        Display.getDisplayInstance.displayText("Enter \"Yes\" to eat the dead");
        return UserInput.getUserInstance().isInputYes();
    }

    /**
     * Logic to increase player object's hitpoints after eating the dead
     * @param player - player object
     */
    private void digestTheDead(Character player){
        Display.getDisplayInstance.displayText("You devour the flesh of your enemy");
        int hp = player.getHitPoints();
        hp += Numbers.TEN.getValue();

        // if hp would increase above 100, set hp to 100
        if(hp > Numbers.ONE_HUNDRED.getValue()){
            hp = Numbers.ONE_HUNDRED.getValue();
        }
        player.setHitPoints(hp);
        Display.getDisplayInstance.displayText("You have " +
                player.getHitPoints() + " hit points");
    }

    /**
     * Press enter to continue
     */
    private void pressEnterKeyToContinue()
    {
        Display.getDisplayInstance.displayText("Press Enter key to quit...");
        UserInput.getUserInstance().getScanner().nextLine();
    }

    /**
     * Switch case for logic based on user input
     * @param action - int value user input
     * @param player - player object
     * @param enemies - List of enemy objects
     * @param round - int value for round number
     * @return - boolean quit
     */
    private boolean tryAction(int action, Character player, List<Character> enemies, int round) {
        switch (action) {
            case Constant.ONE -> {
                attackEnemySelection(player, enemies);
                return false;
            }
            case Constant.TWO -> {
                intimidate(enemies);
                return false;
            }
            case Constant.THREE -> {
                return sneak(round, enemies, player);
            }
            case Constant.FOUR -> {
                Display.getDisplayInstance.displayText("You run away like some kind of wuss");
                return true;
            }
        }
        return false;
    }

    /**
     * Check if player has the option to sneak passed the enemies
     * based on round number and initiative checks.
     * @param round - int value round number
     * @param enemies - List of enemy objects
     * @param player - player object
     * @return - boolean successful sneak attempt
     */
    private boolean sneak(int round, List<Character> enemies, Character player) {
        if(round == Numbers.ONE.getValue() && haveTheDrop(player.getInitiative(), enemies)){
             return attemptSneak(enemies);
        } else {
            Display.getDisplayInstance.displayText("The enemies have " +
                    "already seen you. You cannot sneak.");
            return false;
        }
    }

    /**
     * Check if any enemies have a greater initiative than the player
     * @param playerInitiative - int value player initiative
     * @param enemies - enemy object
     * @return - boolean player has greater initiative than all enemies
     */
    private boolean haveTheDrop(int playerInitiative, List<Character> enemies) {
        return Numbers.ZERO.getValue() == enemies.stream()
                .filter(e -> e.getInitiative() > playerInitiative)
                .count();
    }

    /**
     * Check if any enemy in the location noticed you trying to sneak
     * @param enemies - List of enemy objects
     * @return - boolean successful sneak
     */
    private boolean attemptSneak(List<Character> enemies) {
        for(Character enemy : enemies){
            if(Dice.rollTheDie(Numbers.TWENTY.getValue()) > Numbers.SEVENTEEN.getValue()){
                Display.getDisplayInstance.displayText(enemy.getName() +
                        " busted you trying to sneak by");
                return false;
            }
        }
        Display.getDisplayInstance.displayText("You sneak passed the enemies");
        return true;
    }

    /**
     * Determine whether an intimidation attempt was successful.
     * @param enemies - List of enemy objects
     */
    private void intimidate(List<Character> enemies) {
        // select enemy from list
        int enemyIndex = whichEnemy(enemies);

        // Did you select an enemy?
        if(enemyIndex == Numbers.NEGATIVE_ONE.getValue()){
            // User selected to leave
            Display.getDisplayInstance.displayText("You have chosen not to " +
                    "intimidate and have lost your turn");
            return;
        }

        // get enemy object from list
        Character enemy = enemies.get(enemyIndex);

        // Get intimidation value
        int successRoll = Dice.rollTheDie(Numbers.TWENTY.getValue());
        Display.getDisplayInstance.displayText("Success Roll: " + successRoll);

        // Did player succeed in intimidating the enemy
        if(successRoll > Numbers.TEN.getValue()){
            enemies.remove(enemy);
            enemy.setLocation(LocationBuilder.getRandomLocation());
            Display.getDisplayInstance.displayText("You scared "
                    + enemy.getName() + " so bad it ran away");
        } else {
            // if intimidation failed, enemy gets a boost of hp
            enemy.setHitPoints(enemy.getHitPoints() + Numbers.FIVE.getValue());
            Display.getDisplayInstance.displayText("You failed at your intimidation attempt and " +
                    enemy.getName() + " is definitely not scared of you" +
                    "\n" + enemy.getName() + " gained 5 HP");
        }
    }

    /**
     * Select an enemy to attack and perform the attack.
     * @param player - player object
     * @param enemies - List of enemy objects
     */
    private void attackEnemySelection(Character player, List<Character> enemies) {
        int enemyIndex = whichEnemy(enemies);
        if(enemyIndex != Numbers.NEGATIVE_ONE.getValue()){
            Character enemy = enemies.get(enemyIndex);
            attack(player, enemy);
        } else {
            Display.getDisplayInstance.displayText("You did not pick an " +
                    "enemy and lost your turn");
        }
    }

    /**
     * Logic to determine that attacker's severity of attack,
     * damage delt, and set victim's damage
     * @param attacker - attacker Character object
     * @param victim - victim Character object
     */
    private void attack(Character attacker, Character victim) {
        String severity = determineSeverity();
        int damageDealt = determineDamage(attacker, severity);
        displayAttackDetails(attacker, victim, severity, damageDealt);
        victim.setHitPoints(victim.getHitPoints() - damageDealt);
        victim.setIsAlive(checkStillAlive(victim));
    }

    /**
     * Determine severity of attack
     * @return - String value of severity
     */
    private String determineSeverity() {
        int roll = Dice.rollTheDie(Numbers.TWENTY.getValue());
        if(roll >= Numbers.EIGHTEEN.getValue()){
            return "critical";
        } else if(roll >= Numbers.SEVEN.getValue()){
            return "normal";
        }else if(roll >= Numbers.THREE.getValue()){
            return "low";
        } else
            return "none";
    }

    /**
     * User serverity level to determine attacker's damage value
     * @param attacker - attacker Character object
     * @param severity - String value severity level
     * @return - int value attacker's damage dealt
     */
    private int determineDamage(Character attacker, String severity) {
        return switch (severity) {
            case "critical" -> attacker.dealDamage() * Numbers.TWO.getValue();
            case "normal" -> attacker.dealDamage();
            case "low" -> (int) Math.ceil((double) attacker.dealDamage() / Numbers.TWO.getValue());
            default -> Numbers.ZERO.getValue();
        };
    }

    /**
     * Build display based on attack severity level
     * @param attacker - attacker Character object
     * @param victim - victim Character object
     * @param severity - String value severity level
     * @param damage - int value damage dealt
     */
    private void displayAttackDetails(Character attacker, Character victim,
                                      String severity, int damage){
        String form = switch(severity){
            case "critical" -> attacker.getBerserkable().goBersek();
            case "normal" -> attacker.getAttackable().attack();
            case "low" -> attacker.getScratchable().scratch();
            default -> form = "missed";
        };

        Display.getDisplayInstance.displayText(attacker.getName() + " " +
                form + " " + victim.getName() + " for " + damage + " HP");
    }

    /**
     * Check if the victim Character object isALive
     * @param victim - victim Character object
     * @return - boolean isAlive
     */
    private boolean checkStillAlive(Character victim) {
        if (victim.getHitPoints() <= Numbers.ZERO.getValue()) {
            victim.setHitPoints(Numbers.ZERO.getValue());
        }
        return victim.getHitPoints() > Numbers.ZERO.getValue();
    }

    /**
     * Logic to select an enemy if more than one, or
     * select the only enemy
     * @param enemies - List of enemy objects
     * @return - enemy object index
     */
    private int whichEnemy(List<Character> enemies) {
        if(moreThanOneEnemy(enemies)){
            return getEnemyIndex(enemies);
        } else {
            return Numbers.ZERO.getValue();
        }
    }

    /**
     * Logic to select an enemy from a list from given user input.
     * @param enemies - List of enemy objects
     * @return - an int index value for an enemy within a list
     */
    private int getEnemyIndex(List<Character> enemies) {
        while (true) {
            Display.getDisplayInstance.displayText("Select an enemy:" +
                    "\n\tEnter 99 to leave");

            // Get user input
            String userInput = UserInput.getUserInstance().getScanner().nextLine();

            // Did user select a valid option?
            if(Display.getDisplayInstance.getAcceptableNumbers().stream()
                    .noneMatch(input -> input.equalsIgnoreCase(userInput))){
                Display.getDisplayInstance.displayText("Choose an enemy number");
                // No - next loop iteration and try again
                continue;
            }

            // Did user select to leave?
            int index = Integer.parseInt(userInput);
            if(index == Numbers.NINETY_NINE.getValue()) {
                // Yes - Return -1 and leave
                return Numbers.NEGATIVE_ONE.getValue();
            }

            // Is user input within List range?
            if (!isIndexInRange(index, enemies)) {
                // No - next loop iteration and try again
                continue;
            }

            // Is enemy already dead?
            if (alreadyDead(index, enemies)) {
                // Yes - next loop iteration and try again
                continue;
            }

            // User picked a valid enemy - return enemy index value
            return index;
        }
    }

    /**
     * Check if the selected enemy index from list is alive.
     * @param index - int index value of enemy object within a list
     * @param enemies - List of enemy objects
     * @return - boolean enemy is dead
     */
    private boolean alreadyDead(int index, List<Character> enemies) {
        if(!enemies.get(index).getIsAlive()){
            Display.getDisplayInstance.displayText("Dude, that one is " +
                    "already dead. Sicko!");
            return true;
        }
        return false;
    }

    /**
     * Determine if the user input is an int value between 0 and the
     * number of enemy objects - 1
     * @param index - int value user input
     * @param enemies - List of enemies
     * @return - boolean user input is within range
     */
    private boolean isIndexInRange(int index, List<Character> enemies) {
        return index >= Numbers.ZERO.getValue() && index <= enemies.size() - Numbers.ONE.getValue();
    }

    /**
     * Is there more than one enemy in the list
     * @param enemies - List of enemy objects
     * @return - boolean more than one enemy object in the list
     */
    private boolean moreThanOneEnemy(List<Character> enemies) {
        return enemies.size() > Numbers.ONE.getValue();
    }

    /**
     * Determine whether user input is valid
     * @return - int value of user input
     */
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
