
public class DAG <Value> {

	private class Node {
		private Value val;					// Value stored in Node
		private Node[] successors;			// Array of Nodes' successors
		private int succNumber; 			// Number of successors 
		
		public Node(Value val, int succNumber) {
			this.val = val;
			this.succNumber = succNumber;
		}
	}
	
	// public boolean isEmpty()
	// public boolean size()
	// public boolean contains()
	
}
