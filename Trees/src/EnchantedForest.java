



public class EnchantedForest {
	
	TreeNode<Integer, String> firstTree = null;
	
	private TreeNode<Integer, String> TraverseTree(int power, TreeNode<Integer, String> currentTree) {
		System.out.print("Comparing " + power + " to " + currentTree.GetKey());
		System.out.println();
		
		if (currentTree.IsLast()) {
			System.out.println(currentTree.GetValue() + " is last");
			return currentTree;
		}
	
		
		if (power < currentTree.GetKey()) {
			System.out.println("Getting left tree");
			if (currentTree.LeftEmpty()) {
				return currentTree;
			}
				
			return TraverseTree(power, currentTree.GetLeft());
		}
		else if (power > currentTree.GetKey()) {
			System.out.println("Getting right tree");
			if (currentTree.RightEmpty()) {
				return currentTree;
			}
			return TraverseTree(power, currentTree.GetRight());
		}
		else {
			System.out.println("Keys are the same?");
		}
		
		System.out.println("Returning null");
		return null;
	}
	public void plantTree(int power, String name) {
		
		if (firstTree == null) {
			firstTree = new TreeNode(power, name);
		}
		else {
			TreeNode<Integer, String> newTree = new TreeNode(power, name);
			TreeNode<Integer, String> parentTree = TraverseTree(power, firstTree);
			
			if (parentTree == null) {
				return;
			}
			
			if (newTree.GetKey() < parentTree.GetKey()) {
				System.out.println("This will become the left of " + parentTree.GetValue());
				parentTree.SetLeft(newTree);
			}
			else if (newTree.GetKey() > parentTree.GetKey()) {
				System.out.println("This will become the right of " + parentTree.GetValue());
				parentTree.SetRight(newTree);
			}
		}
		
		System.out.println("Tree planted!");
	}
	
	public boolean witherTree(int power) {
		return true;
	}
	
//	TreeNode findTree(int power) {
//		
//	}
//	
//	List<TreeNode> getTreesInRange(int lowerBound, int upperBound) {
//		
//	}
}