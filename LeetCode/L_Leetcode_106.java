package LeetCode;

/**
 106. Construct Binary Tree from Inorder and Postorder Traversal

 Given inorder and postorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 */
public class L_Leetcode_106 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        return build(inorder, 0, n - 1, postorder, 0, n - 1);
    }

    private TreeNode build(int[] inorder, int i, int j, int[] postorder, int a, int b){
        if(i > j) return null;
        TreeNode root = new TreeNode(postorder[b]);
        if(a == b) return root;
        int k = i;
        while(inorder[k] != postorder[b]) k++;
        root.left = build(inorder, i, k - 1, postorder, a, a + k - 1 - i);
        root.right = build(inorder, k + 1, j, postorder, a + k - i, b - 1);
        return root;
    }
}
