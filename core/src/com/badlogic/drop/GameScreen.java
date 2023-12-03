package com.badlogic.drop;
import java.util.HashMap;
import java.util.Map;
//import android.util.Log;
//import suvitruf.libgdxtutorial.model.*;
//import suvitruf.libgdxtutorial.controller.*;
//import suvitruf.libgdxtutorial.view.*;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;

//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen, InputProcessor {
	//private World 			world;
	//private WorldRenderer 	renderer;
	//private WorldController	controller;



	//private World world;
	public OrthographicCamera cam;
	//ShapeRenderer renderer = new ShapeRenderer();
	public World world;
	private SpriteBatch spriteBatch;
	Texture texture;
	public  Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();

	public int width;
	public int height;
	//public float ppuX;	// �������� �� ����� ���� �� X 
	//public float ppuY;

	Viewport viewport;

	@Override
	public void show() {

		this.cam = new OrthographicCamera(World.CAMERA_WIDTH, World.CAMERA_HEIGHT);
		SetCamera(World.CAMERA_WIDTH / 2f, World.CAMERA_HEIGHT / 2f);
		spriteBatch = new SpriteBatch();
		loadTextures();
		world = new World(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, spriteBatch, textureRegions);
		//Log.e("1","1");
		//world = new World();
		//renderer = new WorldRenderer(/*world*/);
		//controller = new WorldController(world);
		//Gdx.input.setInputProcessor(this);

		Gdx.input.setInputProcessor(world);

		viewport = new FitViewport(World.CAMERA_WIDTH, World.CAMERA_HEIGHT, cam);
	}

	private void loadTextures() {
		texture  = new Texture(Gdx.files.internal("droplet.png"));
		TextureRegion tmp[][] = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight() / 2);
		textureRegions.put("player", tmp[0][0]);
		textureRegions.put("brick1", tmp[0][1]);
		textureRegions.put("brick2", tmp[1][0]);
		textureRegions.put("brick3", tmp[1][1]);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		//ChangeNavigation(x,y);
		return false;
	}


	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	public void SetCamera(float x, float y){
		this.cam.position.set(x, y,0);
		this.cam.update();
	}

	@Override
	public void resize(int width, int height) {
		//renderer.setSize(width, height);
		this.width = width;
		this.height = height;
		//ppuX = (float)width / CAMERA_WIDTH;
		//ppuY = (float)height / CAMERA_HEIGHT;
		//world.setPP(ppuX, ppuY);
		//world.setPP(ppuX, ppuY);
//		world.setViewport(width, height, true);
		world.setViewport(viewport);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}


	@Override
	public boolean keyDown(int keycode) {

		return true;
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 1, 1, 0.5f);
		ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);

//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Log.e("2","2");
		world.update(delta);
		world.draw();


		//controller.update(delta);
		//renderer.render();
	}
	@Override
	public boolean keyUp(int keycode) {

		return true;
	}
	
	/*private void ChangeNavigation(int x, int y){
		controller.resetWay();
		if(height-y >  controller.player.getPosition().y * renderer.ppuY)
			controller.upPressed();
		
		if(height-y <  controller.player.getPosition().y * srenderer.ppuY)
			controller.downPressed();
		
		if ( x< controller.player.getPosition().x * renderer.ppuX) 
			controller.leftPressed();
			
		if (x> (controller.player.getPosition().x +Player.SIZE)* renderer.ppuX)
			controller.rightPressed();
			
	}*/

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		//ChangeNavigation(x,y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		//controller.resetWay();

		return true;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	//	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
