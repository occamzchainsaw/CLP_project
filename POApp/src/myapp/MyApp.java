/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myapp;

import core.Box;
import core.CLPTask;
import core.CLPTaskOptim;
import core.NewClass;
import core.NewClass2;
import core.NewClass3;
import gui.MyAppFrame;
import java.util.ArrayList;

/**
 *
 * @author SzOg
 */
public class MyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        
        
        /*
        NewClass nk = new NewClass();
        nk.p1 = 10;
        nk.s1 = "Tekst";
        nk.setP2(17);
        int param = nk.getP2();
        
        
        System.out.println("Param: " + param + "\n");
        System.out.println("S1 " + nk.s1);
        
        NewClass2 nk2 = new NewClass2();
        System.out.println("NK2 s1: " + nk2.s1);
        System.out.println("NK2 p2: " + nk2.getP2() + "\n");
        
        NewClass2 nk2_2 = new NewClass2(10);
        System.out.println("NK2_2 s1: " + nk2_2.s1);
        System.out.println("NK2_2 p2: " + nk2_2.getP2() + "\n");
        
        NewClass3 nk3 = new NewClass3();
        System.out.println("NK3 s1: " + nk3.s1);
        System.out.println("NK3 p2: " + nk3.getMyP2());
        System.out.println("NK3(2) p2: " + nk3.getP2() + "\n");
        
        NewClass3 nk3_2 = new NewClass3(20);
        System.out.println("NK3_2 s1: " + nk3_2.s1);
        System.out.println("NK3_2 p2: " + nk3.getMyP2());
        System.out.println("NK3_2(2) p2: " + nk3_2.getP2() + "\n");

        System.out.println("NK3 p3: " + nk3.getP3());
        
        MyAppFrame maf = new MyAppFrame();
        maf.setVisible(true);
        
        maf.printLine("NK3_2 s1: " + nk3_2.s1);
        maf.printLine("NK3_2 p2: " + nk3.getMyP2());
        maf.printLine("NK3_2(2) p2: " + nk3_2.getP2() + "\n");
        */
        CLPTask zad = new CLPTask();
        zad.modeluj();
        System.out.println(zad.returnStoreString());
        zad.szukaj();
        //maf.printLine(zad.returnStoreString());
        /*
        CLPTaskOptim zad2 = new CLPTaskOptim();
        zad2.modeluj();
        zad2.szukaj();
        maf.printLine(zad2.returnStoreString());
        */
    }
}
