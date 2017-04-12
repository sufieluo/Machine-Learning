import java.util.ArrayList;
import java.util.Stack;

public class KNN {
	public kdTree tree = new kdTree();
	public int input[][] = new int[0][1];
	public int nearestIndex = -1;
	public double bestDistance = 100000;
	//public int k = 3;//k近邻
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
		System.out.println("最近邻：" + nearestIndex + " 距离：" + bestDistance);
	}
	
	//回溯找最近邻
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
			//判断是否与矩形区域有相交
			else  if (Math.abs(input[0][tempNode.getPartitionDimention()] 
			                            - kdTree.init_data.get(tempNode.getIndex()).get(tempNode.getPartitionDimention()))
			                            < bestDistance) {
				double tempDistance = getDistance(tempNode);
				if (tempDistance < bestDistance) {
					nearestIndex = tempNode.getIndex();
					bestDistance = tempDistance;
				}
				if (input[0][tempNode.getPartitionDimention()] < kdTree.init_data.get(tempNode.getIndex()).get(tempNode.getPartitionDimention())) {
					//如果进入左节点，那就把右节点压栈
					if (tempNode.getRightNode() != null) {
						sNodes.push(tempNode.getRightNode());
					}
					
				}
				else {
					//如果进入右节点，那就把左节点压栈
					if (tempNode.getLeftNode() != null) {
						sNodes.push(tempNode.getLeftNode());
					}
					
				}
				
			}
				
		}
			
	}
	//（蠢办法，没利用到kd树的特性）前序遍历kd树，边遍历边计算距离，求出最近邻
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

	//找初始的最近邻
	public void findCurrentNearest(Node node) {
		if (node.isLeaf()) {
			nearestIndex =  node.getIndex();
			bestDistance = getDistance(node);
			System.out.println("初始最近的点："+nearestIndex);
			return;
		}
		//分割的维度
		int partitionDimention = node.getPartitionDimention();
		sNodes.push(node);
		if (input[0][partitionDimention] < kdTree.init_data.get(node.getIndex()).get(partitionDimention)) {
			if (node.getLeftNode() != null) {
				node = node.getLeftNode();
			}
			else {
				nearestIndex =  node.getIndex();
				bestDistance = getDistance(node);
				System.out.println("初始最近的点："+nearestIndex);
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
				System.out.println("初始最近的点："+nearestIndex);
				return;
			}
			
		}
		
		findCurrentNearest(node);
	}

	//计算输入点与第index的点之间的欧氏距离
	public double getDistance(Node node) {        
		double d = Math.pow(Math.abs(input[0][0]-kdTree.init_data.get(node.getIndex()).get(0)),2)+
						  Math.pow(Math.abs(input[0][1]-kdTree.init_data.get(node.getIndex()).get(1)), 2);
		
		return  Math.sqrt(d);
	}
	
	
}
