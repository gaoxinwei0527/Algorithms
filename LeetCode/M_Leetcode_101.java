package LeetCode;

/**
 101. Symmetric Tree

 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.
 */
public class M_Leetcode_101 {
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
     * simple recursive way
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSame(root.left, root.right);
    }

    private boolean isSame(TreeNode a, TreeNode b){
        if(a == null && b == null) return true;
        if(a == null || b == null || a.val != b.val) return false;
        return isSame(a.left, b.right) && isSame(a.right, b.left);
    }
}
