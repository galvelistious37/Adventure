package com.johnny.pack.age;

public class FilePath {
    private String path;
    private String name;
    private String absolutePath;

    public FilePath() {
        this.path = "./Files/";
        this.name = "save_file.txt";
        this.absolutePath = path + name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }
}
