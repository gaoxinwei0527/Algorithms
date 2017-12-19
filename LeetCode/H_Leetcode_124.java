package LeetCode;

/**
 124. Binary Tree Maximum Path Sum

 Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

 For example:
 Given the below binary tree,

 1
 / \
 2   3
 Return 6.
 */
public class H_Leetcode_124 {
    int max = Integer.MIN_VALUE;

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
     * in helper function, keep updating max value, and return single way max value start with root.
     */
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    private int helper(TreeNode root){
        if(root == null) return Integer.MIN_VALUE;
        int left = helper(root.left);
        int right = helper(root.right);
        int val = root.val;
        if(left > 0) val += left;
        if(right > 0) val += right;
        max = Math.max(val, max);
        long single = Math.max(Math.max((long) left + root.val, (long) right + root.val), (long)root.val);
        return (int)single;
    }
}
