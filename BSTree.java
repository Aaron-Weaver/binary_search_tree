/**
**********************************************************************
*	CS4343 - Program 3
*	Weaver, Aaron
*
*	BSTree.java - Creates the Binary Search Tree to be filled with
*	BSTNodes. Uses tokenized input from WordCounter.java to fill
*	the array. Can delete nodes and traverse the tree for printed
*	output.
**********************************************************************	
*/

/**
*	@author Aaron Weaver
*	@version 1.0
*	@since 2014-3-28
*/
import java.text.DecimalFormat;

public class BSTree
{
	//The root element of the tree.
	private BSTNode root;
	//Node that tracks the deleted node from the delete function.
	private BSTNode deletedNode;

	/**
	*	Constructors for BSTree.
	*/
	public BSTree()
	{
		this(null);
	}

	/**
	*	@param node Initializes root node for the tree.
	*/
	public BSTree(BSTNode node)
	{
		this.root = node;
	}

	/**
	*	Further tokenizes the input from input files by capitalizing,
	*	and removing non-alphabetical characters.
	*	@param line Individual tokens from file inputs
	*	@return String Capitalized and trimmed string for easy comparisons
	*/
	private static String toUpperAndTrim(String line)
	{
		String formatted = line.toUpperCase();
		formatted = formatted.replaceAll("[^a-zA-Z]", "");
		formatted = formatted.trim();
		return formatted;
	}

	/**
	*	Recursively gets the total count of all the nodes in the
	*	tree.
	*	@return int Total count of all nodes.
	*/
	public int count()
	{
		return count(this.root);
	}

	/**
	*	Recursive call for count() method
	*	@param node Individual node to getCount() from
	*	@return int The total count of all nodes
	*/
	public int count(BSTNode node)
	{
		if(node == null)
		{
			return 0;
		}
		return (node.getCount() + count(node.getLeftChild()) 
			+ count(node.getRightChild()));
	}

	/**
	*	Recursively inserts a new BSTNode into the tree based on
	*	String parameter.
	*	@param toInsert The token parameter to initialize BSTNode
	*	@return void
	*/
	public void insert(String toInsert)
	{
		toInsert = toUpperAndTrim(toInsert);
		this.root = insert(toInsert, this.root);
	}

	/**
	*	Recursive call for insert(String) method
	*	@param toInsert Parameter passed from insert(String)
	*	@param newRoot The reference to the root node that will
	*					assist in adding the new node
	*	@return BSTNode The new node added to the tree
	*/
	private BSTNode insert(String toInsert, BSTNode newRoot)
	{
		if(newRoot == null)
		{
			BSTNode ret = new BSTNode(toInsert);
			return ret;
		}

		//Making sure that tree is kept in proper Lexicographical order
		int comparison = toInsert.compareTo(newRoot.getToken());
		if(comparison <= -1)
		{
			newRoot.setLeftChild(
				insert(toInsert, newRoot.getLeftChild()));
		}
		else if(comparison == 0)
		{
			newRoot.setCount(newRoot.getCount() + 1);

		}
		else
		{
			newRoot.setRightChild( 
				insert(toInsert, newRoot.getRightChild()));
		}

		return newRoot;
	}

	/**
	*	Recursively deletes a BSTNode from the tree found using the 
	*	String parameter.
	*	@param toDelete The token parameter to delete BSTNode from
	*					the tree
	*	@return BSTNode The deleted node.
	*/
	public BSTNode delete(String toDelete)
	{
		toDelete = toUpperAndTrim(toDelete);
		root = delete(root, toDelete);
		return deletedNode;
	}

	/**
	*	Recursive call for delete(String) method
	*	@param node The reference to the node that will assist
	*				in deleting the node containing the specified
	*				token.
	*	@param token Parameter passed from insert(String)
	*	@return BSTNode The new node added to the tree
	*/
	private BSTNode delete(BSTNode node, String token)
	{
		//Keeps track of deleted node
		if(node.getToken().equals(token))
		{
			deletedNode = node;
		}
		if(node == null)
		{
			return null;
		}

		//Checking to see if the token is equal to the current node's token
		int comparison = token.compareTo(node.getToken());

		if(comparison == 0)
		{
			if(node.getLeftChild() == null && node.getRightChild() == null)
			{
				return null;
			}
			else if(node.getLeftChild() == null)
			{
				return node.getRightChild();
			}
			else if(node.getRightChild() == null)
			{
				return node.getLeftChild();
			}

			BSTNode temp = smallestNode(node);
			node.setToken(temp.getToken());
			node.setCount(temp.getCount());

			node.setRightChild(delete(node.getRightChild(), temp.getToken()));
			return node;
		}
		else if(comparison < 0)
		{
			//Recurse on root's left child if token is less than current node
			node.setLeftChild(delete(node.getLeftChild(), token));
			return node;
		}
		else
		{
			//Recurse on root's right child if token is greater than current node
			node.setRightChild(delete(node.getRightChild(), token));
			return node;
		}
	}

