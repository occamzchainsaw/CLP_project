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
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SimpleMatrixSelect;
import org.jacop.search.SmallestDomain;

/**
 *
 * @author zahid
 */
public class DorseController {

    public static int LAST_DORSE_ID;
    
    public void searchDiff2(Store store, Dorse dorse, BoxController boxController){
        Search search = new DepthFirstSearch();
        IntVar[][] vars;
        IntVar[] originXIntVars, originYIntVars, lenXIntVars, lenYIntVars;
        int boxNum = boxController.getAllBoxes().size();
        
        vars = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];

        for (int i = 0; i < boxNum; i++) {
            originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController.getAllBoxes())));
            originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController.getAllBoxes())));
            lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController.getAllBoxes().get(i).getSideX(), boxController.getAllBoxes().get(i).getSideX()));
            lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController.getAllBoxes().get(i).getSideY(), boxController.getAllBoxes().get(i).getSideY()));
        }

        int col = 0;
        for (int i = 0; i < boxNum; i++) {
            col = 0;
            vars[i][col++] = originXIntVars[i];
            vars[i][col++] = originYIntVars[i];
            vars[i][col++] = lenXIntVars[i];
            vars[i][col++] = lenYIntVars[i];
        }
        store.impose(new Diff2(vars));
        
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(vars, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search.labeling(store, select);
    }
    
    public double getDeliveryTime(Dorse dorse, BoxController controller){
        ArrayList boxes = controller.getAllBoxes();
        double sumWeigth = 0;
        for (int i = 0; i < boxes.size(); i++) {
            sumWeigth += ((Box)boxes.get(i)).getWeight();
        }
        return sumWeigth;
    }
    

}
