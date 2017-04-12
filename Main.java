import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int data[][] = {{2,3},{5,4},{9,6},{4,7},{8,1},{7,2}};
		int input[][] = {{4,5}};
		ArrayList<ArrayList<Integer>> dataArrayList = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < data.length; i++) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int j = 0; j < data[i].length; j++) {
				 arrayList.add(data[i][j]);
			}
			dataArrayList.add(arrayList);
		}
		
	 KNN knn = new KNN();
	 knn.develop_server(dataArrayList,input);

		
	}

}
