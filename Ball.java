package com.mygdx.game;

import com.badlogic.gdx.Gdx;

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
        // right screen
        if(this.x + this.width > Gdx.graphics.getWidth()){
            dirX = dirX * -1;
            // left screen
        }else if(this.x < 0){

            dirX = dirX * -1;
            // top
        }else if(this.y + this.height > Gdx.graphics.getHeight()){
            dirY = dirY * -1;
        }

    }

    // hit bar
    public void ishitBar(Bar bar){
        if(this.y < bar.y + bar.height
        && this.x < bar.x + bar.width
        && this.x + this.width > bar.x){
            //Gdx.app.log("test","is hit bar");
            //dirX = dirX * -1;
            dirY = dirY * -1;

        }

    }
}
