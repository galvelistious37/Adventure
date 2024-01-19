package com.johnny.pack.age.controller.runner;

import com.johnny.pack.age.controller.GamePlay;
import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.controller.attack.Fight;
import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.controller.status.CharacterStatus;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FightRunner {
    private Character player;
    private List<Character> enemyList;
    private Fight fight;

    public static FightRunner getFightRunner(List<Character> enemyList){
        return new FightRunner(enemyList);
    }

    private FightRunner(List<Character> enemies){
        this.enemyList = enemies;
        player = Player.getInstance();
        fight = new Fight(enemyList);
    }

    public void runFightTask(){
        int round = Numbers.ZERO.getValue();
        boolean quit = false;

        if(!CharacterStatus.areAnyCharactersAlive(enemyList)){
            Display.displayText("All enemies here are dead");
            return;
        }

        CharacterStatus.setCharacterInitiatives(enemyList);

        while(!quit){
            Display.showDisplays(player, enemyList);
            round++;
            quit = goThroughFightOrder(round);

            // if there are no enemies left
            if(enemyList.size() == Numbers.ZERO.getValue()){
                break;
            }

            if (!CharacterStatus.areAnyCharactersAlive(enemyList)){
                player.setHitPoints(CharacterStatus.increaseHealth(player, 10));
                Display.displayText("You have painted these lands " +
                        "with blood of your enemies \nYour health has " +
                        "increased to: " + player.getHitPoints());
                break;
            }
        }
    }

    private boolean goThroughFightOrder(int round) {

        // Counting down from 20, check player initiative
        for(int initiative = Numbers.TWENTY.getValue(); initiative > Numbers.ZERO.getValue(); initiative--){
            if(player.getInitiative() == initiative){
                if(getPlayerChoice(round)){
                    return true;
                }
            }
            enemyTurn(initiative);
            if(!player.getIsAlive()){
                    playerDied();
                return true;
            }
        }
        return false;
    }

    private void enemyTurn(int initiative) {
        enemyList.stream()
                .filter(e -> e.getInitiative() == initiative)
                .filter(Character::getIsAlive)
                .forEach(e -> fight.enemyAction(player, e));
    }

    private boolean getPlayerChoice(int round) {
        int action = FightOptions.getSelection();
        return fight.tryAction(action, round);
    }


    /**
     * End game when player dies
     */
    private void playerDied() {
        Display.displayText("You died!\nGame Over");
        GamePlay.getInstance().quit();
    }
}
