package LeetCode;

/**
 222. Count Complete Tree Nodes

 Given a complete binary tree, count the number of nodes.

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class H_Leetcode_222 {
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
     * two points-
     * 1. subtree of complete tree is also complete tree
     * 2. height of complete tree can be calculated only going left child (greedy)
     *
     * if(h_left > h_right) leaf ends in left subtree, add num of right subtree nodes
     * else leaf ends in right subtree, add num of left subtree nodes
     * Tip - use bit manipulation instead of Math.pow();
     *
     * Time - O(h ^ 2)
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int h1 = height(root.left);
        int h2 = height(root.right);
        if(h1 > h2) return (1 << h2) + countNodes(root.left);
        return (1 << h1) + countNodes(root.right);
    }

    private int height(TreeNode node){
        if(node == null) return 0;
        return height(node.left) + 1;
    }
}
