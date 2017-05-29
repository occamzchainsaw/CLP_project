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
public class DorseController {

    /**
     * ID of the last created dorse.
     */
    public static int LAST_DORSE_ID;

    /**
     * Calculates the delivery time of dorse.
     * @param dorse Dorse object that is going deliver.
     * @param controller Box Controller that has boxes in that dorse.
     * @return Double value of delivery time.
     */
    public double getDeliveryTime(Dorse dorse, BoxController controller){
        ArrayList<Box> boxes = controller.getAllBoxes();
        double sumWeigth = 0;
        for (int i = 0; i < boxes.size(); i++) {
            sumWeigth += ((Box)boxes.get(i)).getWeight();
        }      
        return sumWeigth;
    }
    

}
