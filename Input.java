import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Input {
	public static double [][] label_one = new double [3][100];
	public static int labelOneSize;
	
	public static int getLabelOneSize() {
		return labelOneSize;
	}


	public static void setLabelOneSize(int label_oneSize) {
		labelOneSize = label_oneSize;
	}


	public static double[][] getLabel_one() {
		return label_one;
	}


	public static void setLabel_one(double[][] labelOne) {
		label_one = labelOne;
	}

	
	public static void input(){
	
		String path="C:\\Users\\dell\\Desktop\\test.txt";
		String tempString = null;
	        File file_labelOne=new File(path);
	      
	        
	        if(!file_labelOne.exists())
	        	System.err.println("ÎÄ¼þ²»´æÔÚ");
	      
	    	try {
	    			BufferedReader br1=new BufferedReader(new FileReader(file_labelOne)); 
	    			
	    			int tempIndex1 = 0;
	    		
	    			while((tempString = br1.readLine()) != null){
	    				String[] temp = tempString.split(",");
	    				label_one[0][tempIndex1] = Double.parseDouble(temp[0]);
     	    			label_one[1][tempIndex1] = Double.parseDouble(temp[1]);
	    				label_one[2][tempIndex1] = Double.parseDouble(temp[2]);
	    				tempIndex1++;
	    				
	    			}
	    			labelOneSize = tempIndex1;
	    			
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
	}

}
