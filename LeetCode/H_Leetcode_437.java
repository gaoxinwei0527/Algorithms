package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 437. Path Sum III

 You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

 10
 /  \
 5   -3
 / \    \
 3   2   11
 / \   \
 3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11
 */
public class H_Leetcode_437 {
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
     *
     * this question is asking for all paths with the sum, we can split it into question of the path num start with current root with the sum.
     * then just traverse all tree nodes and make it as start point and get the possible path nums.
     */
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int total = 0;
        while(!q.isEmpty()){
            TreeNode next = q.poll();
            total += helper(next, sum);
            if(next.left != null) q.offer(next.left);
            if(next.right != null) q.offer(next.right);
        }

        return total;
    }

    private int helper(TreeNode root, int sum){
        if(root == null) return 0;
        int res = (root.val == sum ? 1 : 0);
        res += helper(root.left, sum - root.val);
        res += helper(root.right, sum - root.val);
        return res;
    }
}
