package com.badlogic.drop;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class My_game implements Screen {
    static Drop game;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();

    Level level;
    private final Stage stage;
    static int levelNumber;
    Skin skin;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private final SpriteBatch spriteBatch;
    BitmapFont font;

    public My_game(final Drop game, int newLevelNumber) {
        My_game.levelNumber = newLevelNumber;
        My_game.game = game;
        stage = new Stage(new  FitViewport(screenWidth, screenHeight));
        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        skin = new Skin();
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        skin.add("white", new Texture(pixmap));
        skin.add("default", font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white",0.5f, 0.5f, 0.5f, 0.1f);
//        textButtonStyle.down = skin.newDrawable("white", Color.YELLOW);
//        textButtonStyle.checked = skin.newDrawable("white", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("white", 0.5f, 0.5f, 0.5f, 0.2f);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        float dpi = Gdx.graphics.getDensity();
        dpi = (float) Math.sqrt(dpi);

//        System.out.println(dpi);
//        textButtonStyle.font.getData().scale(dpi);
        final TextButton button = new TextButton("Menu", skin);
//        final TextButton button = new TextButton(dpiString, skin);
        button.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                My_game.completeLevel(); // Не забыть освободить память

            }
        });

        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);

        level = new Level(game, levelNumber);
        table.center();
        table.addActor(level.group);

        table.top().right();
        table.add(button).width(100).height(40).padTop(50).padRight(50);

        stage.addActor(table);


        backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));
        TextureRegion backgroundRegion = new TextureRegion(backgroundTexture);
        backgroundSprite =new Sprite(backgroundRegion);
        spriteBatch = new SpriteBatch();

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
        spriteBatch.begin();
        spriteBatch.draw(backgroundSprite,0,0);
        spriteBatch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
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