package com.badlogic.drop;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Drop extends Game {

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

	@Override
	public void create () {
		camera = new OrthographicCamera();
        camera.setToOrtho(true, gameWidth, gameHeight);
		stage = new Stage(new  FitViewport(gameWidth, gameHeight));
//		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		Gdx.input.setInputProcessor(stage);

//		for(int i=0;i<10;i++){
//			for(int j=0;j<10;j++){
//				element = new Element(i*50, j*50, "pict_1.png");
//				elements.add(element);
//				stage.addActor(element);
//			}
//		}

		level = new Level();
		stage.addActor(level.group);
		level.crateLevel();

	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime();
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
	public void dispose () {
		stage.dispose();
	}

}