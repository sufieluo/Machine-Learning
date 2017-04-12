import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class kdTree {
	public Node root = new Node();//���ڵ�
	public static HashMap<Integer, ArrayList<Integer>> init_data= new HashMap<Integer, ArrayList<Integer>>();
	
	static int dimention = 2; 	//ά��

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
		
		//�ҵ���������ά�Ƚ��зָ�
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
           	
        //ֻʣ��һ�����ݣ�˵������Ҷ�ӽڵ�
    	if (data.size() == 1 ) {
			node.setLeaf(true);
			for (Entry<Integer, ArrayList<Integer>> e : init_data.entrySet()) {
				if (e.getValue().equals(data.get(0))) {
					node.setIndex(e.getKey());
					node.setParentIndex(parentIndex);
					System.out.println("��ʼλ�ã�"+node.getIndex()+"γ�ȣ�"+node.getPartitionDimention());
					break;
				}
			}
			return;
		}
    	
    	
    	//�ҵ�aixsά�ȵ���λ������data�е��±�
		int index = findMid(data, node.getPartitionDimention());
		
		for (Entry<Integer, ArrayList<Integer>> e : init_data.entrySet()) {
			if (e.getValue().equals(data.get(index))) {
				node.setIndex(e.getKey());
				node.setParentIndex(parentIndex);
				System.out.println("��ʼλ�ã�"+node.getIndex()+"γ�ȣ�"+node.getPartitionDimention());
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
		//�ҳ����Ӽ�
		
		ArrayList<ArrayList<Integer>> left_data = findLeftData(data,leftIndex);
		//�ҳ����Ӽ�
		ArrayList<ArrayList<Integer>> right_data = findRightData(data,rightIndex);
	
		//++depth����֮���ٴ�
		createTree(node.getLeftNode(),left_data,node.getIndex());
		createTree(node.getRightNode(),right_data,node.getIndex());
		
	}
	
	public static ArrayList<ArrayList<Integer>> findLeftData(ArrayList<ArrayList<Integer>> data,ArrayList<Integer> leftIndex) {
		ArrayList<ArrayList<Integer>> left_data = new ArrayList<ArrayList<Integer>>();
		//�ҳ����Ӽ�
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
		//�ҳ����Ӽ�
		for (int i = 0; i < rightIndex.size(); i++) {
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int j = 0; j < data.get(i).size(); j++) {
				 arrayList.add(data.get(rightIndex.get(i)).get(j));
			}
			right_data.add(arrayList);
			
		}
		return right_data;
	}

	//�ҵ����м������,���������±�
	public static int findMid(ArrayList<ArrayList<Integer>> data,int aixs) {
		int data_of_aixs[] = new int[data.size()]; 
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < data_of_aixs.length; i++) {
			data_of_aixs[i] = data.get(i).get(aixs);
			map.put(i,data_of_aixs[i]);
		}
		Arrays.sort(data_of_aixs);
		//����������λ��
		int mid_data_of_aixs = data_of_aixs[data_of_aixs.length/2];
		//������λ����Ӧ��data�е��±�
		int index = 0;
		for (Entry<Integer, Integer> e : map.entrySet()) {
			if (e.getValue().equals(mid_data_of_aixs)) {
				index = e.getKey();
				break;
			}
		}
		System.out.println("����"+mid_data_of_aixs+"ά�ȣ�"+aixs);
		
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
