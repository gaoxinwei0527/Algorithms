package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 257. Binary Tree Paths

 Given a binary tree, return all root-to-leaf paths.

 For example, given the following binary tree:

 1
 /   \
 2     3
 \
 5
 All root-to-leaf paths are:

 ["1->2->5", "1->3"]
 */
public class L_Leetcode_257 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, root);
        return res;
    }

    private void helper(List<String> res, List<Integer> cur, TreeNode root){
        cur.add(root.val);
        if(root.left == null && root.right == null){
            StringBuilder sb = new StringBuilder("" + cur.get(0));
            for(int i = 1; i < cur.size(); i++){
                sb.append("->").append(cur.get(i));
            }
            res.add(sb.toString());
        }

        if(root.left != null) helper(res, cur, root.left);
        if(root.right != null) helper(res, cur, root.right);
        cur.remove(cur.size() - 1);
    }
}
