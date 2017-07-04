
package core;

/**
 *
 * @author M.Zahid KIZMAZ, Kajetan Poraj, Paweł Achtelik
 */
public class Box {

    /**
     * X side length of the box.
     */
    private int sideX;
    
    /**
     * Y side length of the box.
     */
    private int sideY;
    
    /**
     * ID of the box.
     */
    private int id;
    
    /**
     * Name of the box.
     */
    private String name;

    public Box() {
    }

    public Box(int sideX, int sideY) {
        this.sideX = sideX;
        this.sideY = sideY;
        this.id = BoxController.LAST_ID++;
    }

    public Box(int sideX, int sideY, String name) {
        this.sideX = sideX;
        this.sideY = sideY;
        this.name = name;
        this.id = BoxController.LAST_ID++;
    }
    
    /**
     * Calculates the weight of box.
     * @return Double value as a kilo gram of box.
     */
    public double getWeight(){
        return this.getSideX() * this.getSideY() * 5;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID= " + this.id + " X =" + this.sideX + " Y =" + this.sideY;
    }

}
