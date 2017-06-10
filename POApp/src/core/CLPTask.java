package core;

import java.util.ArrayList;
import java.util.Arrays;
import org.jacop.constraints.Cumulative;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.DepthFirstSearch;
import org.jacop.search.IndomainMin;
import org.jacop.search.Search;
import org.jacop.search.SelectChoicePoint;
import org.jacop.search.SmallestDomain;
import org.jacop.constraints.Diff2;
import org.jacop.core.Domain;
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
    BoxController boxController;
    ArrayList<BoxController> boxControllers;
    DorseController dorseController = new DorseController();
    protected IntVar[][] vars;
    protected IntVar[] originXIntVars, originYIntVars, lenXIntVars, lenYIntVars;
    CLPTaskOptim optim;
    
    public void modeluj() {
        
        optim = new CLPTaskOptim();
        optim.modeluj();
        Dorse dorse = new Dorse("Dorse-1", 5, 10);
        // Generating boxes
        boxControllers = new ArrayList<>();
        
        boxController = new BoxController();
        boxController.generateBoxes(4, 2, 2);
        boxController.generateBoxes(5, 3, 2);
        boxController.generateBoxes(4, 1, 1);
        int boxNum = boxController.getAllBoxes().size();
        
        boxControllers.add(boxController);
        boxControllers.add(boxController);
        boxControllers.add(boxController);
        

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

            vars = boxController.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
            store.impose(new Diff2(vars));
            
    }
   
        
    public void szukaj() {
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(vars, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search.labeling(store, select);
        Domain[] dom = search.getSolution();
        System.out.println(Arrays.toString(dom));
        optim.szukaj();
       
    }

    public String returnStoreString() {
        return store.toString();
    }

}
