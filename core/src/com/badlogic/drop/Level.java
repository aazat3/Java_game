package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Level extends Group {
    static Drop game;
    Element element;
    static Element neighbor;
    static Element[][] elements;
    static int trueElementCount;
    static int elementCount;
    Group group;
    Level(){
        elements = new Element[10][10];
        group = new Group();
        group.clear();
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                element = new Element(i*50, j*50, "pict_1.png");
//                elements = null;
//                elements = new Array<Element>();
//                elements.add(element);
//                group.addActor(element);
//            }
//        }

    }

    void crateLevel(){
        group.clear();
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                element = new Element(i, j, "pict_2.png");
//                elements.add(element);
//                group.addActor(element);
//            }
//        }

//        FileHandle file = Gdx.files.absolute("C:\\Users\\atavl\\OneDrive\\Рабочий стол\\учеба\\Парадигмы программирования\\Курсовая\\Drop\\assets\\level_1.txt");
//        FileHandle file = Menu.getLevel();
//        String text = file.readString();
        String text = Menu.getLevel();
        String[] parts = text.split(" ");
        for (int i = 0; i < parts.length; i+=3){
            element = new Element(Integer.parseInt(parts[i]), Integer.parseInt(parts[i+1]), parts[i+2]);
            elements [Integer.parseInt(parts[i])][Integer.parseInt(parts[i+1])] = element;
//            element = new Element(1*50, 1*50, "pict_2.png");
//            elements = new Array<Element>();
//            elements.add(element);
            group.addActor(element);
        }
        elementCount = parts.length/3;
    }

    static void checkNeighbor(Element element){ // проверяем на соединение при повороте
////////////////////////////////////////////////////
        if(Math.abs(element.ends[3]) == 1){ //если конец
            try {
                neighbor = elements[element.x][element.y - 1]; // сосед с прошлой стороны
                if (neighbor.ends[0] == -1) { //если конец соседа был соединен
//                    element.ends[0] = -1; // соединены
                    neighbor.ends[0] = 1; // рассоединяем
                    neighbor.truePosition();
                }
//                System.out.println(neighbor.ends[0]);
            } catch (Exception ex){
            }
        }
        if (Math.abs(element.ends[0]) == 1) {
            try {
                neighbor = elements[element.x - 1][element.y];
                if (neighbor.ends[1] == -1) {
//                    element.ends[1] = -1;
                    neighbor.ends[1] = 1;
                    neighbor.truePosition();
                }
//                System.out.println(neighbor.ends[1]);
            } catch (Exception ex){
            }
        }
        if (Math.abs(element.ends[1]) == 1) {
            try {
                neighbor = elements[element.x][element.y + 1];
                if (neighbor.ends[2] == -1) {
//                    element.ends[2] = -1;
                    neighbor.ends[2] = 1;
                    neighbor.truePosition();
                }
//                System.out.println(neighbor.ends[2]);
            } catch (Exception ex){
            }
        }
        if (Math.abs(element.ends[2]) == 1) {
            try {
                neighbor = elements[element.x + 1][element.y];
                if (neighbor.ends[3] == -1) {
//                    element.ends[3] = -1;
                    neighbor.ends[3] = 1;
                    neighbor.truePosition();
                }
//                System.out.println(neighbor.ends[3]);
            } catch (Exception ex){
            }
        }
/////////////////////////////////////////////////
        if(element.ends[0] == 1){ //если конец
            try {
                neighbor = elements[element.x][element.y + 1]; // сосед со какой то стороны
                if (neighbor.ends[2] == 1) { //если конец соседа
                    element.ends[0] = -1; // соединены
//                    System.out.println(element.ends[0]);
                    neighbor.ends[2] = -1; // соединены
//                    System.out.println(neighbor.ends[2]);
                    neighbor.truePosition();
                }
            } catch (Exception ex){
//                System.out.println("//////////////////");
            }
        }
        if (element.ends[1] == 1) {
            try {
                neighbor = elements[element.x + 1][element.y];
                if (neighbor.ends[3] == 1) {
                    element.ends[1] = -1;
//                    System.out.println(element.ends[1]);
                    neighbor.ends[3] = -1;
//                    System.out.println(neighbor.ends[3]);
                    neighbor.truePosition();
                }
            } catch (Exception ex){
//                System.out.println("//////////////////");
            }
        }
        if (element.ends[2] == 1) {
            try {
                neighbor = elements[element.x][element.y - 1];
                if (neighbor.ends[0] == 1) {
                    element.ends[2] = -1;
//                    System.out.println(element.ends[2]);
                    neighbor.ends[0] = -1;
//                    System.out.println(neighbor.ends[0]);
                    neighbor.truePosition();
                }
            } catch (Exception ex){
//                System.out.println("//////////////////");
            }
        }
        if (element.ends[3] == 1) {
            try {
                neighbor = elements[element.x - 1][element.y];
                if (neighbor.ends[1] == 1) {
                    element.ends[3] = -1;
//                    System.out.println(element.ends[3]);
                    neighbor.ends[1] = -1;
//                    System.out.println(neighbor.ends[1]);
                    neighbor.truePosition();
                }
            } catch (Exception ex){
//                System.out.println("//////////////////");
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
//                        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
                    }
                } catch (Exception ex) {
                }
            }
        }
//        System.out.println(trueElementCount);
//        System.out.println(elementCount);
        if(trueElementCount == elementCount){
            My_game.completeLevel();
        }
    }

    public void newLevel(){
        game.setScreen(new Menu(game)); // Не забыть освободить память

    }

    void getLevelInfo(){
        try {
            for (int i = 0; i < elements.length; i+=1) {
                for (int j = 0; j < elements[i].length; j += 1) {
                    System.out.println(elements[i][j]);
                }
            }
        } catch (Exception ex){
        }
    }
}
