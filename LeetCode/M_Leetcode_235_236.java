package LeetCode;

/**
 235. Lowest Common Ancestor of a Binary Search Tree

 Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

 _______6______
 /              \
 ___2__          ___8__
 /      \        /      \
 0      _4       7       9
 /  \
 3   5
 For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class M_Leetcode_235_236 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     *
     * utilize the BST feature, compare the value of root, p and q to decide which subtree is p & q in.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val == root.val || q.val == root.val) return root;
        if(p.val < root.val && q.val > root.val || p.val > root.val && q.val < root.val) return root;
        if(p.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else return lowestCommonAncestor(root.right, p, q);
    }

    /**
     236. Lowest Common Ancestor of a Binary Tree

     Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

     _______3______
     /              \
     ___5__          ___1__
     /      \        /      \
     6      _2       0       8
     /  \
     7   4
     For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
     */

    /**
     * @param root
     * @param p
     * @param q
     * @return
     *
     * for normal binary tree, try to find p or q in subtree, if they belongs to different subtree return root.
     * else recurse with the subtree contains both nodes.
     *
     * trick- this tree could have duplicate nodes, use reference comparison instead of value comparison.
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;
        return (left != null ? left : right);
    }
}
