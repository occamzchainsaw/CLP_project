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
import org.jacop.core.Var;
import org.jacop.search.SimpleSelect;

/**
 *
 * @author SzOg
 */
public class CLPTask {

    protected Store store = new Store();
    protected Search search = new DepthFirstSearch();
    protected BoxController boxController, boxController2, boxController3;
    DorseController dorseController = new DorseController();
    protected IntVar[][] vars1, vars2, vars3;
    protected IntVar[] originXIntVars, originYIntVars, lenXIntVars, lenYIntVars;
    ArrayList<Var> allVars;
    Var[] vars;
    CLPTaskOptim optim;
    Dorse dorse;

    public void modeluj() {

        dorseController.generateDorse("a", 10, 5);
        dorseController.generateDorse("b", 12, 6);
        dorseController.generateDorse("c", 10, 6);

        // Generating boxes
        ArrayList<BoxController> boxControllers = BoxController.boxControllers;

        boxController = new BoxController();
        boxController2 = new BoxController();
        boxController3 = new BoxController();

        boxController.generateBoxes(4, 2, 2);
        boxController.generateBoxes(4, 3, 2);
        boxController.generateBoxes(4, 1, 1);

        boxController2.generateBoxes(4, 4, 2);
        boxController2.generateBoxes(5, 1, 2);
        boxController2.generateBoxes(5, 3, 2);

        boxController3.generateBoxes(2, 4, 2);
        boxController3.generateBoxes(2, 1, 2);
        boxController3.generateBoxes(5, 3, 2);

        boxControllers.add(boxController);
        boxControllers.add(boxController2);
        boxControllers.add(boxController3);

        int boxNum = boxController.getAllBoxes().size();
        vars1 = new IntVar[boxNum][4];
        vars2 = new IntVar[boxNum][4];
        vars3 = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];
        allVars = new ArrayList();

        dorse = DorseController.allDorses.get(0);
        for (int i = 0; i < boxNum; i++) {
            originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController.getAllBoxes())));
            originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController.getAllBoxes())));
            lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController.getAllBoxes().get(i).getSideX(), boxController.getAllBoxes().get(i).getSideX()));
            lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController.getAllBoxes().get(i).getSideY(), boxController.getAllBoxes().get(i).getSideY()));
            allVars.add(originXIntVars[i]);
            allVars.add(originYIntVars[i]);
            allVars.add(lenXIntVars[i]);
            allVars.add(lenYIntVars[i]);
        }

        vars1 = boxController.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
        store.impose(new Diff2(vars1));

        boxNum = boxController2.getAllBoxes().size();
        vars1 = new IntVar[boxNum][4];
        vars2 = new IntVar[boxNum][4];
        vars3 = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];

        dorse = DorseController.allDorses.get(1);
        for (int i = 0; i < boxNum; i++) {

            originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController2.getAllBoxes())));
            originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController2.getAllBoxes())));
            lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController2.getAllBoxes().get(i).getSideX(), boxController2.getAllBoxes().get(i).getSideX()));
            lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController2.getAllBoxes().get(i).getSideY(), boxController2.getAllBoxes().get(i).getSideY()));
            allVars.add(originXIntVars[i]);
            allVars.add(originYIntVars[i]);
            allVars.add(lenXIntVars[i]);
            allVars.add(lenYIntVars[i]);
        }

        vars2 = boxController2.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
        store.impose(new Diff2(vars2));

        boxNum = boxController3.getAllBoxes().size();
        vars1 = new IntVar[boxNum][4];
        vars2 = new IntVar[boxNum][4];
        vars3 = new IntVar[boxNum][4];
        originXIntVars = new IntVar[boxNum];
        originYIntVars = new IntVar[boxNum];
        lenXIntVars = new IntVar[boxNum];
        lenYIntVars = new IntVar[boxNum];

        dorse = DorseController.allDorses.get(2);
        for (int i = 0; i < boxNum; i++) {

            originXIntVars[i] = (new IntVar(store, "Origin X->" + i, dorse.getDorseMinX(), dorse.getDorseMaxX(boxController3.getAllBoxes())));
            originYIntVars[i] = (new IntVar(store, "Origin Y->" + i, dorse.getDorseMinY(), dorse.getDorseMaxY(boxController3.getAllBoxes())));
            lenXIntVars[i] = (new IntVar(store, "Length X->" + i, boxController3.getAllBoxes().get(i).getSideX(), boxController3.getAllBoxes().get(i).getSideX()));
            lenYIntVars[i] = (new IntVar(store, "Length Y->" + i, boxController3.getAllBoxes().get(i).getSideY(), boxController3.getAllBoxes().get(i).getSideY()));
            allVars.add(originXIntVars[i]);
            allVars.add(originYIntVars[i]);
            allVars.add(lenXIntVars[i]);
            allVars.add(lenYIntVars[i]);
        }

        vars3 = boxController3.putVariablesInMatrix(originXIntVars, originYIntVars, lenXIntVars, lenYIntVars, boxNum);
        store.impose(new Diff2(vars3)); 
        vars = new Var[allVars.size()];

        for (int i = 0; i < allVars.size(); i++) {
            vars[i] = allVars.get(i);
        }
        optim = new CLPTaskOptim();
        optim.modeluj();
    }

    public void szukaj() {
        SelectChoicePoint<IntVar> select1 = new SimpleSelect(vars, new SmallestDomain(), new IndomainMin());
        search.labeling(store, select1);
        optim.szukaj();

    }

    public String returnStoreString() {
        return store.toString();
    }

}
