package com.badlogic.drop;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class My_game implements Screen {
    static Drop game;

    private Stage stage;
    //	float screenWidth = Gdx.graphics.getWidth();
//	float screenHeight = Gdx.graphics.getHeight();
    float screenWidth = 500;
    float screenHeight = 500;
    float gameWidth = 500;
    float gameHeight = screenHeight / (screenWidth / gameWidth);
    OrthographicCamera camera;
    Element element;
    Array<Element> elements = new Array<Element>();
    Level level;

    public My_game(final Drop game) {;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, gameWidth, gameHeight);
        stage = new Stage(new  FitViewport(gameWidth, gameHeight));
//		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.input.setInputProcessor(stage);

        level = new Level();
        stage.addActor(level.group);
        level.crateLevel();

    }

    public static void completeLevel(){
        game.setScreen(new Menu(game)); // Не забыть освободить память
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
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
    public void dispose () {
        stage.dispose();
    }

}