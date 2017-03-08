
public class DualPerceptron {
	//权重
	public static double[] a = new double [3];
	//偏置
	public static double b = 0;
	//学习率
	public static double rate = 1;
	//数据集，第三行代表类型
	public static double [][] label_one = Input.getLabel_one();
	//数据集大小
	public static int labelSize = Input.getLabelOneSize();
	//迭代次数
	public static int diedainum = 0;
	//gram矩阵
	public static double g[][] = new double[labelSize][labelSize];
	
	public static void output() {
		System.out.println("迭代次数："+ diedainum);
		System.out.println("a:("+a[0]+","+a[1]+a[2]+")");
		System.out.println("b:"+b);
		gramMatrix();
	}
	
	public static void gramMatrix() {
		
		for (int i = 0; i < labelSize; i++) {
			for (int j = 0; j < labelSize; j++) {
				g[i][j] = label_one[0][i] * label_one[0][j] + label_one[1][i] * label_one[1][j];
			}
		}
	}
		
	public static void jisuan() {
		
		diedainum++;
		
		for (int i = 0; i < labelSize; i++) {
			double sum = 0;
			
			for (int j = 0; j < labelSize; j++) {
				sum = sum + a[j]*g[i][j]*label_one[2][j];
				
				}
			//误分条件
			double temp = label_one[2][i] * (b + sum);
			
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
		b = b + label_one[2][index];
		System.out.println("\n"+"迭代次数："+ diedainum);
		System.out.println("a:("+a[0]+","+a[1]+","+a[2]+")");
		System.out.println("b:"+b);
	}
}
