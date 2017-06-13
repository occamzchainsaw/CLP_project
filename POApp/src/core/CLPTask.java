package core;

import java.util.ArrayList;
import java.util.Arrays;
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
    protected BoxController boxController,boxController2,boxController3;
    DorseController dorseController = new DorseController();
    protected IntVar[][] vars1,vars2,vars3;
    protected IntVar[] originXIntVars, originYIntVars, lenXIntVars, lenYIntVars;
    CLPTaskOptim optim;
    Dorse dorse;
    public void modeluj() {
        
        dorseController.generateDorse("a", 10, 5);
        dorseController.generateDorse("b", 12, 6);
        dorseController.generateDorse("c", 8, 6);
        
        // Generating boxes
        ArrayList<BoxController> boxControllers = BoxController.boxControllers;
        
        boxController = new BoxController();
        boxController2 = new BoxController();
        boxController3 = new BoxController();
        
        boxController.generateBoxes(4, 2, 2);
        boxController.generateBoxes(5, 3, 2);
        boxController.generateBoxes(4, 1, 1);
        
        boxController2.generateBoxes(5, 4, 2);
        boxController2.generateBoxes(2, 1, 2);
        boxController2.generateBoxes(3, 3, 2);
        
        boxController3.generateBoxes(2, 4, 2);
        boxController3.generateBoxes(2, 1, 2);
        boxController3.generateBoxes(1, 3, 2);
        
        int boxNum = boxController.getAllBoxes().size();
        
        boxControllers.add(boxController);
        boxControllers.add(boxController2);
        boxControllers.add(boxController3);     

        vars1 = new IntVar[boxNum][4];
        vars2 = new IntVar[boxNum][4];
        vars3 = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];

            for (int i = 0; i < boxNum; i++) {
                dorse = dorseController.getAllDorses().get(0);
                originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController.getAllBoxes())));
                originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController.getAllBoxes())));
                lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController.getAllBoxes().get(i).getSideX(), boxController.getAllBoxes().get(i).getSideX()));
                lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController.getAllBoxes().get(i).getSideY(), boxController.getAllBoxes().get(i).getSideY()));
            }

            vars1= boxController.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
            store.impose(new Diff2(vars1));
            
            for (int i = 0; i < boxNum; i++) {
                dorse = dorseController.getAllDorses().get(1);
                originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController.getAllBoxes())));
                originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController.getAllBoxes())));
                lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController.getAllBoxes().get(i).getSideX(), boxController.getAllBoxes().get(i).getSideX()));
                lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController.getAllBoxes().get(i).getSideY(), boxController.getAllBoxes().get(i).getSideY()));
            }

            vars2 = boxController.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
            store.impose(new Diff2(vars2));
            
            for (int i = 0; i < boxNum; i++) {
                dorse = dorseController.getAllDorses().get(1);
                originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController.getAllBoxes())));
                originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController.getAllBoxes())));
                lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController.getAllBoxes().get(i).getSideX(), boxController.getAllBoxes().get(i).getSideX()));
                lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController.getAllBoxes().get(i).getSideY(), boxController.getAllBoxes().get(i).getSideY()));
            }

            vars3 = boxController.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
            store.impose(new Diff2(vars3));
            optim = new CLPTaskOptim();
            optim.modeluj();
    }
   
        
    public void szukaj() {
        SelectChoicePoint<IntVar> select1 = new SimpleMatrixSelect(vars1, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search.labeling(store, select1);
        SelectChoicePoint<IntVar> select2 = new SimpleMatrixSelect(vars2, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search.labeling(store, select2);
        SelectChoicePoint<IntVar> select3 = new SimpleMatrixSelect(vars3, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search.labeling(store, select3);
      
//        Domain[] dom = search.getSolution();
   //     System.out.println(Arrays.toString(dom));
        search.printAllSolutions();
        optim.szukaj();
       
    }

    public String returnStoreString() {
        return store.toString();
    }

}
