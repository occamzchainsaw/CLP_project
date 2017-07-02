package core;

import java.util.ArrayList;
import org.jacop.constraints.Alldifferent;
import org.jacop.constraints.Constraint;
import org.jacop.constraints.Cumulative;
import org.jacop.constraints.Element;
import org.jacop.constraints.Max;
import org.jacop.core.Domain;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.core.Var;
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
    protected IntVar[] startingTime, durationTimes, resources,completionTimes;
    Var[] searchVariables;
    protected IntVar resourceLimit,Tmax;
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
        completionTimes = new IntVar[3];
        startingTime = new IntVar[3];
        durationTimes = new IntVar[3];
        resources = new IntVar[3];
        resourceLimit = new IntVar(store, 1, 3);
        searchVariables = new Var[7];
        
        
        IntVar index1 = new IntVar(store2, "index1", 1, 3);
        IntVar index2 = new IntVar(store2, "index2", 1, 3);
        IntVar index3 = new IntVar(store2, "index3", 1, 3);
        
        IntVar v1 = new IntVar(store2, "v1", 1, 100);
        IntVar v2 = new IntVar(store2, "v2", 1, 100);
        IntVar v3 = new IntVar(store2, "v3", 1, 100);
        
        IntVar z1 = new IntVar(store2, "z1", 1, 10000);
        IntVar z2 = new IntVar(store2, "z2", 1, 10000);
        IntVar z3 = new IntVar(store2, "z3", 1, 10000);
        
        int[] loadTimes = {(int)dorseController.allDorses.get(0).getLoadingTime(),
            (int)dorseController.allDorses.get(1).getLoadingTime(),
            (int)dorseController.allDorses.get(2).getLoadingTime(),
        };
        
        System.out.println(loadTimes[1]);
        
          int[] delTimes = {(int)dorseController.allDorses.get(0).getLoadingTime()+(int)dorseController.getDeliveryTime(BoxController.boxControllers.get(0)),
            (int)dorseController.allDorses.get(1).getLoadingTime()+(int)dorseController.getDeliveryTime(BoxController.boxControllers.get(1)),
            (int)dorseController.allDorses.get(2).getLoadingTime()+(int)dorseController.getDeliveryTime(BoxController.boxControllers.get(2)),
        };
        
        Constraint elementCons1 = new Element(index1,loadTimes,v1);
        store2.impose(elementCons1);
        Constraint elementCons2 = new Element(index2,loadTimes,v2);
        store2.impose(elementCons2);
        Constraint elementCons3 = new Element(index3,loadTimes,v3);
        store2.impose(elementCons3);
        
        System.out.println(v1.value());
        
        Constraint elementCons4 = new Element(index1,delTimes,z1);
        store2.impose(elementCons4);
        Constraint elementCons5 = new Element(index2,delTimes,z2);
        store2.impose(elementCons5);
        Constraint elementCons6 = new Element(index3,delTimes,z3);
        store2.impose(elementCons6);
        
        ArrayList<IntVar> indexes = new ArrayList<>();
        indexes.add(index1);
        indexes.add(index2);
        indexes.add(index3);
        Constraint allDifferent = new Alldifferent(indexes);
        store2.impose(allDifferent);
        
        IntVar sum = new IntVar(store2, v1.value()+v2.value(), v1.value()+v2.value());
        
        startingTime[0] = new IntVar(store2, 0, 0);
        startingTime[1] = v1;
        startingTime[2] = new IntVar(store2, v1.value()+v2.value(), v1.value()+v2.value());
        
        durationTimes[0]=z1;
        durationTimes[1]=z2;
        durationTimes[2]=z3;

        resources[0]=index1;
        resources[1]=index2;
        resources[2]=index3;
        vars2 = dorseController.putVariablesInMatrix(startingTime, durationTimes, resources, dorseController.allDorses.size());
        store2.impose(new Cumulative(startingTime, durationTimes, resources, resourceLimit));
        
        for (int i = 0; i < 3; i++) {
             completionTimes[i]= new IntVar(store2, startingTime[i].min()+durationTimes[i].min(), startingTime[i].max()+durationTimes[i].max());
             //System.out.println("comp time " + i + completionTimes[i]);
        }
        //Tmax = new Var();
        Tmax = new IntVar(store2,0,50000);
        store2.impose(new Max(completionTimes,Tmax));
       
        
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
        store2.impose(new Cumulative(startingTime, durationTimes, resources, resourceLimit));
        
        */
        /*
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
       */
    }

    @Override
    public void szukaj() {
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(vars2, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search2.labeling(store2, select,Tmax);
        search2.printAllSolutions();
    }

    @Override
    public String returnStoreString() {
        return store2.toString();
    }

}
