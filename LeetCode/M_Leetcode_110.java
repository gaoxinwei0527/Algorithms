package LeetCode;

/**
 110. Balanced Binary Tree

 Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class M_Leetcode_110 {
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
     * use height to record if balanced.
     * recursive way
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return (height(root) != -1);
    }

    private int height(TreeNode root){
        if(root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);
        if(Math.abs(left - right) > 1 || left == -1 || right == -1) return -1;

        return Math.max(left, right) + 1;
    }
}
