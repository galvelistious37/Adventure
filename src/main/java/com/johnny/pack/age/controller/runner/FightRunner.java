package com.johnny.pack.age.controller.runner;

import com.johnny.pack.age.controller.attack.Fight;
import com.johnny.pack.age.controller.builder.EnemyBuilder;
import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.view.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FightRunner {
    private int id;
    private Character player;
    private List<Character> enemies;

    public FightRunner(int id, List<Character> enemies){
        this.id = id;
        this.enemies = enemies;
        player = Player.getInstance();
    }

    public void runFightTask(){
        setCharacterInitiatives();
        Fight fight = new Fight(enemies);
        if(fight.areEnemiesAlive()) {
            fight.doFightinStuff();
        } else {
            Display.getDisplayInstance.displayText("All enemies here are dead");
        }
    }

    /**
     * Set the initiative value for all characters
     */
    public void setCharacterInitiatives() {
        List<Character> everyone = new ArrayList<>();
        everyone.add(player);
        everyone.addAll(enemies);
        everyone.forEach(e ->
                e.setInitiative(Dice.rollTheDie(Numbers.TWENTY.getValue())));
    }


}
