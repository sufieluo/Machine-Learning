public class Perceptron {
	//Ȩ��
	public static double [][] w = new double [2][1];
	//ƫ��
	public static double b = 0;
	//ѧϰ��
	public static double rate = 1;
	//���ݼ��������д�������
	public static double [][] data = Input.getLabel_one();
	//���ݼ���С
	public static int data_size = Input.getLabelOneSize();
	//��������
	public static int diedainum = 0;
	
	public static void output() {
		System.out.println("\n"+"����������"+ diedainum);
		System.out.println("w:("+w[0][0]+","+w[1][0]+")");
		System.out.println("b:"+b);
	}
		
	public static void jisuan() {
		
		diedainum++;
		
		for (int i = 0; i < data_size; i++) {
			double temp = (w[0][0] * data[0][i] +w[1][0] * data[1][i] + b) * data[2][i];
			if (temp > 0.0) {
				continue;
			}
			else {
				updateWeight(i);
				System.out.println("��ֵ㣺x"+(i+1));
				jisuan();
				
			}
		}
		
	}

	private static void updateWeight(int index) {
		w[0][0] = w[0][0] + rate * data[2][index] *data[0][index];
		w[1][0] = w[1][0] + rate * data[2][index] *data[1][index];
		b = b + data[2][index];
		System.out.println("����������"+ diedainum);
		System.out.println("w:("+w[0][0]+","+w[1][0]+")");
		System.out.println("b:"+b);
	}
	
}


