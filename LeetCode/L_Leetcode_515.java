package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 515. Find Largest Value in Each Tree Row

 You need to find the largest value in each row of a binary tree.

 Example:
 Input:

 1
 / \
 3   2
 / \   \
 5   3   9

 Output: [1, 3, 9]
 */
public class L_Leetcode_515 {
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
     * level order traverse
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()){
            int max = Integer.MIN_VALUE;
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode n : level){
                max = Math.max(max, n.val);
                if(n.left != null) next.add(n.left);
                if(n.right != null) next.add(n.right);
            }
            level = next;
            res.add(max);
        }

        return res;
    }
}
