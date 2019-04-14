package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
public class GameScreen implements Screen {
    MyGdxGame game; // Note it’s "MyGdxGame" not "Game"
    // constructor to keep a reference to the main Game class
    public GameScreen(MyGdxGame game){
        this.game = game;
    }
    public void create() {
        Gdx.app.log("GameScreen: ","menuScreen create");
    }
    public void render(float f) {
        Gdx.app.log("GameScreen: ","GameScreen render");

        game.setScreen(MyGdxGame.gameScreen);
        Gdx.app.log("GameScreen: ","gameScreen started");
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
