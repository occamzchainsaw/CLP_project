/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author SzOg
 */
public class NewClass3 extends NewClass2 {
    private int p2 = 100;

    /**
     * Podstawowy konstruktor
     */
    public NewClass3() {
        super();
        s1 = s1.concat(" klasy pochodnej");
        p3 = 200;
    }

    /**
     * Konstruktor z parametrem
     * @param val wartość przypisywana do p2
     */
    public NewClass3(int val) {
        super(val);
        s1 = s1.concat(" klasy pochodnej");
        p3 = 200;
    }

    /**
     * Zwraca wartość parametru p2 zklasy nadrzędnej.
     * @return aktualna dziedziczona wartość p2
     */
    @Override
    public int getP2() {
        System.out.println("Podaję wartość p2 z klasy NowaKlasa2: ");
        return super.getP2();
    }
    
    /**
     * Zwraca wartość parametru p2.
     * @return aktualna wartość p2
     */
    public int getMyP2() {
        System.out.println("Podaję wartość p2 z klasy NowaKlasa3: ");
        return p2;
    }
    
    /**
     * Zwraca wartość parametru p3
     * @return aktualna wartość parametru p3
     */
    public int getP3() {
        return p3;
    }
    
}
