package LeetCode;

/**
 129. Sum Root to Leaf Numbers

 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 For example,

 1
 / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.
 */
public class L_Leetcode_129 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int res = 0;

    /**
     * @param root
     * @return
     *
     * recursive way
     */
    public int sumNumbers(TreeNode root) {
        helper(root, "");
        return res;
    }

    private void helper(TreeNode root, String cur){
        if(root == null) return;
        if(root.left == null && root.right == null) {
            res += Integer.parseInt(cur + root.val);
            return;
        }

        if(root.left != null) helper(root.left, cur + root.val);
        if(root.right != null) helper(root.right, cur + root.val);
    }
}
