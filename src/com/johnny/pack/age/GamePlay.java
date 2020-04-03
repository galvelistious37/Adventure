package com.johnny.pack.age;

import java.io.*;

public class GamePlay {
    Player player;

    public GamePlay (Player player){
        this.player = player;
    }

    public void playTheGame(){
        System.out.println("Your player deetz");
        System.out.println("Name: " + player.getName());
        System.out.println("Hit Points: " + player.getHitpoints());
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Weapon: " + player.getWeapon());
        System.out.println("Location: " + player.getLocation());
//        saveGame(player);
    }

    public void saveGame(ISaveable objectToSave){
        String directory = ("./Files/");
        String fileName = ("save_file.txt");
        String absolutePath = directory + fileName;

        try(FileWriter fw = new FileWriter(absolutePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            String saveString = "";
            for(int i = 0; i < objectToSave.write().size(); i++){
                saveString = saveString + (objectToSave.write().get(i)) + ",";
             }
            out.println(saveString);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}
