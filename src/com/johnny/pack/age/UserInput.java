package com.johnny.pack.age;

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
}
