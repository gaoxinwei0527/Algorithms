package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 144. Binary Tree Preorder Traversal

 Given a binary tree, return the preorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3],
 1
 \
 2
 /
 3
 return [1,2,3].

 Note: Recursive solution is trivial, could you do it iteratively?
 */
public class L_Leetcode_144 {
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
     * Iterative way, use stack
     *
     * Time - O(n)
     * Space - O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        while(root != null){
            res.add(root.val);
            st.push(root);
            root = root.left;
        }

        while(!st.isEmpty()){
            TreeNode next = st.pop();
            TreeNode right = next.right;
            while(right != null){
                res.add(right.val);
                st.push(right);
                right = right.left;
            }
        }

        return res;
    }

    /**
     * @param root
     * @return
     *
     * recursive way
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res){
        if(root == null) return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
