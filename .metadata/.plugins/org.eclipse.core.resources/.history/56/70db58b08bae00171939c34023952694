import java.util.NoSuchElementException;

public class GCA <Key extends Comparable<Key>, Value> {

	private Node root;             				// root of BST

	private class Node {
		private Key key;           				// sorted by key
		private Value val;         				// associated data
		private Node left, right; 				// left and right subtrees
		private int N;             				// number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// Is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// Return number of key-value pairs in BST
	public int size() { return size(root); }

	// Return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	// Is given key found in table?
	public boolean contains(Key key) {
		return get(key) != null;
	}

	// Returns value associated with the given key if found, or null if no such key exists.
	public Value get(Key key) { 
		return get(root, key); 
	}

	private Value get(Node x, Key key) {
		if (x == null){
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0){
			return get(x.left, key);
		}
		else if (cmp > 0){
			return get(x.right, key);
		}
		else{
			return x.val;
		}
	}

	// Insert key-value pair into BST. If key already exists, update with new value.
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	
	// Returns the number of links from the root to the deepest leaf.
	// For an empty tree this should return -1.
	// For a tree with only one node it should return 0.
	public int height() 
	{
		if(isEmpty())
		{
			return -1;
		}
		else
		{
			Node node = root; 
			int rtn = findHeight(node);
			return rtn-1;
		}
	}

	private int findHeight(Node x)
	{
		int leftHeight = 0;
		int rightHeight = 0;
		if(x.left  != null)
		{
			leftHeight = findHeight(x.left);
		}
		if(x.right != null)
		{
			rightHeight = findHeight(x.right);
		}

		if(leftHeight > rightHeight)
		{
			return leftHeight + 1;
		}
		else
		{
			return rightHeight + 1;
		}
	}

	// Deletes a key from a tree (if the key is in the tree). 
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null) return null;

		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else { 
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Node t = x;
			x = max(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
		} 
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	} 

	private Node deleteMax(Node x) {
		if (x == null) return null;
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N -=1;
		return x;
	}

	private Node max(Node x) {
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 
}
