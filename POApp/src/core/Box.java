/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author achte
 */
public class Box {
    private int sideX;
    private int sideY;
    private int id;


    public Box(){}

    public Box(int sideX, int sideY, int id) {
        this.sideX = sideX;
        this.sideY = sideY;
        this.id = id;
    }

    public void setSideX(int sideX) {
        this.sideX = sideX;
    }
    
    public int getSideX() {
        return sideX;
    }

    public int getSideY() {
        return sideY;
    }

    public void setSideY(int sideY) {
        this.sideY = sideY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<Box> generateBoxes(int boxNum){
    
        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(2,2,i+1));
        }
        return boxes;
    }

    @Override
    public String toString() {
        return "ID= " + this.id + " X =" + this.sideX + " Y =" + this.sideY ;
    }
    
}
