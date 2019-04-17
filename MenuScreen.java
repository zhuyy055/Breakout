package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MenuScreen implements Screen {

    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    TextButton playgamebutton;
    TextButton exitgamebutton;
    TextButton musicbutton;
    TextButton highscorebutton;

    // constructor to keep a reference to the main Game class
    public MenuScreen(MyGdxGame game){
        this.game = game;
    }
    public void create() {
        Gdx.app.log("MenuScreen: ","menuScreen create");

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();

        playgamebutton = new TextButton("PLAY", skin, "default");
        playgamebutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        playgamebutton.setHeight(Gdx.graphics.getHeight()*0.2f);
        playgamebutton.setPosition(0 ,
                Gdx.graphics.getHeight() - playgamebutton.getHeight());
        playgamebutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen( new GameScreen(game) );
            }
        } );
        stage.addActor(playgamebutton);
        Gdx.input.setInputProcessor(stage);


        exitgamebutton = new TextButton("Exit", skin, "default");
        exitgamebutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        exitgamebutton.setHeight(Gdx.graphics.getHeight()*0.2f);
        exitgamebutton.setPosition(Gdx.graphics.getWidth() - exitgamebutton.getWidth(),
                0);
        exitgamebutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        } );
        stage.addActor(exitgamebutton);
        Gdx.input.setInputProcessor(stage);


        musicbutton = new TextButton("Music", skin, "default");
        musicbutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        musicbutton.setHeight(Gdx.graphics.getHeight()*0.2f);
        musicbutton.setPosition(0,
                0);
        musicbutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.bgm.isPlaying()) {
                    game.bgm.pause();

                }else{
                    game.bgm.play();
                }
            }
        } );
        stage.addActor(musicbutton);
        Gdx.input.setInputProcessor(stage);


        highscorebutton = new TextButton("Scores", skin, "default");
        highscorebutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        highscorebutton.setHeight(Gdx.graphics.getHeight()*0.2f);
        highscorebutton.setPosition(Gdx.graphics.getWidth() - highscorebutton.getWidth(),
                Gdx.graphics.getHeight() - highscorebutton.getHeight());
        highscorebutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen( new Scores(game));
            }
        } );
        stage.addActor(highscorebutton);
        Gdx.input.setInputProcessor(stage);


    }
    public void render(float f) {
        Gdx.gl.glClearColor( 226/255f, 241/255f, 1f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.begin();
        stage.draw();
        batch.end();
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
        Gdx.app.log("MenuScreen: ","menuScreen show called");
        create(); }
    @Override
    public void hide() {
        Gdx.app.log("MenuScreen: ","menuScreen hide called");
    }
}