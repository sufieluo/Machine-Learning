
public class DualPerceptron {
	//Ȩ��
	public static double[] a = new double [3];
	//ƫ��
	public static double b = 0;
	//ѧϰ��
	public static double rate = 1;
	//���ݼ��������д�������
	public static double [][] label_one = Input.getLabel_one();
	//���ݼ���С
	public static int labelSize = Input.getLabelOneSize();
	//��������
	public static int diedainum = 0;
	//gram����
	public static double g[][] = new double[labelSize][labelSize];
	
	public static void output() {
		System.out.println("����������"+ diedainum);
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
			//�������
			double temp = label_one[2][i] * (b + sum);
			
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
		a[index] = a[index] + rate;
		b = b + label_one[2][index];
		System.out.println("\n"+"����������"+ diedainum);
		System.out.println("a:("+a[0]+","+a[1]+","+a[2]+")");
		System.out.println("b:"+b);
	}
}
