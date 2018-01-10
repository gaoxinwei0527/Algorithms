package LeetCode;

/**
 250. Count Univalue Subtrees

 Given a binary tree, count the number of uni-value subtrees.

 A Uni-value subtree means all nodes of the subtree have the same value.

 For example:
 Given binary tree,
 5
 / \
 1   5
 / \   \
 5   5   5
 return 4.
 */
public class M_Leetcode_250 {
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
     * use a helper to check if current tree is univalue, also keep updating the count
     */
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return 0;
        int[] count = new int[1];
        helper(count, root);
        return count[0];
    }

    private boolean helper(int[] count, TreeNode root){
        if(root.left == null && root.right == null){
            count[0]++;
            return true;
        }

        boolean left = true;
        if(root.left != null){
            left = helper(count, root.left);
        }

        boolean right = true;
        if(root.right != null){
            right = helper(count, root.right);
        }

        if(!(left && right)) return false;
        if(root.right != null && root.right.val != root.val) return false;
        if(root.left != null && root.left.val != root.val) return false;
        count[0]++;
        return true;
    }
}
