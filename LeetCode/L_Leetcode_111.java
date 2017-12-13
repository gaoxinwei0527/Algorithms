package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 111. Minimum Depth of Binary Tree

 Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class L_Leetcode_111 {
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
     * level order traverse until first leaf
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int depth = 1;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()){
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode n : level){
                if(n.left == null && n.right == null) return depth;
                if(n.left != null) next.add(n.left);
                if(n.right != null) next.add(n.right);
            }
            level = next;
            depth++;
        }
        return depth;
    }
}
