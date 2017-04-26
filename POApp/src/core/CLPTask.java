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
    protected Store store = new Store();
    protected Search search = new DepthFirstSearch();
    Box box = new Box();
    //ArrayList<Box> boxes = box.generateBoxes(5);
    protected IntVar o1 = new IntVar(store, 0, 8) ;
    protected IntVar o2 = new IntVar(store, 0, 3) ;
    protected IntVar l1 = new IntVar(store, 2,2) ;
    protected IntVar l2 = new IntVar(store, 2,2) ;
   //WE SHOULD CREATE VECTORS FOR O1 O2 L1 L2
    //ADD ALIAS TO EACH BOX
    //CHECK CONSISTENCY AND PRINT RESULTS BEFORE AND AFTER DIFF2
    //INITIALIZE RECTANGLES INSIDE MODELUJ, FOR DINAMIC SIZE OF BOXES
    private IntVar[][] rectangles;
    

    protected IntVar[][] vars;
    protected IntVar[] originXIntVars, originYIntVars, lenXIntVars,lenYIntVars ;

    
    public void modeluj() {
        /*
        rectangles=new IntVar[][]{{o1, o2, l1, l2},
            {o1, o2, l1, l2},
            {o1, o2, l1, l2},
            {o1, o2, l1, l2},
            {o1, o2, l1, l2}};
        */
        int boxNum = 4;
        vars = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];
        for (int i = 0; i < boxNum; i++) {
            originXIntVars[i] = (new IntVar(store, "Origin X:" + i, 0, 8));
            originYIntVars[i] = (new IntVar(store, "Origin Y:" + i, 0, 3));
            lenXIntVars[i] = (new IntVar(store, "Length X:" + i, 2, 2));
            lenYIntVars[i] = (new IntVar(store, "Length X:" + i, 2, 2));      
            
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
