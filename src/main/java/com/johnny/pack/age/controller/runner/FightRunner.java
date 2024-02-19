package com.johnny.pack.age.controller.runner;

import com.johnny.pack.age.controller.GamePlay;
import com.johnny.pack.age.controller.attack.Fight;
import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.controller.status.CharacterStatus;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.view.Display;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FightRunner {
    private final Character player;
    private final List<Character> enemyList;
    private final Fight fight;
    private CharacterStatus characterStatus = new CharacterStatus();
    private int round;
    private final Predicate<Boolean> isQuitting = b -> b;

    public static FightRunner getFightRunner(List<Character> enemyList){
        return new FightRunner(enemyList, Player.getInstance(), new Fight(enemyList, Dice.getInstance()));
    }

    private FightRunner(List<Character> enemies, Character player, Fight fight){
        this.enemyList = enemies;
        this.player = player;
        this.fight = fight;
    }

    public void runFightTask(){
        if(areEnemiesDead()) return;

        round = Numbers.ZERO.getValue();
        characterStatus.setCharacterInitiatives(enemyList);

        boolean quit = false;
        while(!quit){
            quit = performFightTasks();
            if (didEnemiesLeave()) break;
            if (areEnemiesDead()){
                playerHealthBonus();
                break;
            }
        }
    }

    private boolean performFightTasks() {
        Display.showDisplays(player, enemyList);
        round++;
        return goThroughFightOrder();
    }

    private void playerHealthBonus() {
        player.setHitPoints(characterStatus.increaseHealth(player, 10));
        Display.displayText("Your health has increased to: " 
                + player.getHitPoints());
    }

    private boolean areEnemiesDead(){
        boolean alive = characterStatus.areAnyCharactersAlive(enemyList);
        if(!alive){
            Display.displayText("All enemies are dead");
        }
        return !alive;
    }

    private boolean didEnemiesLeave() {
        return enemyList.size() == Numbers.ZERO.getValue();
    }

    private boolean goThroughFightOrder() {
        List<Character> all = new ArrayList<>();
        all.add(player);
        all.addAll(enemyList);

        Stream<Character> ordered = all.stream()
                .sorted(Comparator
                        .comparing(Character::getInitiative)
                        .reversed());

        return ordered
                .filter(Character::getIsAlive)
                .map(this::getActionType).anyMatch(isQuitting);
    }

    private boolean getActionType(Character character){
        if(character instanceof Player){
            return getPlayerChoice(round);
        } else {
            fight.attack(character, player);
        }
        if(!player.getIsAlive()){
            playerDied();
        }
        return false;
    }

    private boolean getPlayerChoice(int round) {
        int action = FightOptions.getSelection();
        return fight.tryAction(action, round);
    }

    private void playerDied() {
        Display.displayText("You died!\nGame Over");
        GamePlay.getInstance().quit();
    }
}
