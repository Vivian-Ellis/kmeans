//Vivian Ellis
//CSCD 429 HW 4
//k means clustering
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;

public class kmeans
{
   static double[][] mean = new double[600][2];
   public static void main(String... args)
   {
      int clusters = 6;
      dataset = createdataset();
      mean = calcmean(dataset);
      initilizeClusters(clusters,dataset,mean);
      
      do{
         for(int j=0;j<600;j++)
         {
            assign(centers[0][1],mean[j][1],Math.abs(centers[0][1]-mean[j][1]));
            finalClusters[j][0]=j;
            finalClusters[j][1]=currCluster;
            tempi=0;
            currCluster=0;
         }
      
         update();
      }while(checkCenters()==false);
      
      printClusters();
      printIndividualClusters(0,"clusterOne.txt");
      printIndividualClusters(1,"clusterTwo.txt");
      printIndividualClusters(2,"clusterThree.txt");
      printIndividualClusters(3,"clusterFour.txt");
      printIndividualClusters(4,"clusterFive.txt");
      printIndividualClusters(5,"clusterSix.txt");
   }//end main

   //establish clusters
   static double[][] centers = new double[6][2];
   static double[][] centersCopy = new double[6][2];
   static int[][] finalClusters = new int[600][2];
   public static void initilizeClusters(int k, double[][] dataset,double[][] mean)
   {
      double sum =0;
      
      for(int i =0;i<k;i++)
      {
         Random rand = new Random();
         int randomNum = rand.nextInt(600);
         centers[i][0]=randomNum;
         centers[i][1]=mean[randomNum][1]; 
      }//end for
   }//end clusters method
   
   //step 3
   static double currDist=0;
   static int tempi =0;
   static int currCluster;
   public static void assign(double center,double mean,double dist)
   {      
      currDist = Math.abs(center-mean);
      
      if(currDist < dist)
      {
         dist=currDist;
         currCluster=tempi;
      }
      
      tempi++;
      
      //checked all clusterrs
      if(tempi == 6) return;
      
      assign(centers[tempi][1],mean,dist);
   }//end assign
   
   //build the 2darray from the .txt file
   static double[][] dataset =new double[600][60];   
   public static double[][] createdataset()
   {
      BufferedReader buffer;
      String temp;
      int row =0;
      
      try {
      
      BufferedReader br = new BufferedReader(new FileReader("synthetic_control_data.txt"));
         
      while ((temp = br.readLine()) != null) 
      {
         String[] vals = temp.split("\\s+");
                        
         for (int col = 0; col < 60; col++) 
         {
            dataset[row][col] = Double.parseDouble(vals[col]);
         }
         
         row++;
      }//end while 
      
      br.close();
             
      } catch(Exception e) {
         e.printStackTrace();
      }      
      return dataset;
   }//end createdataset

   public static double[][] calcmean(double[][] dataset)
   {
      double sum =0;
      
      for(int row =0;row<600;row++)
      {        
         for(int col =0;col <60;col++)
         {
            sum+=dataset[row][col];
         }//end inner for 
         mean[row][1]=sum/60;
         mean[row][0]=row;
         sum=0;     
      }//end for
      return mean;
   }//end calcmean
   
   static double[][] updatedMean = new double[6][2];
   public static void update()
   {
      double sum[]=new double[6];
      int num[]=new int[6];
      
      for(int j=0;j<6;j++)
      {
         centersCopy[j][0]=j;
         centersCopy[j][1]=centers[j][1];
         centers[j][1]=0;
      }
      
      for(int i=0;i<600;i++)
      {
         //sum+=mean[finalClusters[i][1]][1];
         if(finalClusters[i][1]==0)
         {
            sum[0]+=mean[i][1];
            num[0]+=1;
         }
         
         if(finalClusters[i][1]==1)
         {
            sum[1]+=mean[i][1];
            num[1]+=1;        
         }
         
         if(finalClusters[i][1]==2)
         {
            sum[2]+=mean[i][1];
            num[2]+=1;        
         }         
         
         if(finalClusters[i][1]==3)
         {
            sum[3]+=mean[i][1];
            num[3]+=1;        
         }
         
         if(finalClusters[i][1]==4)
         {
            sum[4]+=mean[i][1];
            num[4]+=1;        
         } 
         
         if(finalClusters[i][1]==5)
         {
            sum[5]+=mean[i][1];
            num[5]+=1;        
         }                          
      }//end for
      
      for(int k=0;k<6;k++)
      {
         centers[k][1]=(sum[k]/num[k]);
      }//end for      
   }//end update
   
   public static boolean checkCenters()
   {
      for(int i=0;i<6;i++)
      {
         if(centers[i][1]!=centersCopy[i][1])
            return false;
      }//end for
      return true;       
   }//end checkCenters
   
   public static void printClusters()
   {
   try{
      PrintWriter writer = new PrintWriter("results.txt");
      for(int i=0;i<600;i++)
      {
         writer.println("<"+(finalClusters[i][0]+1)+","+(finalClusters[i][1]+1)+">");
      }
      writer.close();
      }catch(Exception e){
         e.printStackTrace();
      }
   }//end printClusters
   
   //create 6 files for each cluster
   public static void printIndividualClusters(int clusterNum,String fileName)
   {
   try{
      PrintWriter writer = new PrintWriter(fileName);
      for(int i=0;i<600;i++)
      {
          if(finalClusters[i][1]==clusterNum)
          {
            //writer.println(mean[i][1]);
            for(int col=0;col<60;col++)
            {
               writer.print(dataset[i][col]+" ");
            }//end inner for
            writer.println("");
         }//end if
      }//end outer for
      
      writer.close();
      }catch(Exception e){
         e.printStackTrace();
      }
   }//end printClusters
}//end kmeans class