package com.johnny.pack.age.controller.Move;

import com.johnny.pack.age.model.characterfactory.character.Character;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.view.Display;

import java.util.List;
import java.util.Scanner;

public class UserInput {
    private Scanner scanner;
    private static UserInput INSTANCE = new UserInput();

    private UserInput(){
        this.scanner = new Scanner(System.in);
    }

    public static UserInput getUserInstance(){
        return INSTANCE;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Boolean isInputYes(){
        String eatThem = scanner.nextLine();
        return eatThem.equalsIgnoreCase("YES")
                || eatThem.equalsIgnoreCase("Y");
    }

    public Boolean scannerHasNextInt(){
        return scanner.hasNextInt();
    }

    public void scannerNextLine(){
        scanner.nextLine();
    }

    public boolean validateEnemySelection(String userInput) {
        return Display.getAcceptableNumbers().stream()
                .noneMatch(input -> input.equalsIgnoreCase(userInput));
    }
}
