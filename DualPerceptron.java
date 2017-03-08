
public class DualPerceptron {
	//权重
	public static double[] a = new double [3];
	//偏置
	public static double b = 0;
	//学习率
	public static double rate = 1;
	//数据集，第三行代表类型
	public static double [][] data = Input.getLabel_one();
	//数据集大小
	public static int data_size = Input.getLabelOneSize();
	//迭代次数
	public static int diedainum = 0;
	//gram矩阵
	public static double g[][] = new double[data_size][data_size];
	
	public static void output() {
		System.out.println("迭代次数："+ diedainum);
		System.out.println("a:("+a[0]+","+a[1]+a[2]+")");
		System.out.println("b:"+b);
		gramMatrix();
	}
	
	public static void gramMatrix() {
		
		for (int i = 0; i < data_size; i++) {
			for (int j = 0; j < data_size; j++) {
				g[i][j] = data[0][i] * data[0][j] + data[1][i] * data[1][j];
			}
		}
	}
		
	public static void jisuan() {
		
		diedainum++;
		
		for (int i = 0; i < data_size; i++) {
			double sum = 0;
			
			for (int j = 0; j < data_size; j++) {
				sum = sum + a[j]*g[i][j]*data[2][j];
				
				}
			//误分条件
			double temp = data[2][i] * (b + sum);
			
			if (temp > 0.0) {
				continue;
			}
			else {
				updateWeight(i);
				System.out.println("误分点：x"+(i+1));
				jisuan();
				
			}
			
		}
		
	}

	private static void updateWeight(int index) {
		a[index] = a[index] + rate;
		b = b + data[2][index];
		System.out.println("\n"+"迭代次数："+ diedainum);
		System.out.println("a:("+a[0]+","+a[1]+","+a[2]+")");
		System.out.println("b:"+b);
	}
}
