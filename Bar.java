package com.mygdx.game;

public class Bar {

    float x;
    float y;
    float width;
    float height;

    public Bar(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void changePosition(float x, float y){
        this.x = x;
        this.y = y;
    }



}
