package com.mygdx.game;

public class Ball {
    float x;
    float y;
    float width;
    float height;
    float speed;
    float dirX;
    float dirY;

    public Ball(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        speed = 4f;
        dirX = 1f;
        dirY = 1f;


    }

    public void changePosition(float x, float y){
        this.x = x;
        this.y = y;

    }
}
