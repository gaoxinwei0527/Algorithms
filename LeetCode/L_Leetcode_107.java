package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 107. Binary Tree Level Order Traversal II

 Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 */
public class L_Leetcode_107 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()){
            List<Integer> level_num = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode n : level){
                level_num.add(n.val);
                if(n.left != null) next.add(n.left);
                if(n.right != null) next.add(n.right);
            }

            level = next;
            res.add(0, level_num);
        }

        return res;
    }
}
