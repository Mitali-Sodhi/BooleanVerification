import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYCrosshairState;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.*;
public class LineChart {

	
	
	static  JFreeChart chart;
	static int count;
	static ChartFrame frame1;
    public static void generateChart(ArrayList<Double> Y,ArrayList<Double> x,String label,Color c) {
        XYSeries series = new XYSeries(label);
        for(int i=0;i<Y.size();i++)
        {	
           
        	series.add(x.get(i), Y.get(i));
        	
        }
        XYDataset xyDataset = new XYSeriesCollection(series);
        XYSplineRenderer xYSplineRenderer=new XYSplineRenderer ();
       if(chart==null)
       {   
         chart = ChartFactory.createXYLineChart(
            "3SAT", "alpha(k/n)", "unsat/total",
            xyDataset, PlotOrientation.VERTICAL, true, true, false);
        
       }
       
        
        XYPlot plot = chart.getXYPlot();
        
        plot.setDataset(count, xyDataset);
        plot.setRenderer(count, new XYSplineRenderer());
       
        new XYCrosshairState().getAnchorY(); 
              
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        //xAxis.setTickUnit(new NumberTickUnit(1));
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        //yAxis.setTickUnit(new NumberTickUnit(0.01));
        plot.getRendererForDataset(plot.getDataset(count)).setSeriesPaint(count, c);
       
        if(frame1==null)
        {	
        	frame1 = new ChartFrame("XYLine Chart", chart);
        }		
        frame1.setVisible(true);
        frame1.setSize(400, 400);
        count++;
    }
}
