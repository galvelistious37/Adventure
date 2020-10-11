package com.johnny.pack.age;

import java.util.Scanner;

class UserInput {
    private Scanner scanner;
    private static UserInput INSTANCE = new UserInput();

    private UserInput(){
        this.scanner = new Scanner(System.in);
    }

    static UserInput getUserInstance(){
        return INSTANCE;
    }

    Scanner getScanner() {
        return scanner;
    }

    Boolean isInputYes(){
        String eatThem = scanner.nextLine();
        return eatThem.equalsIgnoreCase("YES")
                || eatThem.equalsIgnoreCase("Y");
    }

    Boolean scannerHasNextInt(){
        return scanner.hasNextInt();
    }

    void scannerNextLine(){
        scanner.nextLine();
    }
}
