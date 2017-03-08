public class Perceptron {
	//权重
	public static double [][] w = new double [2][1];
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
	
	public static void output() {
		System.out.println("\n"+"迭代次数："+ diedainum);
		System.out.println("w:("+w[0][0]+","+w[1][0]+")");
		System.out.println("b:"+b);
	}
		
	public static void jisuan() {
		
		diedainum++;
		
		for (int i = 0; i < labelSize; i++) {
			double temp = (w[0][0] * label_one[0][i] +w[1][0] * label_one[1][i] + b) * label_one[2][i];
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
		w[0][0] = w[0][0] + rate * label_one[2][index] *label_one[0][index];
		w[1][0] = w[1][0] + rate * label_one[2][index] *label_one[1][index];
		b = b + label_one[2][index];
		System.out.println("迭代次数："+ diedainum);
		System.out.println("w:("+w[0][0]+","+w[1][0]+")");
		System.out.println("b:"+b);
	}
	
}


