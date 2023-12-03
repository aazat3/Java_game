//package com.badlogic.drop;
//
//import java.util.Iterator;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//public class GameScreen2 implements Screen {
//
//    public class Element {
//        Texture elementImage;
//        Rectangle element;
//        Sprite sprite;
//        int rotate;
//        int trueRotate;
//        int x;
//        int y;
//
//
//    }
//
//    final Drop game;
//    float screenWidth = Gdx.graphics.getWidth();
//    float screenHeight = Gdx.graphics.getHeight();
//    float gameWidth = 1920;
//    float gameHeight = screenHeight / (screenWidth / gameWidth);
//    Texture dropImage;
//    Texture bucketImage;
//    Sound dropSound;
//    Music rainMusic;
//    OrthographicCamera camera;
//    Viewport viewport;
//    Rectangle bucket;
//    Sprite sprite;
//    int rotation;
//    private TextureRegion region;
//    Array<Rectangle> raindrops;
//    long lastDropTime;
//    int dropsGathered;
//    private SimpleButton playButton;
//    public GameScreen2(Drop game) {
//        this.game = game;
//        // load the images for the droplet and the bucket, 64x64 pixels each
//        dropImage = new Texture(Gdx.files.internal("droplet.png"));
//        bucketImage = new Texture(Gdx.files.internal("test.png"));
//        region = new TextureRegion(bucketImage, 0, 0, 50, 50);
//        sprite = new Sprite(bucketImage,50,50);
//        sprite.setOriginCenter();
//        sprite.setPosition(400,400);
//        sprite.setCenter(25,25);
//        Element element = new Element();
//        playButton = new SimpleButton(100,100, 50,50, region, region);
//        // load the drop sound effect and the rain background "music"
////        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
////        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
////        rainMusic.setLooping(true);
//
//        // create the camera and the SpriteBatch
//        camera = new OrthographicCamera();
//        camera.setToOrtho(true, gameWidth, gameHeight);
//        viewport = new FitViewport(gameWidth, gameHeight, camera);
//
//        // create a Rectangle to logically represent the bucket
//        bucket = new Rectangle();
//        bucket.x = gameWidth / 2 - 50 / 2; // center the bucket horizontally
//        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
//        // the bottom screen edge
//        bucket.width = 50;
//        bucket.height = 50;
//
//        // create the raindrops array and spawn the first raindrop
//        raindrops = new Array<Rectangle>();
//
//    }
//
//    @Override
//    public void render(float delta) {
//        // clear the screen with a dark blue color. The
//        // arguments to clear are the red, green
//        // blue and alpha component in the range [0,1]
//        // of the color to be used to clear the screen.
//        ScreenUtils.clear(0, 0, 0, 1);
//
//        // tell the camera to update its matrices.
//        camera.update();
//
//        // tell the SpriteBatch to render in the
//        // coordinate system specified by the camera.
//        game.batch.setProjectionMatrix(camera.combined);
//        // begin a new batch and draw the bucket and
//        // all drops
//        game.batch.begin();
//        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, gameHeight);
//        System.out.println(playButton.isClicked(100, 99));
//        game.batch.draw(region, bucket.x, bucket.y,25,25, bucket.width, bucket.height,1,1,rotation);
//        for (Rectangle raindrop : raindrops) {
//            game.batch.draw(dropImage, raindrop.x, raindrop.y);
//        }
//        game.batch.end();
//
//        // process user input
//        if (Gdx.input.justTouched()) {
//            Vector3 touchPos = new Vector3();
//            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(touchPos);
//            bucket.x = touchPos.x- 50 / 2;
//            bucket.y = touchPos.y- 50 / 2;
//            rotation += 90;
//        }
//        if (Gdx.input.isKeyPressed(Keys.LEFT))
//            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Keys.RIGHT))
//            bucket.x += 200 * Gdx.graphics.getDeltaTime();
//
//        // make sure the bucket stays within the screen bounds
//        if (bucket.x < 0)
//            bucket.x = 0;
//        if (bucket.x > gameWidth - 50)
//            bucket.x = gameWidth - 50;
//
//        // check if we need to create a new raindrop
//
//        // move the raindrops, remove any that are beneath the bottom edge of
//        // the screen or that hit the bucket. In the later case we play back
//        // a sound effect as well.
//        Iterator<Rectangle> iter = raindrops.iterator();
//        while (iter.hasNext()) {
//            Rectangle raindrop = iter.next();
//            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
//            if (raindrop.y + 64 < 0)
//                iter.remove();
//            if (raindrop.overlaps(bucket)) {
//                dropsGathered++;
//                //dropSound.play();
//                iter.remove();
//            }
//        }
//    }
//
//
//    public void elementRotate() {
//
//    }
//    public void elementPosition() {
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void show() {
//        // start the playback of the background music
//        // when the screen is shown
//        //rainMusic.play();
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
//        dropImage.dispose();
//        bucketImage.dispose();
//        dropSound.dispose();
//        rainMusic.dispose();
//    }
//
//}