	/**
	*	Finds the smallest node reachable from the parametrized
	*	node's current position.
	*	@param node The node that will be deleted, and has at least two subtrees.
	*	@return BSTNode The smallest node reachable from node's position
	*/
	private BSTNode smallestNode(BSTNode node)
	{
		if(node.getRightChild() != null)
		{
			node = node.getRightChild();
		}
		while(node.getLeftChild() != null)
		{
			node = node.getLeftChild();
		}
		return node;
	}

	/**
	*	Formatting header for printed output.
	*	@return void
	*/
	public void printHeader()
	{
		System.out.println("COUNT  PERCENT  TOKEN");
		System.out.println("-----  -------  --------------------");
	}

	/**
	*	Uses inOrderTraversal of the BSTree to print out its contents.
	*	@return void
	*/
	public void print()
	{
		printHeader();
		inOrderTraversal(this.root);
		System.out.println("\n");
	}

	/**
	*	Recursively performs an In Order Traversal of the BSTree.
	*	Prints output for each node as it goes.
	*	@param root Refernce to the root of the tree
	*	@return void
	*/
	private void inOrderTraversal(BSTNode root)
	{
		if(root.getLeftChild() != null)
		{
			//Recursive call
			inOrderTraversal(root.getLeftChild());
		}
		//Formatted output for inOrderTraversal
		System.out.println(formatCount(root) 
  			+ "  " + formatPercent(root) + "  " 
  			+ root.getToken()); 
		if(root.getRightChild() != null)
		{
			//Recursive call
			inOrderTraversal(root.getRightChild());
		}
	}

	/**
	*	Uses postOrderTraversal of the BSTree to print out its contents.
	*	@return void
	*/
	public void printPostorder()
	{
		printHeader();
		postOrderTraversal(this.root);
		System.out.println("\n");
	}

	/**
	*	Recursively performs a Post Order Traversal of the BSTree.
	*	Prints output for each node as it goes.
	*	@param root Refernce to the root of the tree
	*	@return void
	*/
	private void postOrderTraversal(BSTNode root)
	{
		if(root.getLeftChild() != null)
		{
			postOrderTraversal(root.getLeftChild());
		}
		if(root.getRightChild() != null)
  		{
  			postOrderTraversal(root.getRightChild());
  		}
  		//Prints formatted output for Post Order Traversal
  		System.out.println(formatCount(root) 
  			+ "  " + formatPercent(root) + "  " 
  			+ root.getToken()); 
	}

	/**
	*	Recursively copies the tree using a Pre Order Traversal 
	*	and returns the root node to the copied tree.
	*	@return BSTNode Reference to the root of the copied tree
	*/
	public BSTNode copy()
	{
		if(root == null)
		{
			return null;
		}
		return preOrderTraversal(this.root);
	}

	/**
	*	Recursive method for the copy, Traverses the BSTree
	*	copying all the nodes from the local tree to a new tree.
	*	returns a reference to the root node of the new tree.
	*	@param BSTNode A reference to the root of the local tree
	*	@return BSTNode A reference to the root of the copied tree
	*/
	public BSTNode preOrderTraversal(BSTNode root)
	{
		if(root == null)
		{
			return null;
		}
		
		BSTNode node = new BSTNode(root.getToken());
		node.setCount(root.getCount());
		node.setLeftChild(root.getLeftChild());
		node.setRightChild(root.getRightChild());
		return node;
	}

	/**
	*	Formats the total count of each node for proper output.
	*	@param node Reference to node to get count from
	*	@return String Formatted string for the final output
	*/
	private String formatCount(BSTNode node)
	{
		String countFormatted = String.format("%5s", "" + node.getCount());
		return countFormatted;
	}

	/**
	*	Formats the output of percentage each word has within the
	*	BSTree.
	*	@param node Reference to node to get count from
	*	@return String Formatted string for the final output
	*/
	private String formatPercent(BSTNode node)
	{
		double percent = ((0.0 + node.getCount())/(this.count()) * 100);
		//System.out.println("percent: " + percent);
		//System.out.println("count " + node.getCount());
		//System.out.println("this.count " + this.count());
		DecimalFormat dec = new DecimalFormat("0.00");
		String percentFormatted = String.format("%6s", 
			(dec.format(percent)) + "%");

		return percentFormatted;
	}
}