/**
**********************************************************************
*	CS4343 - Program 3
*	Weaver, Aaron
*
*	BSTNode.java - Creates single nodes for the Binary Search Tree
*	(BSTree) and provides accessor, mutator, and compareTo functions.
**********************************************************************	
*/


/**
*	@author Aaron Weaver
*	@version 1.0
*	@since 2014-3-28
*/
public class BSTNode implements Comparable<BSTNode>
{
	private String token;
	//Number of times the specific token has been added to the tree
	private int count;
	//A reference to the left node relative to the parent node
	private BSTNode leftChild;
	//A reference to the right node relative to the parent node
	private BSTNode rightChild;

	public BSTNode(String token) 
	{
		this.token = token;
		this.count = 1;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	*	Simple comparable function, works similarly to Java's method.
	*	Compares the parametric node's token to this.token and returns 
	*	appropriate values. If equal, it then compares the count of these
	* 	two values.
	*	@param input A BSTNode that is compared to the local node.
	*	@return int Value based on whether the input is greater than,
	*			less than, or equal to the local object.
	*/
	@Override
	public int compareTo(BSTNode input)
	{
		if(input.getToken().equals(this.token))
		{
			if(input.getCount() == this.count)
			{
				return 0;
			}
			else if(this.count > input.getCount())
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
		else if(this.token.compareTo(input.getToken()) > 0)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}

	/**
	*	Accessors and Mutators for BSTNode
	*/
	public String getToken()
	{
		return this.token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public int getCount()
	{
		return this.count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public BSTNode getLeftChild()
	{
		return this.leftChild;
	}

	public void setLeftChild(BSTNode leftChild)
	{
		this.leftChild = leftChild;
	}

	public BSTNode getRightChild()
	{
		return this.rightChild;
	}

	public void setRightChild(BSTNode rightChild)
	{
		this.rightChild = rightChild;
	}
}