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
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Fight {

    private Player player = Player.getInstance();
    private List<Character> enemies;
    private final Predicate<Integer> playerQuit = i -> i == 99;
    private final Predicate<Integer> indexInRange = i -> i >= 0 && i <= enemies.size() - 1;
    private final Predicate<Character> isCharacterAlive = Character::getIsAlive;
    private final Predicate<Integer> isFirstRound = i -> i == 1;
    private final Predicate<Integer> haveTheDrop = i -> enemies.parallelStream().noneMatch(e -> e.getInitiative() > i);
    private final Predicate<Integer> bustedSneaking = i -> i > 17;
    private final Predicate<Integer> belowZero = i -> i < 0;
    private final Predicate<Integer> hasHitPoints = i -> i > 0;
    private final Predicate<Integer> isIntimidated = i -> i > Numbers.TEN.getValue();

    public Fight(List<Character> enemies){
        this.enemies = enemies;
    }

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

    private boolean sneak(int round, Character player) {
        if(!isFirstRound.test(round) || !haveTheDrop.test(player.getInitiative())){
            Display.displayText("The enemies have already seen you. You cannot sneak.");
            return false;
        }
        boolean successful = isSneakSuccessful(enemies);
        String msg = successful ? "You sneak past the enemies" : "You were caught sneaking";
        System.out.println(msg);
        return successful;
    }

    private boolean isSneakSuccessful(List<Character> enemies) {
        return enemies.parallelStream()
                .mapToInt(e -> Dice.rollTheDie(Numbers.TWENTY.getValue()))
                .peek(System.out::println)
                .noneMatch(bustedSneaking::test);
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
        Character enemy = enemies.get(enemyIndex);
        int successRoll = Dice.rollTheDie(Numbers.TWENTY.getValue());;
        if(isIntimidated.test(successRoll)){
            scaredAway(enemies, enemy);
        } else {
            failedIntimidation(enemy);
        }
    }

    private static void failedIntimidation(Character enemy) {
        enemy.setHitPoints(CharacterStatus.increaseHealth(enemy, Numbers.FIVE.getValue()));
        Display.displayText("You failed at your intimidation attempt and " +
                enemy.getName() + " is definitely not scared of you" +
                "\n" + enemy.getName() + " gained 5 HP");
    }

    private static void scaredAway(List<Character> enemies, Character enemy) {
        enemies.remove(enemy);
        enemy.setLocation(LocationBuilder.getRandomLocation());
        Display.displayText("You scared " + enemy.getName() + " so bad it ran away");
    }

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

    private void attack(Character attacker, Character victim) {
        String severity = determineSeverity();
        int damageDealt = determineDamage(attacker, severity);
        displayAttackDetails(attacker, victim, severity, damageDealt);
        victim.setHitPoints(victim.getHitPoints() - damageDealt);
        victim.setIsAlive(checkStillAlive(victim));
    }

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
