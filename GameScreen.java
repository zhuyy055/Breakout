package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    TextButton pausegamebutton;
    TextButton returnbutton;
    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"

    Brick[] brickArray;
    Ball ball;
    Bar bar;

    int currentStatus;
    Texture startGame;
    Texture ballTexture;
    Texture barTexture;


    public GameScreen(MyGdxGame game){
        this.game = game;
    }
    public void create() {
        Gdx.app.log("GameScreen: ","menuScreen create");

        // ready to run the game
        currentStatus = 0;


        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();

        brickArray = new Brick[80];

        init();


        // brick ball bar
        bar = new Bar(Gdx.graphics.getWidth()/2 - barTexture.getWidth()/2, pausegamebutton.getHeight(),64,16);
        ball = new Ball(Gdx.graphics.getWidth()/2 - ballTexture.getWidth()/2, pausegamebutton.getHeight() + bar.height,8,8);


    }

    public void render(float f) {
        Gdx.gl.glClearColor( 1f, 226/255f, 232/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        float ballX = ball.x;
        float ballY = ball.y;
        float barX = bar.x;
        float barY = bar.y;


        batch.begin();
        // draw button
        stage.draw();
        batch.end();

        // ready to run
        if(currentStatus == 0){
            batch.begin();
            batch.draw(startGame, Gdx.graphics.getWidth()/2 - startGame.getWidth()/2
                    , Gdx.graphics.getHeight()/2 - startGame.getHeight()/2);
            batch.end();
            if(Gdx.input.isTouched() == true){
                currentStatus = 1;
            }
        }// running
        else if(currentStatus == 1){
            for(int i = 0; i < brickArray.length; i++) {

                // draw brick
                if(brickArray[i]!=null) {
                    batch.begin();
                    batch.draw(brickArray[i].texture, brickArray[i].x, brickArray[i].y);
                    brickArray[i].isHit(ball);
                    batch.end();
                }
            }

            //if()
/*
            batch.begin();
            batch.draw(ballTexture, ballX, ballY);
            batch.end();
*/

            batch.begin();
            batch.draw(ballTexture, Gdx.input.getX() - ball.width/2, Gdx.graphics.getHeight() - Gdx.input.getY() - ball.height/2);
            ball.changePosition(Gdx.input.getX() - ball.width/2, Gdx.graphics.getHeight() - Gdx.input.getY() - ball.height/2);
            batch.end();


            // change bar position
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(bar.x > 0){
                    bar.changePosition(barX - 3f,barY);
                }

            }

            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if(bar.x < Gdx.graphics.getWidth() - bar.width) {
                    bar.changePosition(barX + 3f, barY);
                }
            }

            batch.begin();
            batch.draw(barTexture, bar.x, bar.y);
            batch.end();
/*
            batch.begin();
            batch.draw(barTexture, Gdx.graphics.getWidth() / 2 - barTexture.getWidth()/2, pausegamebutton.getHeight());
            batch.end();*/


        }// pause
        else if(currentStatus == 2){
            Gdx.app.log("Pause","pause button");

            for(int i = 0; i < brickArray.length; i++) {

                // draw brick
                if(brickArray[i]!=null) {
                    batch.begin();
                    batch.draw(brickArray[i].texture, brickArray[i].x, brickArray[i].y);
                    batch.end();
                }
            }

            batch.begin();
            batch.draw(ballTexture, ballX, ballY);
            batch.end();

            batch.begin();
            batch.draw(barTexture, bar.x, bar.y);
            batch.end();


        }// win
        else if(currentStatus == 3){

        }// loose
        else if(currentStatus == 4){

        }
    }

    public void init(){
        pausegamebutton = new TextButton("Pause", skin, "default");
        pausegamebutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        pausegamebutton.setHeight(Gdx.graphics.getHeight()*0.08f);
        pausegamebutton.setPosition(0 ,
                0);
        pausegamebutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Gdx.app.log("Pause","pause button");
                // when the game is running
                if(currentStatus == 1){
                    currentStatus = 2;
                    pausegamebutton.setText("CONTINUES");

                }// when the game is not running
                else if(currentStatus == 2){
                    currentStatus = 1;
                    pausegamebutton.setText("PAUSE");
                }
            }
        } );
        stage.addActor(pausegamebutton);
        Gdx.input.setInputProcessor(stage);

        returnbutton = new TextButton("Return", skin, "default");
        returnbutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        returnbutton.setHeight(Gdx.graphics.getHeight()*0.08f);
        returnbutton.setPosition(Gdx.graphics.getWidth() - returnbutton.getWidth(),
                0);
        returnbutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen( new MenuScreen(game) );
            }
        } );
        stage.addActor(returnbutton);
        Gdx.input.setInputProcessor(stage);

        startGame = new Texture("save Game.png");

        int index = 0;
        int fromTop = Gdx.graphics.getHeight() - 16;

        int brickWidth = 32;
        int brickHeight = 16;

        for(int type = 1; type <= 4; type++){
            for(int x = 0; x < Gdx.graphics.getWidth(); x = x + 64){
                // put brick position
                brickArray[index] = new Brick(x,fromTop - brickHeight*type ,brickWidth,brickHeight,type);
                index++;
            }
        }

        ballTexture = new Texture("bricks/ball.png");

        barTexture = new Texture("bricks/bar.png");

    }
    @Override
    public void dispose() { }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() {
        Gdx.app.log("GameScreen: ","menuScreen show called");
        create(); }
    @Override
    public void hide() {
        Gdx.app.log("GameScreen: ","menuScreen hide called");
    }
}
