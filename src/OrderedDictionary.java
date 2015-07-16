//Ordered Dictionary Class to perform BinaryTree Meethods
public class OrderedDictionary implements OrderedDictionaryADT {
//Declaring a private root variable
	private BinaryTreeNode root;
	//Constructor to initialize the root
	public OrderedDictionary() {
		root = new BinaryTreeNode(null);
	}
	//Private findRoot method to return the correct r, in relation to that word
	private BinaryTreeNode findRoot (String word)
	{
		BinaryTreeNode r = root;
		int check; //int check variable to assign to compareTo
		while (r.getDictEntry()!=null) {
			check = word.compareTo(r.getDictEntry().word()); //CompareTo method which outputs an int
			if (check == 0) { //== to 0 means that lexographically the same
				return r;
			}
			else if (check > 0) { //Get left node if compareTo returns greater than 0
				r = r.getLeft();
			}
			else if (check < 0) { //Get right node if compreTo returns less than 0
				r = r.getRight();
			}
		}
		return r; //Return the root node
	}
	//Method to return the definition of a certain word
	public String findWord(String word) {
		
		BinaryTreeNode currentRoot = findRoot(word); //Run private findRoot method
		if (currentRoot.getDictEntry() != null) { //Ensure the word is in the dictionary
			return currentRoot.getDictEntry().definition();
		}
		else { //If the word is not in the dictionary
			return "";	
		}	
	}
//Method to return an int of the type; either 1,2,or 3
	public int findType(String word) {
		BinaryTreeNode currentRoot = findRoot(word); //Private findRoot method 
		if(findWord(word) == "") { //If the word does not exist within the dictionary
			return -1;
		}
		if (currentRoot.getDictEntry() != null) {  //Ensure that the word is in the dictionary
			return currentRoot.getDictEntry().type(); //Return int type
		}
		return -1;
	}
//Method to insert into a binary tree
	public void insert(String word, String definition, int type) throws DictionaryException {
		DictEntry data = new DictEntry(word, definition, type); //Make a new DictEntry object
		if (findWord(data.word()) != "") { //Ensure that the word does not already exist
			 throw new DictionaryException ("This word already exists");	
		}
		else {
			findRoot(word).setRoot(data); //Insert that dictEntry into the tree with setter method
		}	
	}
//Remove method to remove a node from a binary tree
	public void remove(String word) throws DictionaryException {
		if (findWord(word) == "") { //Ensure that the word does exist, if not then exception
			throw new DictionaryException ("This word does not currently exist");
		}
		else { 
			BinaryTreeNode r = findRoot(word);
			DictEntry data;
			//First case; if the node has no children
			if ((r.getLeft()).getDictEntry() == null && (r.getRight()).getDictEntry() == null) {
				r.setRoot(null); //Make the root null and left and right nodes null
				r.noLeft();
				r.noRight();
				
			}//Second case; if the node has no left child but has a right child
			else if ((r.getLeft()).getDictEntry() == null && (r.getRight()).getDictEntry() != null) {
				data = r.getRight().getDictEntry();
				remove(data.word());//Recursively call remove 
				r.setRoot(data);
			}//Third case; if the node has a left right but no right child
			else if ((r.getLeft()).getDictEntry() != null && (r.getRight()).getDictEntry() == null) {
				data = r.getLeft().getDictEntry();
				remove(data.word()); //Recursively call remove
				r.setRoot(data);
			}
			else {//Fourth case; if the node has two children
				String holder = successor((r.getDictEntry()).word()); //Use a successor method to get the node that will take the place of that being removed 
				data=findRoot(holder).getDictEntry();
				remove(data.word()); //Recursively call remove
				r.setRoot(data); //Get the DictEntry object at that location and set it as the root	
			}
		}	
	}

	//Successor method to return the next greatest word, lexographical order
	public String successor(String word) {
		BinaryTreeNode r = root; //Declare variable needed throughout method
		BinaryTreeNode rParent = new BinaryTreeNode(null);
		DictEntry data = r.getDictEntry();
	    int check;
	    
	    while (data != null) { //Ensure that data is not null, therefore still nodes available to traverse
	    	check = data.word().compareTo(word);
	    	if (check > 0) { //Use compare to which returns int, check if it is greater than 0
	    		rParent = r; //Set parent node to r
	    		r = r.getRight(); //Get right 
	    		if (r.getRight() == null) {
	    			return ""; //Ensure the right node is not = to null, therefore no successor
	    		}
	    	}
	    	else if (check <= 0) { //Check return less than or equal to 0
	    		r = r.getLeft(); //Go to the left
	    	}
	    	data = r.getDictEntry(); //For while loop, get the next DictEntry object
	    } 
	    
	    if (rParent.getDictEntry() == null) { //Ensure the node has  successor
	    	return "";
	    }
	    else {
		return ((rParent.getDictEntry()).word());//Return the successor
	    }
	}
	//Method to return the predecessor; return the next smallest word in lexographical order
	public String predecessor(String word) {
		BinaryTreeNode r = root; //Declare variables to be used throughout the method
		BinaryTreeNode rParent = new BinaryTreeNode(null);
		DictEntry data = r.getDictEntry();
	    int check;
	    
	    while (data != null) { //Same as successor to ensure there are still nodes to traverse
	    	check = data.word().compareTo(word);
	    	if (check < 0) { //Less that 0 returned from compareTo
	    		rParent = r; //Store current r in parent node
	    		r = r.getLeft(); //Go tot to the left
	    		if (r.getLeft() == null) {
	    			return ""; //Ensure getLeft is not null, therefore still has predecessor
	    		    }
	    	}
	    	else if (check >= 0) { //Greater than or equal to zero return 
	    		r = r.getRight(); //Go to the right
	    	}
	    	data = r.getDictEntry();// For while loop; to get the next DictEntry object
	    }
	    if (rParent.getDictEntry() == null) { //Ensure predecessor is available 
	    	return "";
	    }
	    else {
		return ((rParent.getDictEntry()).word()); //Return the predecessor
	    }
	}	
} //End of class
