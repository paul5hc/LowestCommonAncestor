// isEmpty()
// size()
// contains()
// put()
// extendArrayByOne()

import java.util.Arrays;

public class DAG <Value> {
	private class Node {
		private Value val;					// Value stored in Node
		private Node[] successors;			// Array of Nodes' successors
	
		public Node(Value val) {
			this.val = val;
		}
	}
	private Node[] nodeList = new DAG.Node[0];					// Keeps track of the nodes in the list
	
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
	public boolean contains(Value v) {
		boolean valInList = false;
		for (int i=0; i<nodeList.length; i++) {
			if (nodeList[i].val == v) {
				valInList = true;
				break;
			}			
		}
		return valInList;
	}
	
	// Adds a node to the graph. No precaution for cycles yet.
	public void put(Value v, Value fromVal, Value toVal) {	
		Node n = new Node(v);
		Node from = retrieveNodefromVal(fromVal);
		Node to = retrieveNodefromVal(toVal);
		
		if (fromVal != null) {
			from.successors = extendArrayByOne(from.successors);
			from.successors[from.successors.length-1] = n;
			// If 'from' Node is not already in the graph.
			if (from.val == null) {
				addNodeToNodeList(from);
			}
		}
		
		if (toVal != null) {
			n.successors = extendArrayByOne(n.successors);
			n.successors[n.successors.length-1] = to;
			// If 'to' Node is not already in the graph.
			if (to.val == null) {
				addNodeToNodeList(to);
			}
		}
		addNodeToNodeList(n);		
	}
	
	public void addNodeToNodeList (Node n) {
		nodeList = extendArrayByOne(nodeList);
		nodeList[nodeList.length-1] = n;
	}
	
	// Extends an array by one element.
	public Node[] extendArrayByOne(Node[] originalArray) {
		Node[] copyArray = new DAG.Node[originalArray.length+1];
		System.arraycopy(originalArray, 0, copyArray, 0, originalArray.length);
		return copyArray;
	}
	
	// Unsure as to whether or not a Node's children are deleted as well?
	// Assuming that they are not for the time being.
	// Elements need to be deleted rather than turned to null.
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
	
	// Returns Node associated with Value v.
	public Node retrieveNodefromVal (Value v){
		Node returnNode = new Node(null); 				
		for (int i=0; i<nodeList.length; i++){
			if (nodeList[i].val == v){
				returnNode = nodeList[i];
				break;			
			}	
		}
		return returnNode;
	}
}
