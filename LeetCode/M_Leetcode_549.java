package LeetCode;

/**
 549. Binary Tree Longest Consecutive Sequence II

 Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

 Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

 Example 1:
 Input:
 1
 / \
 2   3
 Output: 2
 Explanation: The longest consecutive path is [1, 2] or [2, 1].
 Example 2:
 Input:
 2
 / \
 1   3
 Output: 3
 Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
public class M_Leetcode_549 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @return
     *
     * at each node, get longest increasing / decreasing length, and update max[0] with local max length.
     * return both increasing / decreasing length at each node.
     */
    public int longestConsecutive(TreeNode root) {
        int[] max = new int[1];
        helper(max, root);
        return max[0];
    }

    private int[] helper(int[] max, TreeNode root){
        if(root == null) return new int[]{0, 0};
        if(root.left == null && root.right == null){
            max[0] = Math.max(max[0], 1);
            return new int[]{1, 1};
        }
        int[] res = new int[]{1, 1};

        if(root.left != null){
            int[] left_seq = helper(max, root.left);
            if(root.left.val == root.val + 1) res[0] = Math.max(res[0], 1 + left_seq[0]);
            else if(root.left.val == root.val - 1) res[1] = Math.max(res[1], 1 + left_seq[1]);
        }

        if(root.right != null){
            int[] right_seq = helper(max, root.right);
            if(root.right.val == root.val + 1) res[0] = Math.max(res[0], 1 + right_seq[0]);
            else if(root.right.val == root.val - 1) res[1] = Math.max(res[1], 1 + right_seq[1]);
        }

        int local = res[0] + res[1] - 1;
        max[0] = Math.max(max[0], local);
        return res;
    }
}
