package core;

import java.util.ArrayList;
import java.util.Arrays;
import org.jacop.constraints.Cumulative;
import org.jacop.core.Domain;
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
 * @author SzOg
 */
public class CLPTaskOptim extends CLPTask {

    protected Store store2 = new Store();
    protected IntVar[] startingTime, durationTimes, resources;
    protected IntVar resourceLimit;
    protected IntVar[][] vars2;
    protected Search search2 = new DepthFirstSearch();
    ArrayList<Domain[]> doms = new ArrayList<>();
    @Override
    public void modeluj() {
//        vars = new IntVar[10];
//        for (int i = 0; i < 10; i++) {
//            vars[i] = new IntVar(store, "var[" + i + "]", 1, 10);
//        }
//        store.impose(new Alldifferent(vars));

        ArrayList<Integer> deliveryTimes = new ArrayList();

        startingTime = new IntVar[3];
        durationTimes = new IntVar[3];
        resources = new IntVar[3];
        resourceLimit = new IntVar(store, 1, 3);

        dorseController.generateDorse("a", 10, 5);
        dorseController.generateDorse("b", 12, 6);
        dorseController.generateDorse("c", 8, 6);
        /*
        for (int i = 0; i < dorseController.getAllDorses().size(); i++) {
            deliveryTimes.add((int)dorseController.getDeliveryTime(BoxController.boxControllers.get(i)));
        }
        int loadingTime = 0; 
        int packTime = 0;  
        for (int i = 0; i < startingTime.length; i++) {             
            startingTime[i] = new IntVar(store2, loadingTime, loadingTime );      
            loadingTime += (int) dorseController.getAllDorses().get(i).getLoadingTime();
            
            durationTimes[i] = new IntVar(store2, deliveryTimes.get(i) + loadingTime, deliveryTimes.get(i) + loadingTime);
           // packTime += deliveryTimes.get(i);
            resources[i] = new IntVar(store2, 1, 1);
        }     
        vars2 = dorseController.putVariablesInMatrix(startingTime, durationTimes, resources, dorseController.getAllDorses().size());
        store2.impose(new Cumulative(startingTime, durationTimes, resources, resourceLimit));*/
        
        int[][] comb = new int[][]{{0, 1, 2},
        {0, 2, 1},
        {1, 0, 2},
        {1, 2, 0},
        {2, 0, 1},
        {2, 1, 0}};

        for (int i = 0; i < dorseController.getAllDorses().size(); i++) {
               deliveryTimes.add((int) dorseController.getDeliveryTime(BoxController.boxControllers.get(i)));
        }
        for (int j = 0; j < 6; j++) {

            int loadingTime = 0;
            int packTime = 0;
            for (int i = 0; i < startingTime.length; i++) {
                startingTime[i] = new IntVar(store2, loadingTime, loadingTime);
                loadingTime += (int) dorseController.getAllDorses().get(comb[j][i] ).getLoadingTime()*100;

                durationTimes[i] = new IntVar(store2, deliveryTimes.get(comb[j][i]) + loadingTime, deliveryTimes.get(comb[j][i]) + loadingTime);
                resources[i] = new IntVar(store2, 1, 1);
            }

            vars2 = dorseController.putVariablesInMatrix(startingTime, durationTimes, resources, dorseController.getAllDorses().size());
            store2.impose(new Cumulative(startingTime, durationTimes, resources, resourceLimit));

            szukaj();
        }
       
    }

    @Override
    public void szukaj() {
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(vars2, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search2.labeling(store2, select);
       // doms.add(search2.getSolution());
        //search2.;
    }

    @Override
    public String returnStoreString() {
        return store2.toString();
    }

}
