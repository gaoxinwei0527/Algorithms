package LeetCode;

/**
 105. Construct Binary Tree from Preorder and Inorder Traversal

 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.
 */
public class L_Leetcode_105 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return build(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int i, int j, int[] inorder, int a, int b){
        if(i > j) return null;
        TreeNode root = new TreeNode(preorder[i]);
        if(i == j) return root;
        int k = a;
        while(inorder[k] != preorder[i]) k++;
        root.left = build(preorder, i + 1, k - a + i, inorder, a, k - 1);
        root.right = build(preorder, k - a + i + 1, j, inorder, k + 1, b);
        return root;
    }
}
