package LeetCode;

/**
 671. Second Minimum Node In a Binary Tree

 Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:
 Input:
 2
 / \
 2   5
 / \
 5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.
 Example 2:
 Input:
 2
 / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class L_Leetcode_671 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @return
     *
     * minimal num is definitely root.val (if exist)
     * then we need to find minimal num in left & right subtree of root which is different from root.val.
     */
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return -1;
        int left = helper(root.left, root.val);
        int right = helper(root.right, root.val);
        return compare(left, right);
    }

    private int helper(TreeNode root, int min){
        if(root == null) return -1;
        if(root.val != min) return root.val;
        int left = helper(root.left, min);
        int right = helper(root.right, min);
        return compare(left, right);
    }

    private int compare(int left, int right){
        if(left == -1 && right == -1) return -1;
        if(left == -1) return right;
        if(right == -1) return left;
        return (left < right ? left : right);
    }
}
