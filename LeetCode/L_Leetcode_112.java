package LeetCode;

/**
 112. Path Sum

 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class L_Leetcode_112 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        return pathSum(root, 0, sum);
    }

    private boolean pathSum(TreeNode root, int cur, int sum){
        if(root == null) return false;
        if(cur + root.val == sum && root.left == null && root.right == null) return true;
        return pathSum(root.left, cur + root.val, sum) || pathSum(root.right, cur + root.val, sum);
    }
}
