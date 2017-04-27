/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author zahid
 */
public class BoxController {
    
    public static int LAST_ID ;
    ArrayList<Box> allBoxes = new ArrayList<>();
    
        
    /**
     * Generates Boxes by giving parameters.
     * @param boxNum Number of boxes that wanted to create.
     * @param sideX Length of X dimension.
     * @param sideY Length of Y dimension.
     * @return ArrayList of Boxes those created.
     */    
    public ArrayList<Box> generateBoxes(int boxNum, int sideX, int sideY){
    
        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(sideX,sideY));
        }
        allBoxes.addAll(boxes);
        return boxes;
    }
    
    /**
     * Generates Boxes by giving parameters.
     * @param boxNum Number of boxes that wanted to create.
     * @param sideX Length of X dimension.
     * @param sideY Length of Y dimension.
     * @param name Name of boxes to create.
     * @return ArrayList of Boxes those created.
     */
    public ArrayList<Box> generateBoxes(int boxNum, int sideX, int sideY, String name){
    
        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(sideX,sideY,name + " " + (i+1)));
        }
        allBoxes.addAll(boxes);
        return boxes;
    }

}

