
// Need to know the number of vertices before implementation.
// Only takes integer values (greater than 0) for edges. Need to change to Values
// Think this only implements a directed graph.

import java.util.ArrayList;

public class DirectedAcyclicGraph {

	public static ArrayList<Edge>[] adjList;
	public static int[] inboundEdges;
	public int verticesNumber;

	public DirectedAcyclicGraph(int verticesNumber) {
		adjList = (ArrayList<Edge>[]) new ArrayList[verticesNumber + 1];
		for (int i = 1; i <= verticesNumber; i++) {
			adjList[i] = new ArrayList<Edge>();
		}
		inboundEdges = new int[verticesNumber + 1];
		this.verticesNumber = verticesNumber;
	}

	// Adds an edge between two vertices in the graph.
	public void addEdge(int v1, int v2) {
		Edge edge = new Edge(v1, v2);
		if (adjList[v1].contains(edge) == false) {
			adjList[v1].add(new Edge(v1, v2));
		}
	}

	// Removes an edge between two nodes in the graph.
	public void removeEdge(int v1, int v2) {
		ArrayList<Edge> edges = adjList[v1];
		for (int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			if (e.vertex1 == v1 && e.vertex2 == v2) {
				edges.remove(i);
				break;
			}
		}
	}

	// Returns the count of outgoing edges for a given node in the graph.
	public ArrayList<Edge> getOutEdges(int v1) {
		return adjList[v1];
	}

	// Returns the count of incoming edges for each of the nodes in the graph.
	public int[] getInEdgesCount() {
		for (int i = 1; i <= verticesNumber; i++) {
			for (int j = 1; j < adjList.length; j++) {
				ArrayList<Edge> list = adjList[j];
				for (Edge e : list) {
					if (e.vertex2 == i)
						inboundEdges[i]++;
				}
			}

		}
		return inboundEdges;
	}

	// Prints the graph to the console.
	public void printGraph() {
		ArrayList<Edge> edgeList;
		for (int i = 1; i < verticesNumber; i++) {
			edgeList = adjList[i];
			for (Edge e : edgeList)
				System.out.println("u : " + e.vertex1 + " v : " + e.vertex2);
		}
	}
}