/**
**********************************************************************	
*	CS4343 - Program 3
*	Weaver, Aaron
*
*	WordCounter.java - Takes in two file names as arguments and 
*	parses text from a file. It tokenizes the input, and performs
*	insert, delete, and copy methods for a Binary Search Tree.
**********************************************************************	
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
*	@author Aaron Weaver
*	@version 1.0
*	@since 2014-3-28
*/
public class WordCounter
{
	/**
	*	main takes two file names as arguments and grabs the tokenized
	*	form of those file's contents as a String array, then perfroms
	*	various Binary Search Tree methods.
	*	@param args Two filenames to be parsed
	*	@return void
	*/
	public static void main(String[] args)
	{
		String[] fileOne = parseFile(args[0]);
		String[] fileTwo = parseFile(args[1]);
		BSTree tree1 = new BSTree();
		BSTree tree2 = new BSTree();
		for(int i = 0; i < fileOne.length; i++)
		{
			if(fileOne[i] != "")
			{
				tree1.insert(fileOne[i]);
			}
		}
		tree2 = new BSTree(tree1.copy());

		//delete from first tree, make sure to catch null pointer exception.
		for(int i = 0; i < fileTwo.length; i++)
		{
			try
			{
				if(fileTwo[i] != "")
				{
					tree1.delete(fileTwo[i]);
				}
			}
			catch(NullPointerException e)
			{
				continue;
			}
		}
		//Print out contents of BSTrees
		System.out.println("\n\nBST 1\n");
		tree1.print();
		System.out.println("BST 2\n");
		tree2.print();
		System.out.println("BST 1 (POSTORDER)\n");
		tree1.printPostorder();
	}

	/**
	*	Reads in files specified as command line arguments, parses them,
	*	then tokenizes them. Makes sure to catch exception where file cannot
	*	be opened.
	*	@param fileName The name (or path) to the file to be opened
	*	@return String[] The tokenized form of the files contents
	*/
	public static String[] parseFile(String fileName)
	{
		String contents = "";
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = reader.readLine()) != null)
			{
				contents += line;
			}
		} 
		catch(IOException e)
		{
			System.out.println("The file could not be read, terminating program.");
			System.exit(1);
		}
		//seperates the file contents by spaces.
		return contents.split("\\s+");
	}
}