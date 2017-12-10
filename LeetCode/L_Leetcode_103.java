package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 103. Binary Tree Zigzag Level Order Traversal

 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 */
public class L_Leetcode_103 {
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        boolean leftToRight = true;
        while(!level.isEmpty()){
            List<TreeNode> next = new ArrayList<>();
            List<Integer> level_num = new ArrayList<>();
            for(TreeNode n : level){
                if(leftToRight){
                    level_num.add(n.val);
                }else{
                    level_num.add(0, n.val);
                }
                if(n.left != null) next.add(n.left);
                if(n.right != null) next.add(n.right);
            }

            res.add(level_num);
            level = next;
            leftToRight = !leftToRight;
        }

        return res;
    }
}
