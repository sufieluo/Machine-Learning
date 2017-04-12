import java.util.ArrayList;
import java.util.Stack;

public class KNN {
	public kdTree tree = new kdTree();
	public int input[][] = new int[0][1];
	public int nearestIndex = -1;
	public double bestDistance = 100000;
	//public int k = 3;//k����
	public Stack<Node> sNodes = new Stack<Node>();//
	
	public  KNN() {
		
	}
	public kdTree getTree() {
		return tree;
	}

	public void setTree(kdTree tree) {
		this.tree = tree;
	}
 
	public int[][] getInput() {
		return input;
	}

	public void setInput(int[][] input) {
		this.input = input;                               
	}
	
	
	public  void develop_server(ArrayList<ArrayList<Integer>> dataArrayList,int input[][]) {
		setTree(kdTree.bulidKdTree(dataArrayList));
		setInput(input);
		findCurrentNearest(tree.getRoot()); 
		getNearest();
		System.out.println("����ڣ�" + nearestIndex + " ���룺" + bestDistance);
	}
	
	//�����������
	private void getNearest() {
		
		while(!sNodes.isEmpty()) {
			Node tempNode = sNodes.pop();
			if (tempNode.isLeaf()) {
				double tempDistance = getDistance(tempNode);
				if (tempDistance < bestDistance) {
					nearestIndex = tempNode.getIndex();
					bestDistance = tempDistance;
				}
				
			}
			//�ж��Ƿ�������������ཻ
			else  if (Math.abs(input[0][tempNode.getPartitionDimention()] 
			                            - kdTree.init_data.get(tempNode.getIndex()).get(tempNode.getPartitionDimention()))
			                            < bestDistance) {
				double tempDistance = getDistance(tempNode);
				if (tempDistance < bestDistance) {
					nearestIndex = tempNode.getIndex();
					bestDistance = tempDistance;
				}
				if (input[0][tempNode.getPartitionDimention()] < kdTree.init_data.get(tempNode.getIndex()).get(tempNode.getPartitionDimention())) {
					//���������ڵ㣬�ǾͰ��ҽڵ�ѹջ
					if (tempNode.getRightNode() != null) {
						sNodes.push(tempNode.getRightNode());
					}
					
				}
				else {
					//��������ҽڵ㣬�ǾͰ���ڵ�ѹջ
					if (tempNode.getLeftNode() != null) {
						sNodes.push(tempNode.getLeftNode());
					}
					
				}
				
			}
				
		}
			
	}
	//�����취��û���õ�kd�������ԣ�ǰ�����kd�����߱����߼�����룬��������
//	private void getNearest(Node node) {
//		if(node != null){
//			if (getDistance(node) < bestDistance) {
//				bestDistance = getDistance(node);
//				nearestIndex = node.getIndex();
//			}
//			
//			getNearest(node.getLeftNode());
//			getNearest(node.getRightNode());
//		}
//		return;
//	}

	//�ҳ�ʼ�������
	public void findCurrentNearest(Node node) {
		if (node.isLeaf()) {
			nearestIndex =  node.getIndex();
			bestDistance = getDistance(node);
			System.out.println("��ʼ����ĵ㣺"+nearestIndex);
			return;
		}
		//�ָ��ά��
		int partitionDimention = node.getPartitionDimention();
		sNodes.push(node);
		if (input[0][partitionDimention] < kdTree.init_data.get(node.getIndex()).get(partitionDimention)) {
			if (node.getLeftNode() != null) {
				node = node.getLeftNode();
			}
			else {
				nearestIndex =  node.getIndex();
				bestDistance = getDistance(node);
				System.out.println("��ʼ����ĵ㣺"+nearestIndex);
				return;
			}
		}
		else {
			if (node.getRightNode() != null) {
				node = node.getRightNode();
			}
			else {
				nearestIndex =  node.getIndex();
				bestDistance = getDistance(node);
				System.out.println("��ʼ����ĵ㣺"+nearestIndex);
				return;
			}
			
		}
		
		findCurrentNearest(node);
	}

	//������������index�ĵ�֮���ŷ�Ͼ���
	public double getDistance(Node node) {        
		double d = Math.pow(Math.abs(input[0][0]-kdTree.init_data.get(node.getIndex()).get(0)),2)+
						  Math.pow(Math.abs(input[0][1]-kdTree.init_data.get(node.getIndex()).get(1)), 2);
		
		return  Math.sqrt(d);
	}
	
	
}
