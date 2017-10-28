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
	
	// Lots of code repetition. Need to make a new function for Array Copy.
	public void put(Node n, Node from, Node to) {	
		if (from.val != null) {
			Node[] newFrom = new DAG.Node[from.successors.length+1];
			System.arraycopy(from.successors, 0, newFrom, 0, from.successors.length);
			newFrom[newFrom.length-1] = n;
			from.successors = newFrom;
		}
		
		if (to.val != null) {
			Node[] newTo = new DAG.Node[n.successors.length+1];
			System.arraycopy(n.successors, 0, newTo, 0, n.successors.length);
			newTo[newTo.length-1] = to;
			to.successors = newTo;
		}
		
		Node[] newNodeList = new DAG.Node[nodeList.length+1];
		System.arraycopy(nodeList, 0, newNodeList, 0, nodeList.length);
		newNodeList[newNodeList.length-1] = n;
		nodeList = newNodeList;
		
	}
	
	
}
