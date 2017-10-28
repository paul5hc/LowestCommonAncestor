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
	public int size() { return nodeList.length; }
	
	// Checks whether Node n is in the graph.
	public boolean contains(Node n) {
		if (Arrays.asList(nodeList).contains(n) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
