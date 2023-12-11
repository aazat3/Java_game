package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Menu implements Screen {

    Drop game;
    Skin skin;
    Stage stage;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private SpriteBatch spriteBatch;
    private TextureRegion backgroundRegion;
    BitmapFont font;
    public Menu(final Drop game) {
        this.game = game;
        stage = new Stage(new FitViewport(screenWidth, screenHeight));
        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        skin = new Skin();
        font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        skin.add("white", new Texture(pixmap));
        skin.add("default", font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//        textButtonStyle.up = skin.newDrawable("white",0.5f, 0.5f, 0.5f, 0.5f);
//        textButtonStyle.down = skin.newDrawable("white", Color.YELLOW);
//        textButtonStyle.checked = skin.newDrawable("white", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("white", 0.5f, 0.5f, 0.5f, 0.2f);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        float dpi = Gdx.graphics.getDensity();
        dpi = (float) Math.sqrt(dpi);

        float weightSize = Gdx.graphics.getWidth() / Gdx.graphics.getPpcX();
        float heightSize = Gdx.graphics.getHeight() / Gdx.graphics.getPpcX();
//        textButtonStyle.font.getData().scale(dpi);
        textButtonStyle.font.getData().setScale(dpi,dpi);
        skin.add("default", textButtonStyle);

        Table table = new Table();
//        table.setDebug(true);
        table.setFillParent(true);
        stage.addActor(table);

        final TextButton button = new TextButton("Level 1", skin);
        button.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new My_game(game, 1)); // Не забыть освободить память

            }
        });
        final TextButton button2 = new TextButton("Level 2", skin);
        button2.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new My_game(game, 2)); // Не забыть освободить память

            }
        });
        final TextButton button3 = new TextButton("Level 3", skin);
        button3.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new My_game(game, 3)); // Не забыть освободить память

            }
        });
        final TextButton buttonExit = new TextButton("Exit", skin);
        buttonExit.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                dispose();

            }
        });

        table.add(button);
        table.row();
        table.add(button2);
        table.row();
        table.add(button3);
        table.row();
        table.add(buttonExit);

//        table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);

        backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));
        backgroundRegion = new TextureRegion(backgroundTexture);
        backgroundSprite =new Sprite(backgroundRegion);
        spriteBatch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(backgroundSprite,0,0);
        spriteBatch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        skin.dispose();
        game.dispose();
        Gdx.app.exit();
    }
}