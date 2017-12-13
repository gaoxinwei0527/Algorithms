package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 113. Path Sum II

 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
 5
 / \
 4   8
 /   / \
 11  13  4
 /  \    / \
 7    2  5   1
 return
 [
 [5,4,11,2],
 [5,8,4,5]
 ]
 */
public class L_Leetcode_113 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, root, 0, sum);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, TreeNode root, int cur, int sum){
        if(root == null) return;
        if(root.val + cur == sum && root.left == null && root.right == null){
            List<Integer> next = new ArrayList<>(tmp);
            next.add(root.val);
            res.add(next);
            return;
        }

        tmp.add(root.val);
        helper(res, tmp, root.left, cur + root.val, sum);
        helper(res, tmp, root.right, cur + root.val, sum);
        tmp.remove(tmp.size() - 1);
    }
}
