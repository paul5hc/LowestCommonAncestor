// isEmpty()
// size()
// contains()
// put()
// extendArrayByOne()

import java.util.Arrays;

public class DAG <Value> {
	
	public Node[] nodeList;					// Keeps track of the nodes in the list

	private class Node {
		private Value val;					// Value stored in Node
		private Node[] successors;			// Array of Nodes' successors
		private int succNumber; 			// Number of successors 
		
		public Node(Value val, int succNumber) {
			this.val = val;
			this.succNumber = succNumber;
		}
	}
	
	// Is the DAG empty?
	public boolean isEmpty() { 
		if (size()==0) {
			return true;
		}
		else {
			return false;
		}
	}

	// Returns number of Nodes in graph.
	public int size() { 
		return nodeList.length; 
	}
	
	// Checks whether Node n is in the graph.
	public boolean contains(Node n) {
		if (Arrays.asList(nodeList).contains(n) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Adds a node to the graph. No precaution for cycles yet.
	public void put(Node n, Node from, Node to) {	
		if (from.val != null) {
			from.successors = extendArrayByOne(from.successors);
			from.successors[from.successors.length-1] = n;
		}
		
		if (to.val != null) {
			n.successors = extendArrayByOne(n.successors);
			n.successors[n.successors.length-1] = to;
		}
		
		nodeList = extendArrayByOne(nodeList);
		nodeList[nodeList.length-1] = n;		
	}
	
	// Extends an array by one element.
	public Node[] extendArrayByOne(Node[] originalArray) {
		Node[] arrayCopy = new DAG.Node[originalArray.length+1];
		System.arraycopy(originalArray, 0, arrayCopy, 0, originalArray.length);
		return arrayCopy;
	}
	
	// Unsure as to whether or not a Node's children are deleted as well?
	// Assuming that they are not for the time being.
	public void deleteNode(Node n) {
		int i;
		for(i=0; i<n.successors.length; i++) {
			n.successors[i] = null;
		}
		
		for(i=0; i<nodeList.length; i++) {
			if (Arrays.asList(nodeList[i].successors).contains(n) == true) {
				for (int j=0; j<nodeList[i].successors.length; j++) {
					if (nodeList[i].successors[j] == n) {
						nodeList[i].successors[j] = null;
					}
				}
			}
		}
		
		for (int k=0; k<nodeList.length; k++) {
			if (nodeList[k] == n) {
				nodeList[k] = null;
			}
		}		
	}
}
