package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

public class Level extends Group {
    static Drop game;
    Element element;
    static Element neighbor;
    static Element[][] elements;
    static int trueElementCount;
    static int elementCount;
    Group group;
    static int levelNumber;
    static String levelInfo;
    static String newLevelName;
    static FileHandle file;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();

    Level(Drop game, int levelNumber){
        Level.game = game;
        Level.levelNumber = levelNumber;
        levelInfo = getLevel(levelNumber);
        elements = new Element[11][11];
        group = new Group();
        group.clear();
//        String text = Menu.getLevel();
        String[] parts = levelInfo.split(" ");
//        String[] parts = {"1", "2", "pict_2.png"};
        for (int i = 0; i < parts.length; i+=3){
            element = new Element(Integer.parseInt(parts[i]), Integer.parseInt(parts[i+1]), parts[i+2]);
            elements [Integer.parseInt(parts[i])][Integer.parseInt(parts[i+1])] = element;
            group.addActor(element);
//            for (int j = 0; j < i; j+=1){element.addRotation(); }
//            element.addRotation();
        }
        elementCount = parts.length/3;
        group.setOrigin(250, 250);
//        group.setPosition(1920/2Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        group.setPosition(screenWidth/2-70*5, screenHeight/2-70*5);
        allElementCheck();

    }

    static void allElementCheck(){
        for (int i = 0; i < elements.length; i+=1) {
            for (int j = 0; j < elements[i].length; j += 1) {
                try {
//                    Level.checkNeighbor(elements[i][j]);
                    Random r = new Random();
                    int x = r.nextInt(4)+1 ;
                    for (int xx = 0; xx < x; xx += 1) {
                        elements[i][j].startRotation();
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }
    static void checkNeighbor(Element element){ // проверяем на соединение при повороте
////////////////////////////////////////////////////
        if(Math.abs(element.ends[3]) == 1){ //если конец
            try {
                neighbor = elements[element.x][element.y - 1]; // сосед с прошлой стороны
                if (neighbor.ends[0] == -1) { //если конец соседа был соединен
                    neighbor.ends[0] = 1; // рассоединяем
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
        if (Math.abs(element.ends[0]) == 1) {
            try {
                neighbor = elements[element.x - 1][element.y];
                if (neighbor.ends[1] == -1) {
                    neighbor.ends[1] = 1;
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
        if (Math.abs(element.ends[1]) == 1) {
            try {
                neighbor = elements[element.x][element.y + 1];
                if (neighbor.ends[2] == -1) {
                    neighbor.ends[2] = 1;
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
        if (Math.abs(element.ends[2]) == 1) {
            try {
                neighbor = elements[element.x + 1][element.y];
                if (neighbor.ends[3] == -1) {
                    neighbor.ends[3] = 1;
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
/////////////////////////////////////////////////
        if(element.ends[0] == 1){ //если конец
            try {
                neighbor = elements[element.x][element.y + 1]; // сосед со какой то стороны
                if (neighbor.ends[2] == 1) { //если конец соседа
                    element.ends[0] = -1; // соединены
                    neighbor.ends[2] = -1; // соединены
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
        if (element.ends[1] == 1) {
            try {
                neighbor = elements[element.x + 1][element.y];
                if (neighbor.ends[3] == 1) {
                    element.ends[1] = -1;
                    neighbor.ends[3] = -1;
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
        if (element.ends[2] == 1) {
            try {
                neighbor = elements[element.x][element.y - 1];
                if (neighbor.ends[0] == 1) {
                    element.ends[2] = -1;
                    neighbor.ends[0] = -1;
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
        if (element.ends[3] == 1) {
            try {
                neighbor = elements[element.x - 1][element.y];
                if (neighbor.ends[1] == 1) {
                    element.ends[3] = -1;
                    neighbor.ends[1] = -1;
                    neighbor.truePosition();
                }
            } catch (Exception ignored){
            }
        }
/////////////////////////////////////////////////////
        element.truePosition();
        trueLevel();
    }

    static void trueLevel(){
        trueElementCount = 0;

        for (int i = 0; i < elements.length; i+=1) {
            for (int j = 0; j < elements[i].length; j += 1) {
                try {
                    if (elements[i][j].trueElement) {
                        trueElementCount += 1;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        if(trueElementCount == elementCount){
            nextLevel();
        }
    }

    void getLevelInfo(){
        try {
            for (int i = 0; i < elements.length; i+=1) {
                for (int j = 0; j < elements[i].length; j += 1) {
                    System.out.println(elements[i][j]);
                }
            }
        } catch (Exception ignored){
        }
    }

    public static void nextLevel(){
        try {
            game.setScreen(new My_game(game, levelNumber + 1)); // Не забыть освободить память
        } catch (Exception ex){
            game.setScreen(new Menu(game));
        }
    }
    static public String getLevel(int levelNumber){
        try {
            file = Gdx.files.internal("level_"+Integer.toString(levelNumber)+".txt");
            newLevelName = file.readString();
            return newLevelName;
        } catch (Exception ex){
            game.setScreen(new Menu(game));
        }
        return null;
    }
}
