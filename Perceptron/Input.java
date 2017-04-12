import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Input {
	public static double [][] data = new double [3][100];
	public static int data_size;
	
	public static int getLabelOneSize() {
		return data_size;
	}


	public static void setLabelOneSize(int label_oneSize) {
		data_size = label_oneSize;
	}


	public static double[][] getLabel_one() {
		return data;
	}


	public static void setLabel_one(double[][] labelOne) {
		data = labelOne;
	}

	public static void input(){
	
			String path="C:\\Users\\dell\\Desktop\\test.txt";
			String tempString = null;
	        File file_labelOne=new File(path);
	      
	        if(!file_labelOne.exists())
	        	System.err.println("文件不存在");
	      
	    	try {
	    			BufferedReader br=new BufferedReader(new FileReader(file_labelOne)); 
	    			
	    			int tempIndex1 = 0;
	    		
	    			while((tempString = br.readLine()) != null){
	    				String[] temp = tempString.split(",");
	    				data[0][tempIndex1] = Double.parseDouble(temp[0]);
     	    			data[1][tempIndex1] = Double.parseDouble(temp[1]);
	    				data[2][tempIndex1] = Double.parseDouble(temp[2]);
	    				tempIndex1++;
	    				
	    			}
	    			data_size = tempIndex1;
	    			
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
	}

}
