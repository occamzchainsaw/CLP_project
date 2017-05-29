/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

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
