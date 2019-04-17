package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import javax.xml.soap.Text;

public class Brick {
    float x;
    float y;
    Texture texture;
    int width;
    int height;
    int type;

    public Brick(float x, float y,  int width, int height, int type){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.texture = setTexture(type);
    }

    public Texture setTexture(int type){
        if(type == 1){
            Texture brick1 = new Texture("bricks/brick1.png");
            this.texture = brick1;
            return brick1;
        }else if(type == 2){
            Texture brick2 = new Texture("bricks/brick2.png");
            this.texture = brick2;
            return brick2;
        }
        else if(type == 3){
            Texture brick3 = new Texture("bricks/brick3.png");
            this.texture = brick3;
            return brick3;
        }else if(type == 4){
            Texture brick4 = new Texture("bricks/brick4.png");
            this.texture = brick4;
            return brick4;
        }
        return null;
    }

    public void isHit(Ball ball){

        // bottom
        if(ball.y + ball.height > this.y
                && ball.y < this.y + this.height / 2
                && ball.x > this.x
                && ball.x + ball.width < this.x + this.width) {

            Gdx.app.log("brick a","i hit the bottom");
        }

        else if(ball.x + ball.width > this.x
        && ball.y > this.y
        && ball.y + ball.height < this.y + this.height
        && ball.x < this.x + this.width/2){
            Gdx.app.log("brick a","i hit the left");
        }
        else if(ball.x < this.x + this.width
                && ball.y > this.y
                && ball.y + ball.height < this.y + this.height
        && ball.x + ball.width > this.x + this.width/2 ){
            Gdx.app.log("brick a","i hit the right");
        }
        else if(ball.x > this.x
        && ball.x + ball.width < this.x + this.width
        && ball.y  < this.y + this.height
        && ball.y + ball.height > this.y){
            Gdx.app.log("brick a","i hit the top");
        }
    }




}
