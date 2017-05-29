package core;

import java.util.ArrayList;

/**
 *
 * @author zahid
 */
public class Dorse {

    private int id;
    private String name;
    private int lengthX;
    private int lengthY;

    public Dorse() {
    }

    public Dorse(String name, int lengthX, int lengthY) {
        this.name = name;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.id = DorseController.LAST_DORSE_ID++;
    }

    public int getDorseMinX() {
        return 0;
    }

    public int getDorseMinY() {
        return 0;
    }

    public int getDorseMaxX(ArrayList<Box> boxes) {
        int tmp = boxes.get(0).getSideX();
        for (Box next : boxes) {
            if (tmp < next.getSideX()) {
                tmp = next.getSideX();
            }
        }
        return (lengthX - tmp);
    }

    public int getDorseMaxY(ArrayList<Box> boxes) {
        int tmp = boxes.get(0).getSideY();
        for (Box next : boxes) {
            if (tmp < next.getSideY()) {
                tmp = next.getSideY();
            }
        }
        return (lengthY - tmp);
    }
    
    public double getLoadingTime(){
        return (this.getLengthX() * this.getLengthY()) / 10;
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
