/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SmallestDomain;
import org.jacop.constraints.Diff2;
import org.jacop.search.SimpleMatrixSelect;

/**
 *
 * @author SzOg
 */
public class CLPTask {

    /* 
        /WE SHOULD CREATE VECTORS FOR O1 O2 L1 L2
        //ADD ALIAS TO EACH BOX
        //CHECK CONSISTENCY AND PRINT RESULTS BEFORE AND AFTER DIFF2
        //INITIALIZE RECTANGLES INSIDE MODELUJ, FOR DINAMIC SIZE OF BOXES
    */
    
    protected Store store = new Store();
    protected Search search = new DepthFirstSearch();
    protected ArrayList<Box> boxes;
    protected IntVar[][] vars;
    protected IntVar[] originXIntVars, originYIntVars, lenXIntVars, lenYIntVars;

    public void modeluj() {

        boxes = new BoxController().generateBoxes(5, 3, 3, "Box");
        int boxNum = boxes.size();
        vars = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];
        
        for (int i = 0; i < boxNum; i++) {
            originXIntVars[i] = (new IntVar(store, "Origin X:" + i, 0, 8));
            originYIntVars[i] = (new IntVar(store, "Origin Y:" + i, 0, 3));
            lenXIntVars[i] = (new IntVar(store, "Length X:" + i, boxes.get(i).getSideX(), boxes.get(i).getSideY()));
            lenYIntVars[i] = (new IntVar(store, "Length X:" + i, boxes.get(i).getSideX(), boxes.get(i).getSideY()));
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
    }

    public void szukaj() {
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(vars, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search.labeling(store, select);
    }

    public String returnStoreString() {
        return store.toString();
    }

}
