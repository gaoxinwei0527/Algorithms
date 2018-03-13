package LeetCode;

/**
 687. Longest Univalue Path

 Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

 Note: The length of path between two nodes is represented by the number of edges between them.

 Example 1:

 Input:

 5
 / \
 4   5
 / \   \
 1   1   5
 Output:

 2
 Example 2:

 Input:

 1
 / \
 4   5
 / \   \
 4   4   5
 Output:

 2
 Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class L_Leetcode_687 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int longestUnivaluePath(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return 0;
        int[] max = new int[1];
        max[0] = 1;
        helper(root, max);
        return max[0] - 1;
    }

    private int helper(TreeNode root, int[] max){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int l = helper(root.left, max);
        int r = helper(root.right, max);
        int cur = 1;
        int side = 1;
        if(root.left != null && root.left.val == root.val){
            cur += l;
            side = Math.max(side, 1 + l);
        }

        if(root.right != null && root.right.val == root.val){
            cur += r;
            side = Math.max(side, 1 + r);
        }

        max[0] = Math.max(max[0], cur);
        return side;
    }
}
