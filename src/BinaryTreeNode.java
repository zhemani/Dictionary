//Node class to use with ordered dictionary
public class BinaryTreeNode {
//Declare private variables to use for nodes
	private DictEntry root;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	//Constructor to initialize the root
	public BinaryTreeNode (DictEntry root) {
		this.root = root;
	}
	//Getter method for the root
	public DictEntry getDictEntry () {
		return root;
	}
	//Getter method for the left reference
	public BinaryTreeNode getLeft () {
		if (left == null) {
			left = new BinaryTreeNode(null);
		}
		return left;
	}
	//Getter method for the right reference
	public BinaryTreeNode getRight () {
		if (right == null) {
			right = new BinaryTreeNode(null);
		}
		return right;
	}
	//Setter method for the root
	public void setRoot (DictEntry root) {
		this.root = root;
	}
	//Setter method for the left reference
	public void setLeft (DictEntry left) {
		this.left = new BinaryTreeNode(left);
	}
	//Setter method for the right reference
	public void setRight (DictEntry right) {
		this.right = new BinaryTreeNode(right);
	}
	//Null the left reference
	public void noLeft () {
		left = null;
	}
	//Null the right reference
	public void noRight () {
		right = null;
	}
}//End of class
