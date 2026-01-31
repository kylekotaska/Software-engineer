public class TreeNode<K, V> {
	private K key;
	private V value;
	private TreeNode<K, V> left;
	private TreeNode<K, V> right;
	private TreeNode<K, V> parent;
	
	public boolean IsLast() {
		if (right == null && left == null) {
			return true;
		}
		return false;
	}
	
	public boolean LeftEmpty() {
		return left == null;
	}
	
	public boolean RightEmpty() {
		return right == null;
	}
	public V GetValue() {
		return value;
	}
	
	public K GetKey() {
		return key;
	}
	
	public TreeNode<K, V> GetLeft() {
		return left;
	}
	
	public TreeNode<K, V> GetRight() {
		return right;
	}
	
	public void SetLeft(TreeNode<K, V> tree) {
		left = tree;
	}
	
	public void SetRight(TreeNode<K, V> tree) {
		right = tree;
	}
	public TreeNode(K k, V v) {
		key = k;
		value = v;
		left = null;
		right = null;
	}
}