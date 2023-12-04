package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Menu implements Screen {

    Drop game;
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    OrthographicCamera camera;
    float screenWidth = 1920;
    float screenHeight = 1080;
    float gameWidth = 1920;
    float gameHeight = screenHeight / (screenWidth / gameWidth);
    static FileHandle file;
    static String level;

    public Menu(final Drop game) {
        this.game = game;

        batch = new SpriteBatch();
//        stage = new Stage();
        stage = new Stage(new FitViewport(gameWidth, gameHeight));

        Gdx.input.setInputProcessor(stage);

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libGDX font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.RED);
        textButtonStyle.down = skin.newDrawable("white", Color.YELLOW);
        textButtonStyle.checked = skin.newDrawable("white", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
//        table.add(button);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        final TextButton button = new TextButton("Click me!", skin);
        table.add(button);
        button.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + button.isChecked());
                button.setText("Good job!");
                file = Gdx.files.absolute("C:\\Users\\atavl\\OneDrive\\Рабочий стол\\учеба\\Парадигмы программирования\\Курсовая\\Drop\\assets\\level_1.txt");
                level = file.readString();
                game.setScreen(new My_game(game)); // Не забыть освободить память

            }
        });
        final TextButton button2 = new TextButton("Click me!", skin);
        table.add(button2);
        button2.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + button2.isChecked());
                button2.setText("Good job!");
                file = Gdx.files.absolute("C:\\Users\\atavl\\OneDrive\\Рабочий стол\\учеба\\Парадигмы программирования\\Курсовая\\Drop\\assets\\level_2.txt");
                level = file.readString();
                game.setScreen(new My_game(game)); // Не забыть освободить память

            }
        });
        final TextButton button3 = new TextButton("Click me!", skin);
        table.add(button3);
        button3.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + button3.isChecked());
                button3.setText("Good job!");
                file = Gdx.files.absolute("C:\\Users\\atavl\\OneDrive\\Рабочий стол\\учеба\\Парадигмы программирования\\Курсовая\\Drop\\assets\\level_2.txt");
                level = file.readString();
                game.setScreen(new Menu(game)); // Не забыть освободить память

            }
        });
        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
//        table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
    }

    static public String getLevel(){
        return level;
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
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
        stage.dispose();
        skin.dispose();
    }
}