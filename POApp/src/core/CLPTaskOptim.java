/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import org.jacop.constraints.Alldifferent;
import org.jacop.constraints.Cumulative;
import org.jacop.core.IntVar;
import org.jacop.core.Store;

/**
 *
 * @author SzOg
 */
public class CLPTaskOptim extends CLPTask {
    
    protected Store store2 = new Store();
    protected IntVar[] startingTime, packingTime, resources;
    protected IntVar  resourceLimit;
    @Override
    public void modeluj() {
//        vars = new IntVar[10];
//        for (int i = 0; i < 10; i++) {
//            vars[i] = new IntVar(store, "var[" + i + "]", 1, 10);
//        }
//        store.impose(new Alldifferent(vars));

        
        startingTime = new IntVar[3];
        packingTime = new IntVar[3];
        resources = new IntVar[3];
        resourceLimit = new IntVar(store, 1, 1);
        
        dorseController.generateDorse("a", 10, 5);
        dorseController.generateDorse("b", 12, 6);
        dorseController.generateDorse("c", 8, 6);
        
        for (int i = 0; i < startingTime.length; i++) {
            int loadingTime = (int)dorseController.getAllDorses().get(i).getLoadingTime();
            startingTime[i] = new IntVar(store2, 0, 100); 
            packingTime[i] = new IntVar(store2, loadingTime, loadingTime);
            resources[i] = new IntVar(store2, 1, 1);  
        }
        
        store2.impose(new Cumulative(startingTime, packingTime, resources, resourceLimit));
    }
    
}
