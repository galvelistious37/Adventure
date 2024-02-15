package com.johnny.pack.age.controller.runner;

import com.johnny.pack.age.controller.Move.UserInput;
import com.johnny.pack.age.model.constant.Numbers;
import com.johnny.pack.age.view.Display;

public class FightOptions {

    /**
     * Determine whether user input is valid
     * @return - int value of user input
     */
    public static int getSelection() {
        while(true) {
            Display.getFightMenu();
            if(UserInput.getUserInstance().scannerHasNextInt()){
                int selection =  Integer.parseInt(UserInput.getUserInstance().getScanner().nextLine());
                if(selection > Numbers.ZERO.getValue() && selection < Numbers.FOUR.getValue()){
                    return selection;
                }
            }
            Display.displayText("Select a valid option");
            UserInput.getUserInstance().getScanner().nextLine();
        }
    }
}
