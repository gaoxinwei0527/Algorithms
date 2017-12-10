package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 95. Unique Binary Search Trees II

 Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3
 */
public class H_Leetcode_95 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param n
     * @return
     *
     * Recursive way
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int i, int j){
        List<TreeNode> res = new ArrayList<>();
        if(i > j){
            res.add(null);
            return res;
        }
        if(i == j){
            res.add(new TreeNode(i));
            return res;
        }

        for(int k = i; k <= j; k++){
            List<TreeNode> left = helper(i, k - 1);
            List<TreeNode> right = helper(k + 1, j);
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode root = new TreeNode(k);
                    root.left = copy(l);
                    root.right = copy(r);
                    res.add(root);
                }
            }
        }
        return res;
    }

    private TreeNode copy(TreeNode root){
        if(root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = copy(root.left);
        res.right = copy(root.right);
        return res;
    }
}
