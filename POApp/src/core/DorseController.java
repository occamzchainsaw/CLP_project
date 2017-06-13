/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import org.jacop.core.IntVar;

/**
 *
 * @author zahid
 */
public class DorseController {

    /**
     * ID of the last created dorse.
     */
    public static int LAST_DORSE_ID;
    
    /**
     * ArrayList of Dorses created
     */
    private static ArrayList<Dorse> allDorses = new ArrayList<>();

    public DorseController() {
    }
    
    /**
     * Simply generating dorse by using the constructor and adding it to ArrayList.
     * @param name Name of the dorse.
     * @param lengthX Integer length of X side of the dorse.
     * @param lengthY Integer length of Y side of the dorse.
     * @return Dorse that created.
     */
    public Dorse generateDorse(String name,int lengthX,int lengthY){
        Dorse dorse = new Dorse(name, lengthX, lengthY);
        allDorses.add(dorse);
        LAST_DORSE_ID++;
        return dorse;
    }

    
    
    /**
     * Calculates the delivery time of dorse.
     * @param controller Box Controller that has boxes in that dorse.
     * @return Double value of delivery time.
     */
    public double getDeliveryTime(BoxController controller){
        ArrayList<Box> boxes = controller.getAllBoxes();
        if(boxes.isEmpty()){
            return 0;
        }
        double sumWeigth = 0;
        for (int i = 0; i < boxes.size(); i++) {
            sumWeigth += ((Box)boxes.get(i)).getWeight();
        }      
        return sumWeigth;
    }


    public ArrayList<Dorse> getAllDorses() {
        return allDorses;
    }
    

    public IntVar[][] putVariablesInMatrix(IntVar[] firstColumn, IntVar[] secondColumn, IntVar[] thirdColumn, int rowNum){
        
        int col;
        IntVar[][] vars = new IntVar[rowNum][3];
        
        for (int i = 0; i < rowNum; i++) {
            col = 0;
            vars[i][col++] = firstColumn[i];
            vars[i][col++] = secondColumn[i];
            vars[i][col++] = thirdColumn[i];
        }
        return vars;
    }

}
