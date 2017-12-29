package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 199. Binary Tree Right Side View

 Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

 For example:
 Given the following binary tree,
 1            <---
 /   \
 2     3         <---
 \     \
 5     4       <---
 You should return [1, 3, 4].

 Credits:
 Special thanks to @amrsaqr for adding this problem and creating all test cases.
 */
public class L_Leetcode_199 {
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
     * just level order traverse and get last val of each level
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()){
            res.add(level.get(level.size() - 1).val);
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode n : level){
                if(n.left != null) next.add(n.left);
                if(n.right != null) next.add(n.right);
            }
            level = next;
        }
        return res;
    }
}
