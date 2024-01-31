package com.johnny.pack.age.controller.attack;

import com.johnny.pack.age.controller.builder.LocationBuilder;
import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.controller.status.CharacterStatus;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.constant.Constant;
import com.johnny.pack.age.view.Display;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Fight {

    private Player player = Player.getInstance();
    private List<Character> enemies;
    private final Predicate<Integer> playerQuit = i -> i == 99;
    private final Predicate<Integer> indexInRange = i -> i >= 0 && i <= enemies.size() - 1;
    private final Predicate<Character> isCharacterAlive = Character::getIsAlive;
    private final Predicate<Integer> isFirstRound = i -> i == 0;
    private final Predicate<Integer> haveTheDrop = i -> enemies.stream().noneMatch(e -> e.getInitiative() > i);
    private final Predicate<Integer> bustedSneaking = i -> i > 17;
    private final Predicate<Integer> belowZero = i -> i < 0;
    private final Predicate<Integer> hasHitPoints = i -> i > 0;

    public Fight(List<Character> enemies){
        this.enemies = enemies;
    }

//    /**
//     * Countdown from 20 to determine initiative order for player and
//     * @param round - int value of round number
//     * @return - boolean quit
//     */
//    public boolean goThroughFightOrder(int round) {
//        boolean quit = false;
//
//
//        return quit;
//    }

    /**
     * Check if the enemy is alive and attack player if true.
     * @param player - player object
     * @param enemy - enemy object
     */
    public void enemyAction(Character player, Character enemy) {
        attack(enemy, player);
    }

    /**
     * Switch case for logic based on user input
     * @param action - int value user input
     * @param round - int value for round number
     * @return - boolean quit
     */
    public boolean tryAction(int action, int round) {
        return switch (action) {
            case Constant.ONE -> {attackEnemySelection(player, enemies); yield false;}
            case Constant.TWO -> {intimidate(enemies); yield false;}
            case Constant.THREE -> sneak(round, player);
            case Constant.FOUR -> {Display.displayText("You run away like some kind of wuss"); yield true;}
            default ->  false;
        };
    }

    /**
     * Check if player has the option to sneak passed the enemies
     * based on round number and initiative checks.
     * @param round - int value round number
     * @param player - player object
     * @return - boolean successful sneak attempt
     */
    private boolean sneak(int round, Character player) {
        if(isFirstRound.test(round) || !haveTheDrop.test(player.getInitiative())){
            Display.displayText("The enemies have already seen you. You cannot sneak.");
            return false;
        }
        return attemptSneak(enemies);
    }

//    /**
//     * Check if any enemies have a greater initiative than the player
//     * @param playerInitiative - int value player initiative
//     * @param enemies - enemy object
//     * @return - boolean player has greater initiative than all enemies
//     */
//    private boolean haveTheDrop(int playerInitiative, List<Character> enemies) {
//        return Numbers.ZERO.getValue() == enemies.stream()
//                .filter(e -> e.getInitiative() > playerInitiative)
//                .count();
//    }

    /**
     * Check if any enemy in the location noticed you trying to sneak
     * @param enemies - List of enemy objects
     * @return - boolean successful sneak
     */
    private boolean attemptSneak(List<Character> enemies) {
        for(Character enemy : enemies){
            if(bustedSneaking.test(Dice.rollTheDie(Numbers.TWENTY.getValue()))){
                Display.displayText(enemy.getName() + " busted you trying to sneak by");
                return false;
            }
        }
        Display.displayText("You sneak passed the enemies");
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
            Display.displayText("You have chosen not to " +
                    "intimidate and have lost your turn");
            return;
        }

        // get enemy object from list
        Character enemy = enemies.get(enemyIndex);

        // Get intimidation value
        int successRoll = Dice.rollTheDie(Numbers.TWENTY.getValue());
        Display.displayText("Success Roll: " + successRoll);

        // Did player succeed in intimidating the enemy
        if(successRoll > Numbers.TEN.getValue()){
            enemies.remove(enemy);
            enemy.setLocation(LocationBuilder.getRandomLocation());
            Display.displayText("You scared "
                    + enemy.getName() + " so bad it ran away");
        } else {
            // if intimidation failed, enemy gets a boost of hp
            enemy.setHitPoints(CharacterStatus.increaseHealth(enemy, Numbers.FIVE.getValue()));
            Display.displayText("You failed at your intimidation attempt and " +
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
            Display.displayText("You did not pick an " +
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
            case "critical" -> attacker.getEquipable().getBerserkable().goBerserk();
            case "normal" -> attacker.getEquipable().getAttackable().attack();
            case "low" -> attacker.getEquipable().getScratchable().scratch();
            default -> "missed";
        };

        Display.displayText(attacker.getName() + " " +
                form + " " + victim.getName() + " for " + damage + " HP");
    }

    /**
     * Check if the victim Character object isALive
     * @param victim - victim Character object
     * @return - boolean isAlive
     */
    private boolean checkStillAlive(Character victim) {
        Consumer<Integer> setHitPoints = victim::setHitPoints;
        if (belowZero.test(victim.getHitPoints())) {
            setHitPoints.accept(Numbers.ZERO.getValue());
        }
        return hasHitPoints.test(victim.getHitPoints());
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
            Display.displayText("Select an enemy:" +
                    "\n\tEnter 99 to leave");

            // Get user input
            String userInput = UserInput.getUserInstance().getScanner().nextLine();

            // Did user select a valid option?
            if(UserInput.getUserInstance().validateEnemySelection(userInput)){
                Display.displayText("Choose an enemy number");
                // No - next loop iteration and try again
                continue;
            }

            int index = Integer.parseInt(userInput);
            if(playerQuit.test(index)) return Numbers.NEGATIVE_ONE.getValue(); // Yes - return -1 and leave
            if (!indexInRange.test(index)) continue; // No - next loop iteration
            if (!isCharacterAlive.test(enemies.get(index))) continue; // NO - next loop iteration

            // User picked a valid enemy - return enemy index value
            return index;
        }
    }

    /**
     * Is there more than one enemy in the list
     * @param enemies - List of enemy objects
     * @return - boolean more than one enemy object in the list
     */
    private boolean moreThanOneEnemy(List<Character> enemies) {
        return enemies.size() > Numbers.ONE.getValue();
    }
}
