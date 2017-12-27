package KNN_eucledian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
         
         //distance
         
         double[] query = {16.0,21.5,28};
         
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
 			
	
	}

}
