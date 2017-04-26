/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author SzOg
 */
public class MyChart {
    private XYPlot plot = new XYPlot();
    private JFreeChart chart = null;
    private ChartPanel chartPanel;
    private String valName;
    
    
    /**
     * Tworzy wykres sinusa o zadanej liczbie punktów
     * @param vName nazwa serii
     * @param pointNb liczba punktów wykresu
     * @return 
     */
     public ChartPanel createChart(String vName, int pointNb) {
        valName = vName;
        
        StandardXYItemRenderer renderer
            = new StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES);
        renderer.setSeriesShapesFilled(0, true);
        
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries(valName, calcSin(pointNb));
        
        plot = new XYPlot(dataset, new NumberAxis(), new NumberAxis(), renderer);         
        chart = new JFreeChart(plot);
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        chartPanel.setVisible(true);
        
        return chartPanel;
    }
     
     /**
      * Zwraca dwuwymiarową tablicę z rzędnymi i odciętymi wwykresu sinusa
      * @param pointNb liczba punktów wykresu
      * @return dane do wykresu
      */
     private double[][] calcSin(int pointNb) {
        double[][] y = new double[2][pointNb];
        for (int i = 0; i < pointNb; i++) {
            y[0][i] = (2*Math.PI/(pointNb-1))*i;
            y[1][i] = Math.sin((2*Math.PI/(pointNb-1))*i);   
        }
        return y;
     }
}
