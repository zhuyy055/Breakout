package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"

    int currentStatus;
    Texture startGame;

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


        startGame = new Texture("save Game.png");


    }
    public void render(float f) {
        Gdx.gl.glClearColor( 1f, 226/255f, 232/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

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

        }// pause
        else if(currentStatus == 2){
            Gdx.app.log("Pause","pause button");
        }// win
        else if(currentStatus == 3){

        }// loose
        else if(currentStatus == 4){

        }

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
