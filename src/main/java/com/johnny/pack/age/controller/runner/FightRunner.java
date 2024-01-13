package com.johnny.pack.age.controller.runner;

import com.johnny.pack.age.controller.attack.Fight;
import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.controller.status.CharacterStatus;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.view.Display;

import java.util.ArrayList;
import java.util.List;

public class FightRunner {
    private Character player;
    private List<Character> enemyList;

    public static FightRunner getFightRunner(List<Character> enemyList){
        return new FightRunner(enemyList);
    }

    private FightRunner(List<Character> enemies){
        this.enemyList = enemies;
        player = Player.getInstance();
    }

    public void runFightTask(){
        Fight fight = new Fight(enemyList);
        int round = Numbers.ZERO.getValue();
        boolean quit = false;

        if(!CharacterStatus.areAnyCharactersAlive(enemyList)){
            Display.getDisplayInstance.displayText("All enemies here are dead");
            return;
        }

        CharacterStatus.setCharacterInitiatives(enemyList);

        while(!quit){
            Display.getDisplayInstance.showDisplays(player, enemyList);
            round++;
            quit = fight.goThroughFightOrder(round);

            // if there are no enemies left
            if(enemyList.size() == Numbers.ZERO.getValue()){
                break;
            }

            if (!CharacterStatus.areAnyCharactersAlive(enemyList)){
                Display.getDisplayInstance.displayText("You have painted these lands " +
                        "with blood of your enemies");
                if(fight.eatTheDead()){
                    fight.digestTheDead(player);
                }
                break;
            }
        }
    }
}
