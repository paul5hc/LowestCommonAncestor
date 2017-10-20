import static org.junit.Assert.*;

import org.junit.Test;

public class LCATest {

	@Test
	public void testContains(){
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		assertEquals("Checking contains() on empty tree", false, bst.contains(5));
		
		bst.put(7, 7);   	//        _7_
		bst.put(8, 8);   	//      /     \
		bst.put(3, 3);   	//    _3_      8
		bst.put(1, 1);   	//  /     \     
		bst.put(2, 2);   	// 1       6     
		bst.put(6, 6);   	//  \     /
		bst.put(4, 4);   	//   2   4
		bst.put(5, 5);   	//        \
		                	//         5


		assertSame("Checking contains() on a non-empty tree", true, bst.contains(6)); 
	}
	
	@Test
	public void testHeight() {
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		assertEquals("Checking height of empty tree", -1, bst.height());

		bst.put(7, 7);   
		assertEquals("Checking height of single node tree", 0, bst.height());

						   //         _7_
		bst.put(8, 8);     //       /     \
		bst.put(3, 3);     //     _3_      8
		bst.put(1, 1);     //   /     \
		bst.put(2, 2);     //  1       5
		bst.put(5, 5);     //   \     
						   //    2   

		assertEquals("Checking height of non-empty tree", 3, bst.height());
	}
	
	@Test
	public void testLowestCommonAncestor(){
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		assertSame("Checking lowest common ancestor of an empty tree", null, bst.lowestCommonAncestor(bst.root, 7, 8)); 
		
		bst.put(7, 7); 		//        _7_
		bst.put(8, 8);   	//      /     \
		bst.put(3, 3);   	//    _3_      8
		bst.put(1, 1);   	//  /     \     
		bst.put(2, 2);   	// 1       6     
		bst.put(6, 6);   	//  \     /
		bst.put(4, 4);   	//   2   4
		bst.put(5, 5);   	//        \
		                	//         5

		assertSame("Checking lowest common ancestor of tree that doesn't contain one of the keys", null, bst.lowestCommonAncestor(bst.root, 7, 9)); 
		assertSame("Checking lowest common ancestor of tree that doesn't contain both of the keys", null, bst.lowestCommonAncestor(bst.root, -5, 9)); 
		assertSame("Checking lowest common ancestor of two Keys", 7, bst.lowestCommonAncestor(bst.root, 7, 8)); 
		assertSame("Checking lowest common ancestor of two Keys", 3, bst.lowestCommonAncestor(bst.root, 6, 2)); 
	}

}
