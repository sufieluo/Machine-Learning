import java.util.ArrayList;


public class Utilz {
	
	//�������ݵ�dimentionά�ķ����С
	public static double variance(ArrayList<ArrayList<Integer>> data,int dimention){
        double vsum = 0;
        double sum = 0;
        for(ArrayList<Integer> d:data){
            sum+=d.get(dimention);
            vsum+=d.get(dimention)*d.get(dimention);
        }
        int n = data.size();
        return vsum/n-Math.pow(sum/n, 2);
    }
	

}
