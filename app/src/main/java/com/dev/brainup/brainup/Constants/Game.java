package com.dev.brainup.brainup.Constants;

import java.util.ArrayList;

public class Game {
    private int gameID;
    private String gametype;
    private int level;
    private String image;
    private String resultfalse1;
    private String resultfalse2;
    private String resulttrue;
    public Game() {
    }

    public Game(String gametype, int level, String image, String resultfalse1, String resultfalse2, String resulttrue) {
        this.gametype = gametype;
        this.level = level;
        this.image = image;
        this.resultfalse1 = resultfalse1;
        this.resultfalse2 = resultfalse2;
        this.resulttrue = resulttrue;
    }

    public Game(int gameID, String gametype, int level, String image, String resultfalse1, String resultfalse2, String resulttrue) {
        this.gameID = gameID;
        this.gametype = gametype;
        this.level = level;
        this.image = image;
        this.resultfalse1 = resultfalse1;
        this.resultfalse2 = resultfalse2;
        this.resulttrue = resulttrue;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getResultfalse1() {
        return resultfalse1;
    }

    public void setResultfalse1(String resultfalse1) {
        this.resultfalse1 = resultfalse1;
    }

    public String getResultfalse2() {
        return resultfalse2;
    }

    public void setResultfalse2(String resultfalse2) {
        this.resultfalse2 = resultfalse2;
    }

    public String getResulttrue() {
        return resulttrue;
    }

    public void setResulttrue(String resulttrue) {
        this.resulttrue = resulttrue;
    }

    @Override
    public String toString() {
        return this.gametype;
    }
}

