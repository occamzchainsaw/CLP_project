
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
    protected IntVar[] startingTime, packingTime, resources;
    protected IntVar resourceLimit;
    protected IntVar[][] vars2;
    protected Search search2 = new DepthFirstSearch();

    @Override
    public void modeluj() {
//        vars = new IntVar[10];
//        for (int i = 0; i < 10; i++) {
//            vars[i] = new IntVar(store, "var[" + i + "]", 1, 10);
//        }
//        store.impose(new Alldifferent(vars));

        ArrayList<Integer> deliveryTimes = new ArrayList();
        


        startingTime = new IntVar[3];
        packingTime = new IntVar[3];
        resources = new IntVar[3];
        resourceLimit = new IntVar(store, 1, 1);

        dorseController.generateDorse("a", 10, 5);
        dorseController.generateDorse("b", 12, 6);
        dorseController.generateDorse("c", 8, 6);
        
        for (int i = 0; i < dorseController.getAllDorses().size(); i++) {
            deliveryTimes.add((int)dorseController.getDeliveryTime(boxController));
        }
        int loadingTime = 0; 
        int packTime = 0;  
        for (int i = 0; i < startingTime.length; i++) {             
            startingTime[i] = new IntVar(store2, loadingTime, loadingTime + 
                    (int) dorseController.getAllDorses().get(i).getLoadingTime());
            
            loadingTime += (int) dorseController.getAllDorses().get(i).getLoadingTime();
            
            packingTime[i] = new IntVar(store2, packTime, deliveryTimes.get(i) + packTime);
            packTime += deliveryTimes.get(i);
            resources[i] = new IntVar(store2, 1, 1);
        }

        vars2 = dorseController.putVariablesInMatrix(startingTime, packingTime, resources, dorseController.getAllDorses().size());
        store2.impose(new Cumulative(startingTime, packingTime, resources, resourceLimit));
    }
    
    @Override
    public void szukaj() {
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(vars2, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search2.labeling(store2, select);
        Domain[] dom = search2.getSolution();
        System.out.println("Search 2");
        System.out.println(Arrays.toString(dom));
       
    }
     
    @Override
    public String returnStoreString() {
        return store2.toString();
    }

}
