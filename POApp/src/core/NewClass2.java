/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author SzOg
 */
public class NewClass2 {
    public int p1 = 0;
    public String s1 = "";
    private int p2 = 5;
    protected int p3 = 0;

    /**
     * Podstawowy konstruktor
     */
    public NewClass2() {
        s1 = "Nowy obiekt";
    }

    /**
     * Konstruktor z parametrem
     * @param val wartość przypisywana do p2
     */
    public NewClass2(int val) {
        s1 = "Nowy obiekt z parametrem";
        p2 = val;
    }
   
    /**
     * Ustawia wartość parametru P2
     * @param val wartość, którą chcemy ustawić
     */
    public void setP2(int val) {
        p2 = val;
    }
    
    /**
     * Zwraca wartość parametru p2.
     * @return aktualna wartość p2
     */
    public int getP2() {
        return p2;
    }
}
