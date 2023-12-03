//package com.badlogic.drop;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//public class MainMenuScreen implements Screen {
//    static int XVIEW = 1000, YVIEW = 600;
//    final Drop game;
//    OrthographicCamera camera;
//
//    public MainMenuScreen(final Drop gam) {
//        game = gam;
//
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, XVIEW, YVIEW);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(1, 1, 1f, 1);
//
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);
//
//        game.batch.begin();
//        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
//        game.batch.end();
//
//        if (Gdx.input.isTouched()) {
//            System.out.println("asd");
//            game.setScreen(new GameScreen());
//            dispose();
//        }
//    }
//
//    @Override
//    public void resize(int width, int height) {
//    }
//
//    @Override
//    public void show() {
//    }
//
//    @Override
//    public void hide() {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void dispose() {
//    }
//}