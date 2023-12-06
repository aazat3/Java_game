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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import java.util.stream.IntStream;

public class Element extends Actor{
    Image image;
    int x;
    int y;
    TextureRegion region;
    int position;
    int falsePosition;
    int truePosition;
    int ends [];
    boolean trueElement = false;
    boolean mouseM = false;

    Element(int x, int y, String pathImage){
        if (pathImage.equals("pict_1.png")){
            ends =  new int[]{0,1,0,1};
        } else if (pathImage.equals("pict_2.png")) {
            ends =  new int[]{0,1,0,0};
        }else if (pathImage.equals("pict_3.png")) {
            ends =  new int[]{0,1,1,1};
        }else if (pathImage.equals("pict_4.png")) {
            ends =  new int[]{0,1,1,0};
        }else if (pathImage.equals("pict_5.png")) {
            ends =  new int[]{1,1,1,1};
        }
        for (int end : ends)
            falsePosition += end;
        this.x = x;
        this.y = y;
        setX(x*50);
        setY(y*50);
        Texture texture = new Texture(Gdx.files.absolute(pathImage));
        region = new TextureRegion(texture);
        setBounds(getX(), getY(),
                region.getRegionWidth(), region.getRegionHeight());
        image = new Image(region);
        image.setPosition(x, y);
        image.setOrigin(image.getWidth()/2,image.getHeight()/2);
        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                addRotation();
                return true;
            }
            public boolean mouseMoved (InputEvent event, float x, float y) {
                mouseM = true;
                return true;
            }
        });
        this.setSize(50,50);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
//        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.setColor(1f, 1f, 1f, 0.6f); // тут цвета элементов
        batch.draw(region, getX(), getY(), getOriginX()+getWidth()/2, getOriginY()+getHeight()/2,
                getWidth(), getHeight(), getScaleX(), getScaleY(), position*90);
    }


    public Actor hit (float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ? this : null;
    }

    void addRotation(){
        if (position >= 0) {
            position -= 1;
        } else{
            position -= 1 + 4;
        }
        int z = Math.abs(ends[0]);
        ends[0] = Math.abs(ends[3]);
        ends[3] = Math.abs(ends[2]);
        ends[2] = Math.abs(ends[1]);
        ends[1] = Math.abs(z);
        Level.checkNeighbor(this);
    }

    void truePosition(){
        truePosition = 0;
        for (int end : ends)
            truePosition += end;
        if(truePosition == -1 * falsePosition) {
            trueElement = true;
        } else
            trueElement = false;
    }
}
