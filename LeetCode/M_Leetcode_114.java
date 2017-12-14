package LeetCode;

import java.util.Stack;

/**
 114. Flatten Binary Tree to Linked List

 Given a binary tree, flatten it to a linked list in-place.

 For example,
 Given

 1
 / \
 2   5
 / \   \
 3   4   6
 The flattened tree should look like:
 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6
 */
public class M_Leetcode_114 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     *
     * preorder traverse
     */
    public void flatten(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode next = st.pop();
            if(next.right != null) st.push(next.right);
            if(next.left != null) st.push(next.left);
            next.left = null;
            next.right = st.isEmpty() ? null : st.peek();
        }
    }
}
