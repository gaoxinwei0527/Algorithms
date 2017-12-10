package LeetCode;

import java.util.Stack;

/**
 98. Validate Binary Search Tree

 Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:
 2
 / \
 1   3
 Binary tree [2,1,3], return true.
 Example 2:
 1
 / \
 2   3
 Binary tree [1,2,3], return false.
 */
public class L_Leetcode_98 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @return
     *
     * inorder traverse to validate if all nodes are in order and there is no duplicates
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> st = new Stack<>();
        while(root != null){
            st.push(root);
            root = root.left;
        }

        long cur = (long)Integer.MIN_VALUE - 1;
        while(!st.isEmpty()){
            TreeNode next = st.pop();
            if(next.val <= cur) return false;
            cur = next.val;
            TreeNode right = next.right;
            while(right != null){
                st.push(right);
                right = right.left;
            }
        }

        return true;
    }
}
