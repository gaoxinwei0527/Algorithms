package LeetCode;

/**
 563. Binary Tree Tilt

 Given a binary tree, return the tilt of the whole tree.

 The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

 The tilt of the whole tree is defined as the sum of all nodes' tilt.

 Example:
 Input:
 1
 /   \
 2     3
 Output: 1
 Explanation:
 Tilt of node 2 : 0
 Tilt of node 3 : 0
 Tilt of node 1 : |2-3| = 1
 Tilt of binary tree : 0 + 0 + 1 = 1
 Note:

 The sum of node values in any subtree won't exceed the range of 32-bit integer.
 All the tilt values won't exceed the range of 32-bit integer.
 */
public class L_Leetcode_563 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int findTilt(TreeNode root) {
        int[] tilt = new int[1];
        sum(tilt, root);
        return tilt[0];
    }

    private int sum(int[] tilt, TreeNode root){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return root.val;
        int l = sum(tilt, root.left);
        int r = sum(tilt, root.right);
        tilt[0] += Math.abs(l - r);
        return (l + r + root.val);
    }
}
