

public  class  Node{
	
	private int index = -1;
	private Node leftNode = null;//左孩子
	private Node rightNode = null;//右孩子
	private int partitionDimention = 0;//分割的维度
	private boolean isLeaf = false;//是否为叶子节点
	private int parentIndex = -1;

	
	public Node () {
		
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Node getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	public Node getRightNode() {
		return rightNode;
	}
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	public int getPartitionDimention() {
		return partitionDimention;
	}
	public void setPartitionDimention(int partitionDimention) {
		this.partitionDimention = partitionDimention;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public int getParentIndex() {
		return parentIndex;
	}
	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}	
	
}
