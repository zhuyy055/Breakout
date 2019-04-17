package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Scores implements Screen {

    MyGdxGame game;
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;

    Label one;
    Label two;
    Label three;
    Label four;
    Label five;
    Label six;
    Label seven;
    Label eight;
    Label nine;
    Label ten;

    TextButton returnbutton;


    public Scores(MyGdxGame game){
        this.game = game;
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("gui/uiskin.json"));
        stage = new Stage();

    }

    @Override
    public void show() {

        one = new Label("1: " + game.totalscores[0], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        one.setPosition(Gdx.graphics.getWidth()/2 - one.getWidth()/2
                ,Gdx.graphics.getHeight()*0.8f);
        stage.addActor(one);

        two = new Label("2: " + game.totalscores[1], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        two.setPosition(Gdx.graphics.getWidth()/2 - two.getWidth()/2
                ,Gdx.graphics.getHeight()*0.75f);
        stage.addActor(two);


        three = new Label("3: " + game.totalscores[2], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        three.setPosition(Gdx.graphics.getWidth()/2 - three.getWidth()/2
                ,Gdx.graphics.getHeight()*0.7f);
        stage.addActor(three);

        four = new Label("4: " + game.totalscores[3], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        four.setPosition(Gdx.graphics.getWidth()/2 - four.getWidth()/2
                ,Gdx.graphics.getHeight()*0.65f);
        stage.addActor(four);

        if(game.totalscores[4] == 0) {
            five = new Label("5: " + 0, new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }else{
            five = new Label("5: " + game.totalscores[4], new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        }
        five.setPosition(Gdx.graphics.getWidth()/2 - five.getWidth()/2
                ,Gdx.graphics.getHeight()*0.6f);
        stage.addActor(five);

        if(game.totalscores[5] == 0) {
            six = new Label("6: " + game.totalscores[5], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }else{
            six = new Label("6: " + game.totalscores[5], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }
        six.setPosition(Gdx.graphics.getWidth()/2 - six.getWidth()/2
                ,Gdx.graphics.getHeight()*0.55f);
        stage.addActor(six);

        if(game.totalscores[6] == 0) {
            seven = new Label("7: " + game.totalscores[6], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }else {
            seven = new Label("7: " + game.totalscores[6], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }
        seven.setPosition(Gdx.graphics.getWidth()/2 - seven.getWidth()/2
                ,Gdx.graphics.getHeight()*0.5f);
        stage.addActor(seven);

        if(game.totalscores[7] == 0) {
            eight = new Label("8: " + game.totalscores[7], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }else{
            eight = new Label("8: " + game.totalscores[7], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }
        eight.setPosition(Gdx.graphics.getWidth()/2 - eight.getWidth()/2
                ,Gdx.graphics.getHeight()*0.45f);
        stage.addActor(eight);

        if(game.totalscores[8] == 0) {
            nine = new Label("9: " + game.totalscores[8], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }else{
            nine = new Label("9: " + game.totalscores[8], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }
        nine.setPosition(Gdx.graphics.getWidth()/2 - nine.getWidth()/2
                ,Gdx.graphics.getHeight()*0.4f);
        stage.addActor(nine);

        if(game.totalscores[9] == 0) {
            ten = new Label("10: " + game.totalscores[9], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }else{
            ten = new Label("10: " + game.totalscores[9], new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }
        ten.setPosition(Gdx.graphics.getWidth()/2 - ten.getWidth()/2
                ,Gdx.graphics.getHeight()*0.35f);
        stage.addActor(ten);

        returnbutton = new TextButton("Return", skin, "default");
        returnbutton.setWidth(Gdx.graphics.getWidth()*0.2f);
        returnbutton.setHeight(Gdx.graphics.getHeight()*0.2f);
        returnbutton.setPosition(0 ,
                0);
        returnbutton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen( new MenuScreen(game) );
            }
        } );
        stage.addActor(returnbutton);
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 226/255f, 241/255f, 1f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
