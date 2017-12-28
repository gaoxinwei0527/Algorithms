package LeetCode;

import java.util.Stack;

/**
 173. Binary Search Tree Iterator

 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class L_Leetcode_173 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * in-order traverse
     */
    public class BSTIterator {
        Stack<TreeNode> st;

        public BSTIterator(TreeNode root) {
            st = new Stack<>();
            while(root != null){
                st.push(root);
                root = root.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !st.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode next = st.pop();
            TreeNode right = next.right;
            while(right != null){
                st.push(right);
                right = right.left;
            }
            return next.val;
        }
    }

}
