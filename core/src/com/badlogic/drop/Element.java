package com.badlogic.drop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Element extends Actor{
    Image image;
    int x;
    int y;
    TextureRegion region;
    int position;
    float animatedPosition;
    int animate;
    int falsePosition;
    int truePosition;
    int[] ends;
    boolean trueElement = false;
    boolean mouseM = false;

    Element(int x, int y, String pathImage){
        switch (pathImage) {
            case "pict_1.png":
                ends = new int[]{0, 1, 0, 1};
                break;
            case "pict_2.png":
                ends = new int[]{0, 1, 0, 0};
                break;
            case "pict_3.png":
                ends = new int[]{0, 1, 1, 1};
                break;
            case "pict_4.png":
                ends = new int[]{0, 1, 1, 0};
                break;
            case "pict_5.png":
                ends = new int[]{1, 1, 1, 1};
                break;
        }
        for (int end : ends)
            falsePosition += end;
        float dpi = Gdx.graphics.getDensity();
        dpi = (float) Math.sqrt(dpi);

//        int dpi = 1;
        this.x = x;
        this.y = y;
        setX(x*70*dpi-35*dpi);
        setY(y*70*dpi-35*dpi);
        Texture texture = new Texture(Gdx.files.internal(pathImage));
        region = new TextureRegion(texture);
        setBounds(getX(), getY(), region.getRegionWidth(), region.getRegionHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                addRotation();
                animate += 1;
                return true;
            }
            public boolean mouseMoved (InputEvent event, float x, float y) {
                mouseM = true;
                return true;
            }
        });
        this.setSize(70*dpi,70*dpi);
        animate = 0;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
//        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.setColor(1f, 1f, 1f, 0.6f); // тут цвета элементов
        batch.draw(region, getX(), getY(), getOriginX()+getWidth()/2, getOriginY()+getHeight()/2,
                getWidth(), getHeight(), getScaleX(), getScaleY(), position*90/10f);
        if(animate > 0){
            rotationAnimation();
        }
    }

    public Actor hit (float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ? this : null;
    }

    void startRotation() {

        if (position >= 0) {
            position -= 10;
        } else {
            position -= 10  + 40;
        }

        int z = Math.abs(ends[0]);
        ends[0] = Math.abs(ends[3]);
        ends[3] = Math.abs(ends[2]);
        ends[2] = Math.abs(ends[1]);
        ends[1] = Math.abs(z);
        Level.checkNeighbor(this);

    }
    void addRotation() {
        int z = Math.abs(ends[0]);
        ends[0] = Math.abs(ends[3]);
        ends[3] = Math.abs(ends[2]);
        ends[2] = Math.abs(ends[1]);
        ends[1] = Math.abs(z);
        Level.checkNeighbor(this);

    }
    void rotationAnimation() {
        if (position >= 0) {
            position -= 1;
        } else {
            position -= 1 + 40;
        }
        if (Math.abs(position % 10) < 0.1f){
            animate -= 1;

        }

    }

    void truePosition(){
        truePosition = 0;
        for (int end : ends)
            truePosition += end;
        trueElement = truePosition == -1 * falsePosition;
    }
}
