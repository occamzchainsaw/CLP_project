package core;

import java.util.ArrayList;

/**
 *
 * @author M.Zahid KIZMAZ, Kajetan Poraj, Paweł Achtelik
 */
public class Dorse {

    /**
     * ID of the dorse.
     */
    private int id;
    
    /**
     * Name of the dorse.
     */
    private String name;
    
    /**
     * X side length of the dorse.
     */
    private int lengthX;
    
    /**
     * Y side length of the dorse.
     */
    private int lengthY;

    public Dorse() {
    }

    public Dorse(String name, int lengthX, int lengthY) {
        this.name = name;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.id = DorseController.LAST_DORSE_ID++;
    }
    
    /**
     * Minimum X coordinate of dorse for boxes that created.
     * @return Calculated Integer value of X coordinate.
     */
    public int getDorseMinX() {
        return 0;
    }

    /**
     * Minimum Y coordinate of dorse for boxes that created.
     * @return Calculated Integer value of Y coordinate.
     */
    public int getDorseMinY() {
        return 0;
    }

    /**
     * Maximum X coordinate of dorse for boxes that created.
     * @param boxes ArrayList of boxes that created.
     * @return Calculated Integer value of X coordinate.
     */
    public int getDorseMaxX(ArrayList<Box> boxes) {
        int tmp = boxes.get(0).getSideX();
        for (Box next : boxes) {
            if (tmp < next.getSideX()) {
                tmp = next.getSideX();
            }
        }
        return (lengthX - tmp);
    }

    /**
     * Maximum Y coordinate of dorse for boxes that created.
     * @param boxes ArrayList of boxes that created.
     * @return Calculated Integer value of Y coordinate.
     */
    public int getDorseMaxY(ArrayList<Box> boxes) {
        int tmp = boxes.get(0).getSideY();
        for (Box next : boxes) {
            if (tmp < next.getSideY()) {
                tmp = next.getSideY();
            }
        }
        return (lengthY - tmp);
    }
    
    /**
     * Calculates the loading time of dorse considering the size of dorse.
     * @return Calculated Double value of loading time.
     */
    public double getLoadingTime(){
        return (this.getLengthX() * this.getLengthY());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    @Override
    public String toString() {
        return "Dorse{" + "id=" + id + ", name=" + name + ", lengthX=" + lengthX + ", lengthY=" + lengthY + '}';
    }

}
