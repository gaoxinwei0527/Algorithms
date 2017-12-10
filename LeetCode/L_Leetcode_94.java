package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 94. Binary Tree Inorder Traversal

 Given a binary tree, return the inorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3],
 1
 \
 2
 /
 3
 return [1,3,2].

 Note: Recursive solution is trivial, could you do it iteratively?
 */
public class L_Leetcode_94 {
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
     * Use stack
     *
     * Time & space - O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        while(root != null){
            st.push(root);
            root = root.left;
        }

        List<Integer> res = new ArrayList<>();
        while(!st.isEmpty()){
            TreeNode next = st.pop();
            res.add(next.val);
            TreeNode right = next.right;
            while(right != null){
                st.push(right);
                right = right.left;
            }
        }

        return res;
    }
}
