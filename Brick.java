package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Brick {
    float x;
    float y;
    Texture texture;
    int width;
    int height;

    public Brick(float x, float y, Texture texture, int width, int height){
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    public void changePosition(float x, float y){
        this.x = x;
        this.y = y;
    }


}
