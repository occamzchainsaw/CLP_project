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
    private String name;

    public Box() {
    }
    
    public Box(int sideX, int sideY, int id) {
        this.sideX = sideX;
        this.sideY = sideY;
        this.id = id;
    }
    public Box(int sideX, int sideY, String name) {
        this.sideX = sideX;
        this.sideY = sideY;
        this.name = name;
    }
    
    /**
     * 
     * @param boxNum Number of boxes that wanted to create.
     * @param sideX Length of X dimension.
     * @param sideY Length of Y dimension.
     * @return ArrayList of Boxes created typed ArrayList<Box>
     */    
    public ArrayList<Box> generateBoxes(int boxNum, int sideX, int sideY){
    
        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(sideX,sideY,i+1));
        }
        return boxes;
    }
    
    /**
     * 
     * @param boxNum Number of boxes that wanted to create.
     * @param sideX Length of X dimension.
     * @param sideY Length of Y dimension.
     * @param name Name of boxes to create.
     * @return ArrayList of Boxes created typed ArrayList<Box>
     */
    public ArrayList<Box> generateBoxes(int boxNum, int sideX, int sideY, String name){
    
        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(sideX,sideY,name + " " + (i+1)));
        }
        return boxes;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "ID= " + this.id + " X =" + this.sideX + " Y =" + this.sideY ;
    }
    
}
