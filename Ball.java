package com.mygdx.game;

public class Ball {
    int x;
    int y;
    int width;
    int height;

    public Ball(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void changePosition(int x, int y){
        this.x = x;
        this.y = y;

    }
}
