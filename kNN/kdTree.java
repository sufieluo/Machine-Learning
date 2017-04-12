import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class kdTree {
	public Node root = new Node();//根节点
	public static HashMap<Integer, ArrayList<Integer>> init_data= new HashMap<Integer, ArrayList<Integer>>();
	
	static int dimention = 2; 	//维度

	public kdTree(){
		
	}
	
	
	public Node getRoot() {
		return root;
	}


	public void setRoot(Node root) {
		this.root = root;
	}


	public static kdTree bulidKdTree(ArrayList<ArrayList<Integer>> data) {
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				init_data.put(i, data.get(i));
			}
			
		}
		if (data.isEmpty()) {
			return null;
		}
	
		kdTree tree = new kdTree();
	
		createTree(tree.root,data,-1);
		printTree(tree.root);
		
		return tree;
	}
	
	public static void createTree(Node node, ArrayList<ArrayList<Integer>> data,int parentIndex) {
		if (data.size() == 0) {
			return;
		}
		
		//找到方差最大的维度进行分割
		node.setPartitionDimention(-1);
        double var = -1;
        double tmpvar;
        for(int i=0;i<dimention;i++){
          	tmpvar=Utilz.variance(data, i);
            if (tmpvar>var){
                var = tmpvar;
                node.setPartitionDimention(i);
            }
        }
           	
        //只剩下一个数据，说明到了叶子节点
    	if (data.size() == 1 ) {
			node.setLeaf(true);
			for (Entry<Integer, ArrayList<Integer>> e : init_data.entrySet()) {
				if (e.getValue().equals(data.get(0))) {
					node.setIndex(e.getKey());
					node.setParentIndex(parentIndex);
					System.out.println("初始位置："+node.getIndex()+"纬度："+node.getPartitionDimention());
					break;
				}
			}
			return;
		}
    	
    	
    	//找到aixs维度的中位数所在data中的下标
		int index = findMid(data, node.getPartitionDimention());
		
		for (Entry<Integer, ArrayList<Integer>> e : init_data.entrySet()) {
			if (e.getValue().equals(data.get(index))) {
				node.setIndex(e.getKey());
				node.setParentIndex(parentIndex);
				System.out.println("初始位置："+node.getIndex()+"纬度："+node.getPartitionDimention());
				break;
			}
		}
		
		ArrayList<Integer> leftIndex = new ArrayList<Integer>();
		ArrayList<Integer> rightIndex = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).get(node.getPartitionDimention()) < data.get(index).get(node.getPartitionDimention()))  {
				node.setLeftNode(new Node());
				leftIndex.add(i);
			}	
			else if(data.get(i).get(node.getPartitionDimention()) >= data.get(index).get(node.getPartitionDimention()) && i != index){
				node.setRightNode(new Node());
				rightIndex.add(i);
			}		
		}
		//找出左子集
		
		ArrayList<ArrayList<Integer>> left_data = findLeftData(data,leftIndex);
		//找出右子集
		ArrayList<ArrayList<Integer>> right_data = findRightData(data,rightIndex);
	
		//++depth加了之后再传
		createTree(node.getLeftNode(),left_data,node.getIndex());
		createTree(node.getRightNode(),right_data,node.getIndex());
		
	}
	
	public static ArrayList<ArrayList<Integer>> findLeftData(ArrayList<ArrayList<Integer>> data,ArrayList<Integer> leftIndex) {
		ArrayList<ArrayList<Integer>> left_data = new ArrayList<ArrayList<Integer>>();
		//找出左子集
		for (int i = 0; i < leftIndex.size(); i++) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int j = 0; j < data.get(i).size(); j++) {
				 arrayList.add(data.get(leftIndex.get(i)).get(j));
			}
			left_data.add(arrayList);
		}
		return left_data;
	}
	
	public static ArrayList<ArrayList<Integer>> findRightData(ArrayList<ArrayList<Integer>> data,ArrayList<Integer> rightIndex) {
		ArrayList<ArrayList<Integer>> right_data = new ArrayList<ArrayList<Integer>>();
		//找出右子集
		for (int i = 0; i < rightIndex.size(); i++) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int j = 0; j < data.get(i).size(); j++) {
				 arrayList.add(data.get(rightIndex.get(i)).get(j));
			}
			right_data.add(arrayList);
			
		}
		return right_data;
	}

	//找到最中间的数据,返回数据下标
	public static int findMid(ArrayList<ArrayList<Integer>> data,int aixs) {
		int data_of_aixs[] = new int[data.size()]; 
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < data_of_aixs.length; i++) {
			data_of_aixs[i] = data.get(i).get(aixs);
			map.put(i,data_of_aixs[i]);
		}
		Arrays.sort(data_of_aixs);
		//排序后算出中位数
		int mid_data_of_aixs = data_of_aixs[data_of_aixs.length/2];
		//返回中位数对应的data中的下标
		int index = 0;
		for (Entry<Integer, Integer> e : map.entrySet()) {
			if (e.getValue().equals(mid_data_of_aixs)) {
				index = e.getKey();
				break;
			}
		}
		System.out.println("根："+mid_data_of_aixs+"维度："+aixs);
		
		return index;
	}
	
	public static void printTree(Node node) {
		if (node != null) {
			if (node.isLeaf()) {
				System.out.println(node.getIndex());
				return;
			}
			
			System.out.println(node.getIndex());
			printTree(node.getLeftNode());
			printTree(node.getRightNode());
		}		
		return;
		
	}
//	
//	public Node findRightNode() {
//		
//	}
//	
//	public Node findParentNode() {
//		
//	}
}
