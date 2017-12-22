package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 145. Binary Tree Postorder Traversal

 Given a binary tree, return the postorder traversal of its nodes' values.

 For example:
 Given binary tree {1,#,2,3},
 1
 \
 2
 /
 3
 return [3,2,1].

 Note: Recursive solution is trivial, could you do it iteratively?
 */
public class M_Leetcode_145 {
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
     * iterative way using stack. basically keep the nodes in stack in order of root -> right->left, which is reverse order of post order
     * then every time we pop out a node, we insert it to start of the result list.
     *
     * Time - O(n ^ 2), insert val to start of list requires shift
     * Space - O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode next = st.pop();
            res.add(0, next.val);
            if(next.left != null) st.push(next.left);
            if(next.right != null) st.push(next.right);
        }

        return res;
    }

    /**
     * @param root
     * @return
     *
     * recursive way
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res){
        if(root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }
}
