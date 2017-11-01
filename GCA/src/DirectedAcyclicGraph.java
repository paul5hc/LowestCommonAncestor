// Currently implements acyclic graph. Need to update tests.
// Need to know the number of vertices before implementation.
// Only takes integer values (greater than 0) for edges.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectedAcyclicGraph {

	public static ArrayList<Edge>[] adjList;
	public int verticesNumber;

	public DirectedAcyclicGraph(int verticesNumber) {
		adjList = (ArrayList<Edge>[]) new ArrayList[verticesNumber + 1];
		for (int i = 1; i <= verticesNumber; i++) {
			adjList[i] = new ArrayList<Edge>();
		}
		this.verticesNumber = verticesNumber;
	}
	
	// Checks if graph is empty.
	public boolean isEmpty() {
		if (verticesNumber == 0) {
			return true;
		}
		return false;
	}

	// Adds an edge between two vertices in the graph.
	public void addEdge(int v1, int v2) {
		if (v1 <= verticesNumber && v2 <= verticesNumber) {
			if (adjList[v1] == null)
				adjList[v1] = new ArrayList<Edge>();
			Edge edge = new Edge(v1, v2);
			if (!containsEdge(v1, v2)) {
				adjList[v1].add(edge);
				if (hasCycle()) {
					removeEdge(edge.vertex1, edge.vertex2);
				}
			}
		}
	}

	// Checks if graph contains a certain node or not.
	public boolean containsEdge(int v1, int v2) {
		if (v1 <= verticesNumber && v2 <= verticesNumber) {
			ArrayList<Edge> edgeList;
			edgeList = adjList[v1];
			for (Edge edge : edgeList)
				if (edge.vertex2 == v2) {
					return true;
				}
		}
		return false;
	}

	// Removes an edge between two vertices in the graph.
	public void removeEdge(int v1, int v2) {
		if (v1 <= verticesNumber && v2 <= verticesNumber) {
			ArrayList<Edge> edges = adjList[v1];
			for (int i = 0; i < edges.size(); i++) {
				Edge e = edges.get(i);
				if (e.vertex1 == v1 && e.vertex2 == v2) {
					edges.remove(i);
					break;
				}
			}
		}
	}
	
	// Checks if the graph has a cycle (1).
	boolean hasCycle() {
	    List<Integer> visited = new ArrayList<>();
	    for (int i = 1; i < adjList.length; ++i) {
	      if (hasCycle(i, visited)) {
	        return true;
	      }
	    }
	    return false;
	  }
	
	// Checks if the graph has a cycle (2).
	public boolean hasCycle (int v1, List<Integer> visited) {
		if (visited.contains(v1)) {
			return true;
		}
		visited.add(v1);
		for (Edge edge : adjList[v1]) {
			if (hasCycle(edge.vertex2, visited)) {
				return true;
			}
		}
		visited.remove(visited.size() -1);
		return false;
	}
	
	// Currently doesn't work for nodes with more than one edge coming out of them.
	public void lowestCommonAncestor(int v1, int v2) {
		ArrayList<Integer> ancestors_v1 = new ArrayList<Integer>();
		ArrayList<Integer> ancestors_v2 = new ArrayList<Integer>();
		ancestors_v1.add(v1);
		ancestors_v2.add(v2);
		
		findPath(ancestors_v1, v1);
		findPath(ancestors_v2, v2);
		System.out.println("ancestors_v1: "+ancestors_v1);
		System.out.println("ancestors_v2: "+ancestors_v2);
		
		for (int i : ancestors_v1)
			if (ancestors_v2.contains(i)) {
				System.out.println("Lowest Common Ancestor: " + i);
				break;
			}
		//return 0;
	}
	
	public void findPath(ArrayList<Integer> ancestorList, int vertex) {
		ArrayList<Edge> edgeList;
		edgeList = adjList[vertex];
		for (Edge edge : edgeList) {
			ancestorList.add(edge.vertex2);
			findPath(ancestorList, edge.vertex2);
		}
	}

	// Prints the graph to the console.
	public void printGraph() {
		ArrayList<Edge> edgeList;
		for (int i = 1; i <= verticesNumber; i++) {
			edgeList = adjList[i];
			for (Edge edge : edgeList)
				System.out.println("v1 : " + edge.vertex1 + " v2 : " + edge.vertex2);
		}
	}
	
}