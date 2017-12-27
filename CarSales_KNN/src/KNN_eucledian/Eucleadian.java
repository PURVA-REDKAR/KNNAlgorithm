package KNN_eucledian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Eucleadian {
	
	static double[][] data=new double[200][3]; 
	static List<CarList> car_investment = new ArrayList<CarList>();
	private static List getCarListFromExcel() {
		
		String csvFile = "C:/Users/purva/eclipse-workspace/CarSales_KNN/Car_sales.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int i = 0,j=0,itrator = 0;
        
        
        try {
			br = new BufferedReader(new FileReader(csvFile));
			
			   while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] car = line.split(cvsSplitBy);
	               // System.out.println("car" +car[2]);

	               if(itrator!=0)
	               {  
	            	   
	            	   //4 year Sales
	            	   
	            	  data[i][0]= Double.parseDouble(car[3]);
	            	
	            	 //  System.out.println(" file"+Double.parseDouble(car[2]));
	            	//   System.out.println("data"+data[i][0]);
	                //Price in thousands
	            	   data[i][1] =Double.parseDouble(car[5]);
	              
	              //Fuel efficiency
		                data[i][2] = Double.parseDouble(car[13]);
		                

		                
		               
		             
		                
		               car_investment.add(new CarList(data[i],car[16]));
	              // System.out.println("Sales in thousands = " +data[i][0] + " , Price in thousands=" +data[i][1]+"Fuel efficiency" +data[i][2]+"pp"+car[16]  );
	             //  System.out.println(car_investment );
		               
		               i++;
	             
	            //   }
	              
	            }
	               
	               itrator++;
		}
			   
        }catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	static class Result {	
		double distance;
		String Investment;
		public Result(double distance, String Investment){
			this.Investment = Investment;
			this.distance = distance;	    	    
		}
	}
	
	static class DistanceComparator implements Comparator<Result> {
		@Override
		public int compare(Result a, Result b) {
			return a.distance < b.distance ? -1 : a.distance == b.distance ? 0 : 1;
		}
	}
	
	  
	public static void main(String[] args) {
		
		Eucleadian eu = new Eucleadian();
		List<Result> rList = new ArrayList<Result>();
		
		eu.getCarListFromExcel();

         int k=3; //K values
         double fouryear,price,fueleff;
         
         System.out.println("Enter 4 year value,price and fuel Efficiency");
         
         Scanner in = new Scanner(System.in);
         fouryear = in.nextDouble();
         price = in.nextDouble();
         fueleff = in.nextDouble();
         
         //4 year Sales
         
         
       //Price in thousands
         
         //Fuel efficiency
         
         //distance
         
         double[] query = { fouryear,price,fueleff};
         
         for(CarList cars : car_investment){
 			double dist = 0.0;  
 			for(int j = 0; j < cars.carAttributes.length; j++){    	     
 				dist += Math.pow(cars.carAttributes[j] - query[j], 2) ;
 				    	     
 			}

 			double distance = Math.sqrt( dist );
 			//System.out.println(distance+"city"+cars.Investmet);
 			rList.add(new Result(distance,cars.Investmet));
 			
         }
 			
 			//System.out.println(resultList);
 			Collections.sort(rList,new DistanceComparator());
 			
 			String[] near_invest = new String[k];
 			
 			int x1=0;
 			//for(Result t:rList) {
 				//System.out.println(t.Investment+""+t.distance);
 				
 				//if(x1<k)
 				//{
 			//	near_invest[x1]=t.Investment;
 			//	System.out.println(near_invest[x1]+" " + x1);
 			//	x1++;
 			//	}
 			//}
 			
 			for(int x=0;x<k;x++)
 			{
 				near_invest[x] = rList.get(x).Investment;
 				System.out.println(near_invest[x]+" " + x);
 			}
 			
 			String ClassMajority = FindMajority(near_invest);
 			System.out.println("Class of new instance is: "+ClassMajority); 
	
	}
	
	private static String FindMajority(String[] majarray)
	{
		//find unique
		Set<String> h = new HashSet<String>(Arrays.asList(majarray));
		String[] uniqueValues = h.toArray(new String[0]);
		int[] counts = new int[uniqueValues.length];
		for (int i = 0; i < uniqueValues.length; i++) {
			for (int j = 0; j < majarray.length; j++) {
				if(majarray[j].equals(uniqueValues[i])){
					counts[i]++;
				}
			}        
		}
		
		int max = counts[0];
		for (int counter = 1; counter < counts.length; counter++) {
			if (counts[counter] > max) {
				max = counts[counter];
			}
		}
		System.out.println("max # of occurences: "+max);
		
		//find number of occurances or mode of same frequency
        
		int freq = 0;
		for (int counter = 0; counter < counts.length; counter++) {
			if (counts[counter] == max) {
				freq++;
			}
		}
		
		int index = -1;
		if(freq==1){
			for (int counter = 0; counter < counts.length; counter++) {
				if (counts[counter] == max) {
					index = counter;
					break;
				}
			}
			return uniqueValues[index];
		}
		
		else{//we have multiple modes
			int[] i1 = new int[freq];//array of indices of modes
			System.out.println("multiple majority classes: "+freq+" classes");
			int ixi = 0;
			for (int counter = 0; counter < counts.length; counter++) {
				if (counts[counter] == max) {
					i1[ixi] = counter;//save index of each max count value
					ixi++; // increase index of ix array
				}
			}
			
			
			
			for (int counter = 0; counter < i1.length; counter++)         
				System.out.println("class index: "+i1[counter]);       

			//now choose one at random
			Random generator = new Random();        
			//get random number 0 <= rIndex < size of ix
			int rIndex = generator.nextInt(i1.length);
			System.out.println("random index: "+rIndex);
			int nIndex = i1[rIndex];
			//return unique value at that index 
			return uniqueValues[nIndex];
		}
		
		
		
	}
}
