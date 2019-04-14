package com.mygdx.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MyGdxGame extends Game implements ApplicationListener {
	// The class with the menu
	public static MenuScreen menuScreen;
	// The class with the game
	public static GameScreen gameScreen;

	public static Scores scores;

	public int[] totalscores;

	public Music bgm;

	public MyGdxGame(){

	}
	@Override
	public void create() {
		Gdx.app.log("MyGdxGame: "," create");
		gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        scores = new Scores(this);
		Gdx.app.log("MyGdxGame: ","about to change screen to menuScreen");
		// Change screens to the menu
		setScreen(menuScreen);
		Gdx.app.log("MyGdxGame: ","changed screen to menuScreen");



        // make scores
        totalscores = new int[10];
        totalscores[0] = 189;
        totalscores[1] = 91;
        totalscores[2] = 67;
        totalscores[3] = 43;
        totalscores[4] = 0;
        totalscores[5] = 0;
        totalscores[6] = 0;
        totalscores[7] = 0;
        totalscores[8] = 0;
        totalscores[9] = 0;


		bgm = Gdx.audio.newMusic(Gdx.files.internal("piano.mp3"));
		bgm.play();
		bgm.setLooping(true);


	}

	@Override
	public void dispose() {super.dispose();
		bgm.dispose();}
	@Override
	// this method calls the super class render
	// which in turn calls the render of the actual screen being used
	public void render() {super.render();}
	@Override
	public void resize(int width, int height) { super.resize(width, height);}
	@Override
	public void pause() {
		super.pause();
	}
	@Override
	public void resume() {
		super.resume();
	}
}
