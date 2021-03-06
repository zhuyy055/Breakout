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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    TextButton pausegamebutton;
    TextButton returnbutton;
    TextButton retrybutton;

    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"

    Brick[] brickArray;
    Ball ball;
    Bar bar;

    int currentStatus;
    Texture startGame;
    Texture ballTexture;
    Texture barTexture;

    int score;
    Label scoreLabel;


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

        score = 0;
        scoreLabel = new Label("Score is: " + score, skin);
        scoreLabel.setPosition(Gdx.graphics.getWidth() * 0.6f,0);
        stage.addActor(scoreLabel);
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
            init();


            batch.begin();
            batch.draw(startGame, Gdx.graphics.getWidth()/2 - startGame.getWidth()/2
                    , Gdx.graphics.getHeight()/2 - startGame.getHeight()/2);
            batch.end();

            // start the game
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
                    if(brickArray[i].isHit(ball) == true){
                        score = score + 1;
                        scoreLabel.setText("Score is: " + score);
                        Gdx.app.log("score is: ", "sss " + score);
                        brickArray[i] = null;
                    }
                    batch.end();
                }
            }

            // check bricks
            boolean allgone = true;
            for(int i = 0; i < brickArray.length; i++){
                if(brickArray[i] != null){
                    allgone = false;
                }
            }
            // win!!!
            if(allgone == true){
                currentStatus = 3;
                addtoScore();
            }


            batch.begin();
            batch.draw(ballTexture, ballX + ball.speed * ball.dirX, ballY + ball.speed * ball.dirY);
            ball.changePosition(ballX + ball.speed * ball.dirX, ballY + ball.speed * ball.dirY);
            batch.end();


/*
            batch.begin();
            batch.draw(ballTexture, Gdx.input.getX() - ball.width/2, Gdx.graphics.getHeight() - Gdx.input.getY() - ball.height/2);
            ball.changePosition(Gdx.input.getX() - ball.width/2, Gdx.graphics.getHeight() - Gdx.input.getY() - ball.height/2);
            batch.end();
*/
            // you loose
            if(ballY < 0){
                currentStatus = 4;
                addtoScore();
            }


            // change ball direction
            ball.ishitBar(bar);

            float barSpeed = 4f;
            // change bar position
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(bar.x > 0){
                    bar.changePosition(barX - barSpeed,barY);
                }
            }

            else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if(bar.x < Gdx.graphics.getWidth() - bar.width) {
                    bar.changePosition(barX + barSpeed, barY);
                }
            }
            // cursor
            else if(Gdx.input.isTouched()){

                if(Gdx.input.getX() < Gdx.graphics.getWidth() - bar.width && Gdx.input.getX() > 0) {
                    bar.changePosition( Gdx.input.getX(),barY);
                }


            }

            batch.begin();
            batch.draw(barTexture, bar.x, bar.y);
            batch.end();

            /*
            batch.begin();
            batch.draw(barTexture, Gdx.graphics.getWidth() / 2 - barTexture.getWidth()/2, pausegamebutton.getHeight());
            batch.end();
            */

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

            batch.begin();
            batch.draw(barTexture, bar.x, bar.y);
            batch.end();


            batch.begin();
            batch.draw(ballTexture, ballX, ballY);
            batch.end();


            winScreen();
        }// loose
        else if(currentStatus == 4){

            for(int i = 0; i < brickArray.length; i++) {

                // draw brick
                if(brickArray[i]!=null) {
                    batch.begin();
                    batch.draw(brickArray[i].texture, brickArray[i].x, brickArray[i].y);
                    if(brickArray[i].isHit(ball) == true){
                        brickArray[i] = null;
                    }
                    batch.end();
                }
            }


            batch.begin();
            batch.draw(barTexture, bar.x, bar.y);
            batch.end();


            batch.begin();
            batch.draw(ballTexture, ballX, ballY);
            batch.end();


            looseScreen();
        }
    }

    public void winScreen(){
        Texture winpic = new Texture("win.png");
        batch.begin();
        batch.draw(winpic,Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()*0.3f/2, Gdx.graphics.getHeight()/2 - Gdx.graphics.getWidth()*0.2f/2,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getWidth()*0.2f);
        batch.end();

        retrybutton.setVisible(true);


    }


    public void looseScreen(){

        Texture loosepic = new Texture("loose.png");

        batch.begin();
        batch.draw(loosepic,Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()*0.3f/2, Gdx.graphics.getHeight()/2 - Gdx.graphics.getWidth()*0.2f/2,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getWidth()*0.2f);
        batch.end();

        retrybutton.setVisible(true);


    }


    public void addtoScore(){
        // add score to list
        int index = 0;
        while(index < game.totalscores.length && game.totalscores[index + 1] != 0 ){
            index = index + 1;
        }
        game.totalscores[index + 1 ] = score;
    }


    public void init(){
        score = 0;
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


        retrybutton =   new TextButton("retry", skin, "default");
        retrybutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        retrybutton.setHeight(Gdx.graphics.getHeight()*0.08f);
        retrybutton.setPosition(Gdx.graphics.getWidth()/2 - retrybutton.getWidth() /2  ,
                0);
        retrybutton.setVisible(false);
        retrybutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                currentStatus = 0;
                retrybutton.setVisible(false);

            }
        } );
        stage.addActor(retrybutton);
        Gdx.input.setInputProcessor(stage);


        startGame = new Texture("save Game.png");

        int index = 0;
        int fromTop = Gdx.graphics.getHeight() - 16;

        int brickWidth = 32;
        int brickHeight = 16;

        // init brick position
        for(int type = 1; type <= 4; type++){
            for(int x = 0; x < Gdx.graphics.getWidth(); x = x + 32){
                // put brick position
                // draw from top
                brickArray[index] = new Brick(x,fromTop - brickHeight*type ,brickWidth,brickHeight,type);
                index++;
            }
        }

        ballTexture = new Texture("bricks/ball.png");

        barTexture = new Texture("bricks/bar.png");


        // brick ball bar
        bar = new Bar(Gdx.graphics.getWidth()/2 - barTexture.getWidth()/2, pausegamebutton.getHeight(),64,16);
        ball = new Ball(Gdx.graphics.getWidth()/2 - ballTexture.getWidth()/2, pausegamebutton.getHeight() + bar.height,8,8);

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
