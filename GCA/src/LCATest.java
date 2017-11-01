import static org.junit.Assert.*;
import org.junit.Test;

public class LCATest {

	@Test
	public void testContains() {
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		assertEquals("Checking contains() on empty tree", false, bst.contains(5));

		bst.put(7, 7); // _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 6
		bst.put(6, 6); // \ /
		bst.put(4, 4); // 2 4
		bst.put(5, 5); // \
						// 5

		assertSame("Checking contains() on a non-empty tree", true, bst.contains(6));
	}

	@Test
	public void testHeight() {
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		assertEquals("Checking height of empty tree", -1, bst.height());

		bst.put(7, 7);
		assertEquals("Checking height of single node tree", 0, bst.height());

		// _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 5
		bst.put(5, 5); // \
						// 2

		assertEquals("Checking height of non-empty tree", 3, bst.height());
	}

	@Test
	public void testLowestCommonAncestor() {
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		assertSame("Checking lowest common ancestor of an empty tree", null, bst.lowestCommonAncestor(bst.root, 7, 8));

		bst.put(7, 7); // _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 6
		bst.put(6, 6); // \ /
		bst.put(4, 4); // 2 4
		bst.put(5, 5); // \
						// 5

		assertSame("Checking lowest common ancestor of tree that doesn't contain one of the keys", null,
				bst.lowestCommonAncestor(bst.root, 7, 9));
		assertSame("Checking lowest common ancestor of tree that doesn't contain both of the keys", null,
				bst.lowestCommonAncestor(bst.root, -5, 9));
		assertSame("Checking lowest common ancestor of two Keys", 7, bst.lowestCommonAncestor(bst.root, 7, 8));
		assertSame("Checking lowest common ancestor of two Keys", 3, bst.lowestCommonAncestor(bst.root, 6, 2));
	}

	/********************************
	 Tests for Directed Acyclic Graph
	 ********************************/
	@Test
	public void testDAG_contains() {
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);

		assertTrue("Checks if graph contains certain directed edge.", graph.containsEdge(1, 2));
		assertTrue("Checks if graph contains certain directed edge.", graph.containsEdge(1, 3));
		assertTrue("Checks if graph contains certain directed edge.", graph.containsEdge(2, 3));
		assertFalse("Checks if graph contains certain directed edge.", graph.containsEdge(5, 6));

		graph.removeEdge(2, 3);
		assertFalse("Checks if graph still contains a removed edge.", graph.containsEdge(2, 3));
	}

	@Test
	public void testDAG_isEmpty() {
		DirectedAcyclicGraph graph1 = new DirectedAcyclicGraph(0);
		assertTrue("Checks if given graph is empty.", graph1.isEmpty());

		DirectedAcyclicGraph graph2 = new DirectedAcyclicGraph(1);
		assertFalse("Checks if given graph is not empty.", graph2.isEmpty());
	}

	@Test
	public void testDAG_hasCycle() {
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(7);
		graph.addEdge(2, 1);
		graph.addEdge(3, 2);
		graph.addEdge(4, 3);
		graph.addEdge(7, 4);
		graph.addEdge(7, 6);
		graph.addEdge(6, 5);
		graph.addEdge(5, 2);

		// If the code is correct, the graph should have no cycles.
		assertFalse("Checks if graph has any cycles.", graph.hasCycle());
	}

	@Test
	public void testDAG_LowestCommonAncestor() {
		DirectedAcyclicGraph graph = new DirectedAcyclicGraph(7);
		graph.addEdge(2, 1);
		graph.addEdge(3, 2);
		graph.addEdge(4, 3);
		graph.addEdge(7, 4);
		graph.addEdge(7, 6);
		graph.addEdge(6, 5);
		graph.addEdge(5, 2);

		assertEquals("Checks the lowest common ancestor of two nodes in DAG.", 2, graph.lowestCommonAncestor(6, 3));
		assertEquals("Checks the lowest common ancestor of two nodes in DAG.", 1, graph.lowestCommonAncestor(1, 2));
		assertEquals("Checks the lowest common ancestor of two nodes in DAG.", 6, graph.lowestCommonAncestor(6, 7));
	}
}
