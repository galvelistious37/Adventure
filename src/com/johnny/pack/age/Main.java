package com.johnny.pack.age;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        GamePlay playTheGame = new GamePlay();
        playTheGame.play();
    }

    private static void play() {
        continuePlay();
    }

    /**
     * Take player input selection for opening menu options
     */
    private static void continuePlay() {
        GamePlay gamePlay = new GamePlay();
        Scanner scanner = new Scanner(System.in);

    }

    public static void quit() {
        System.out.println("Shutting down...");
        System.exit(0);
    }
}
