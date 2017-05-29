/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import org.jacop.constraints.Diff2;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

/**
 *
 * @author zahid
 */
public class BoxController {

    /**
     * ID of the last created box.
     */
    public static int LAST_ID;
    
    /**
     * ArrayList of Boxes created
     */
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

    /**
     * Gives the ArrayList of Boxes.
     * @return Box typed of ArrayList. 
     */
    public ArrayList<Box> getAllBoxes() {
        return allBoxes;
    }

    /**
     * Gives the highest X value of boxes created.
     * @return Int value of maximum X sides of boxes.
     */
    public int getBoxesMaxX() {
        int tmp = allBoxes.get(0).getSideX();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp < allBoxes.get(i).getSideX()) {
                tmp = allBoxes.get(i).getSideX();
            }
        }
        return tmp;
    }

    /**
     * Gives the highest Y value of boxes created.
     * @return Int value of maximum Y sides of boxes.
     */
    public int getBoxesMaxY() {
        int tmp = allBoxes.get(0).getSideY();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp < allBoxes.get(i).getSideY()) {
                tmp = allBoxes.get(i).getSideY();
            }
        }
        return tmp;
    }

    /**
     * Gives the lowest X value of boxes created.
     * @return Int value of minimum X sides of boxes.
     */
    public int getBoxesMinX() {
        int tmp = allBoxes.get(0).getSideX();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp > allBoxes.get(i).getSideX()) {
                tmp = allBoxes.get(i).getSideX();
            }
        }
        return tmp;
    }
    
    /**
     * Gives the lowest Y value of boxes created.
     * @return Int value of minimum Y sides of boxes.
     */
    public int getBoxesMinY() {
        int tmp = allBoxes.get(0).getSideY();
        for (int i = 1; i < allBoxes.size(); i++) {
            if (tmp > allBoxes.get(i).getSideY()) {
                tmp = allBoxes.get(i).getSideY();
            }
        }
        return tmp;
    }
    
    
    /**
     * Puts variables in ordered way to IntVar matrix.
     * @param originXIntVars First columns values of matrix.
     * @param originYIntVars Second columns values of matrix.
     * @param lenXIntVars Third columns values of matrix.
     * @param lenYIntVars Fourth columns values of matrix.
     * @param rowNum Number of rows in matrix.
     * @return Ordered IntVar matrix.
     */
    public IntVar[][] putVariablesInMatrix(IntVar[] originXIntVars, IntVar[] originYIntVars, IntVar[] lenXIntVars, IntVar[] lenYIntVars, int rowNum){
        
        int col;
        IntVar[][] vars = new IntVar[rowNum][4];
        
        for (int i = 0; i < rowNum; i++) {
            col = 0;
            vars[i][col++] = originXIntVars[i];
            vars[i][col++] = originYIntVars[i];
            vars[i][col++] = lenXIntVars[i];
            vars[i][col++] = lenYIntVars[i];
        }
        return vars;
    }
}
