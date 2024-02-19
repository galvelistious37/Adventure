package com.johnny.pack.age.controller.status;

import com.johnny.pack.age.controller.dice.Dice;
import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.characterfactory.character.Player;
import com.johnny.pack.age.model.constant.Numbers;

import java.util.ArrayList;
import java.util.List;

public class CharacterStatus {
    private Dice dice = Dice.getInstance();

    /**
     * stream through characters and find any alive
     * @return - boolean a character is alive
     */
    public boolean areAnyCharactersAlive(List<Character> characters) {
        return characters.stream().anyMatch(Character::getIsAlive);
    }



    /**
     * Set the initiative value for all characters
     */
    public void setCharacterInitiatives(List<Character> characters) {
        List<Character> everyone = new ArrayList<>();
        everyone.add(Player.getInstance());
        everyone.addAll(characters);
        everyone.forEach(e ->
                e.setInitiative(dice.rollTheDie(Numbers.TWENTY.getValue())));
    }

    public int increaseHealth(Character character, int amount){
        return character.getHitPoints() + amount;
    }
}
