//Vivian Ellis
//CSCD 429 HW 4
//k means clustering EXTRA CREDIT
import java.util.*;
import java.lang.*;
import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.ChartUtilities;
public class extracredit
{
   Double d = new Double(2.2);
   static double[][] dataset =new double[600][60];  
   
   TimeSeries chartone = new TimeSeries(d);
   
   public static void main( final String[ ] args )throws Exception {
      TimeSeries series = new TimeSeries( "Random Data" );
      TimeSeriesCollection seriesCollection = new TimeSeriesCollection();
      Second current = new Second();
   
      String temp;
      int row =0;
      String[] incomingFile = {"clusterOne.txt","clusterTwo.txt","clusterThree.txt","clusterFour.txt","clusterFive.txt","clusterSix.txt"};
      String[] fileName = {"controlChartOne.JPEG","controlChartTwo.JPEG","controlChartThree.JPEG","controlChartFour.JPEG","controlChartFive.JPEG","controlChartSix.JPEG"};
       
      for(int i=0;i<6;i++){
      seriesCollection = new TimeSeriesCollection();
      
    try {
  
      BufferedReader br = new BufferedReader(new FileReader(incomingFile[i]));
         
      while ((temp = br.readLine()) != null) 
      {
         String[] vals = temp.split("\\s+");
                        
         for (int col = 0; col < 60; col++) 
         {
            series.add( current ,Double.parseDouble(vals[col]) );
            current = ( Second ) current.next( );
         }
         current = new Second(); //restart time to beginning
         row++;
         seriesCollection.addSeries(series);
         series = new TimeSeries( "Random Data" );
      }//end while 
      
      br.close();
      }//end try
      catch ( SeriesException e ) {
            System.err.println( "Error adding to series" );
      }//end catch
      
      
      final XYDataset dataset=( XYDataset )seriesCollection;
      JFreeChart timechart = ChartFactory.createTimeSeriesChart(
         "title", 
         "", 
         "", 
         dataset,
         false, 
         false, 
         false);
         
      int width = 600;   /* Width of the image */
      int height = 600;  /* Height of the image */ 
      File timeChart = new File(fileName[i]); 
      ChartUtilities.saveChartAsJPEG( timeChart, timechart, width, height );
      }//end for loop
   }//end main
}//end class