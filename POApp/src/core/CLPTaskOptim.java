package core;

import java.util.ArrayList;
import org.jacop.constraints.Constraint;
import org.jacop.constraints.Diff2;
import org.jacop.constraints.Max;
import org.jacop.constraints.SumInt;
import org.jacop.constraints.XgtY;
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
    protected IntVar[] startingTime, durationTimes, resources,completionTimes,sumVar,time;
    Var[] searchVariables;
    protected IntVar resourceLimit,Tmax,timeLimit,index1,index2,index3,ltVar1,ltVar2,ltVar3;
    protected IntVar[][] varsOptim;
    protected Search search2 = new DepthFirstSearch();
    ArrayList<Domain[]> doms = new ArrayList<>();
    protected IntVar[] originXoptim, originYoptim, timeXoptim, truckYoptim;
    @Override

    public void modeluj() {

        completionTimes = new IntVar[3];
        startingTime = new IntVar[3];
        durationTimes = new IntVar[3];
        resources = new IntVar[3];
        resourceLimit = new IntVar(store, 1, 3);
        searchVariables = new Var[7];
        originXoptim=new IntVar[3];
        originYoptim=new IntVar[3];
        timeXoptim=new IntVar[3];
        truckYoptim=new IntVar[3];
          
        int[] loadTimes = {(int)DorseController.allDorses.get(0).getLoadingTime(),
            (int)DorseController.allDorses.get(1).getLoadingTime(),
            (int)DorseController.allDorses.get(2).getLoadingTime(),
        };
       
          int[] delTimes = {(int)DorseController.allDorses.get(0).getLoadingTime()+(int)dorseController.getDeliveryTime(BoxController.boxControllers.get(0)),
            (int)DorseController.allDorses.get(1).getLoadingTime()+(int)dorseController.getDeliveryTime(BoxController.boxControllers.get(1)),
            (int)DorseController.allDorses.get(2).getLoadingTime()+(int)dorseController.getDeliveryTime(BoxController.boxControllers.get(2)),
        };
          
        //////////PREPARATION ON DIFF2 ARGUMENTS
        for (int i = 0; i < 3; i++) {
            
            originXoptim[i] = (new IntVar(store2, "Starting time->" + i,0,loadTimes[0]+loadTimes[1]+loadTimes[2] ));
            originYoptim[i] = (new IntVar(store2, "Truck->" + i,0,2 ));
            timeXoptim[i] = (new IntVar(store2, "Total time->" + i,loadTimes[i]+delTimes[i],loadTimes[i]+delTimes[i] ));
            truckYoptim[i] = (new IntVar(store2, "Lenght Y->" + i,1,1 ));   
        }
        //////////////////////SUMMING STARTTIME+LOADTIME////////////
        ltVar1=new IntVar(store2,"originXoptim + loadTimes",loadTimes[0],loadTimes[0]);
        IntVar sum1 = new IntVar(store2, "sum1",1, 500);
        IntVar[] sumVar1 = {ltVar1,originXoptim[0]};
        Constraint sumIntCtr1 = new SumInt(store2,sumVar1, "==", sum1); 
        store2.impose(sumIntCtr1);
        
        ltVar2=new IntVar(store2,"originXoptim + loadTimes",loadTimes[1],loadTimes[1]);
        IntVar sum2 = new IntVar(store2, "sum2",1, 500);
        IntVar[] sumVar2 = {ltVar2,originXoptim[1]};
        Constraint sumIntCtr2 = new SumInt(store2,sumVar2, "==", sum2); 
        store2.impose(sumIntCtr2);
        
        ltVar3=new IntVar(store2,"originXoptim + loadTimes",loadTimes[2],loadTimes[2]);
        IntVar sum3 = new IntVar(store2, "sum3",1, 500);
        IntVar[] sumVar3 = {ltVar3,originXoptim[2]};
        Constraint sumIntCtr3 = new SumInt(store2,sumVar3, "==", sum3); 
        store2.impose(sumIntCtr3);
        ///////////////////////////////////////      
        store2.impose(new XgtY(originXoptim[2],sum2) );
        store2.impose(new XgtY(originXoptim[1],sum1) );
        
        ///////////////GETTING COMPLETION TIME FOR MINIMIZATION/////////////////
        IntVar sum4 = new IntVar(store2, "sum4",1, 2000);
        IntVar sum5 = new IntVar(store2, "sum5",1, 2000);
        IntVar sum6 = new IntVar(store2, "sum6",1, 2000);
        IntVar[] getSumTime1 = {originXoptim[0],timeXoptim[0]};
        IntVar[] getSumTime2 = {originXoptim[1],timeXoptim[1]};
        IntVar[] getSumTime3 = {originXoptim[2],timeXoptim[2]};
        Constraint sumIntCtr4 = new SumInt(store2,getSumTime1, "==", sum4); 
        Constraint sumIntCtr5 = new SumInt(store2,getSumTime2, "==", sum5); 
        Constraint sumIntCtr6 = new SumInt(store2,getSumTime3, "==", sum6); 
        store2.impose(sumIntCtr4);
        store2.impose(sumIntCtr5);
        store2.impose(sumIntCtr6);
        completionTimes[0]=sum4;
        completionTimes[1]=sum5;
        completionTimes[2]=sum6;
        Tmax = new IntVar(store2,0,50000);
        store2.impose(new Max(completionTimes,Tmax));
        //////////////////////////////////////////
        
        
        varsOptim =new IntVar[][]{{originXoptim[0],originXoptim[1],originXoptim[2]}, {originYoptim[0],originYoptim[1],originYoptim[2]}, {timeXoptim[0],timeXoptim[1],timeXoptim[2]}, {truckYoptim[0],truckYoptim[1],truckYoptim[2]}};
        store2.impose(new Diff2(originXoptim, originYoptim, timeXoptim, truckYoptim));
    }

    @Override
    public void szukaj() {
        SelectChoicePoint<IntVar> select = new SimpleMatrixSelect(varsOptim, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());
        search2.labeling(store2, select, Tmax);
        //search2.printAllSolutions();
    }

    @Override
    public String returnStoreString() {
        return store2.toString();
    }

}
