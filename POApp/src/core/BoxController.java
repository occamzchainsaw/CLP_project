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

    public static int LAST_ID;
    private ArrayList<Box> allBoxes;

    public BoxController() {
        this.allBoxes = new ArrayList<>();
    }

    /**
     * Generates Boxes by giving parameters.
     *
     * @param boxNum Number of boxes that wanted to create.
     * @param sideX Length of X dimension.
     * @param sideY Length of Y dimension.
     * @return ArrayList of Boxes those created.
     */
    public void generateBoxes(int boxNum, int sideX, int sideY) {

        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(sideX, sideY));
        }
        allBoxes.addAll(boxes);
    }

    /**
     * Generates Boxes by giving parameters.
     *
     * @param boxNum Number of boxes that wanted to create.
     * @param sideX Length of X dimension.
     * @param sideY Length of Y dimension.
     * @param name Name of boxes to create.
     * @return ArrayList of Boxes those created.
     */
    public void generateBoxes(int boxNum, int sideX, int sideY, String name) {

        ArrayList<Box> boxes = new ArrayList();
        for (int i = 0; i < boxNum; i++) {
            boxes.add(new Box(sideX, sideY, name + "-" + (i + 1)));
        }
        allBoxes.addAll(boxes);
    }

    public ArrayList<Box> getAllBoxes() {
        return allBoxes;
    }

    public int getBoxesMaxX() {
        int tmp = allBoxes.get(0).getSideX();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp < allBoxes.get(i).getSideX()) {
                tmp = allBoxes.get(i).getSideX();
            }
        }
        return tmp;
    }

    public int getBoxesMaxY() {
        int tmp = allBoxes.get(0).getSideY();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp < allBoxes.get(i).getSideY()) {
                tmp = allBoxes.get(i).getSideY();
            }
        }
        return tmp;
    }

    public int getBoxesMinX() {
        int tmp = allBoxes.get(0).getSideX();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp > allBoxes.get(i).getSideX()) {
                tmp = allBoxes.get(i).getSideX();
            }
        }
        return tmp;
    }

    public int getBoxesMinY() {
        int tmp = allBoxes.get(0).getSideY();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp > allBoxes.get(i).getSideY()) {
                tmp = allBoxes.get(i).getSideY();
            }
        }
        return tmp;
    }
}
