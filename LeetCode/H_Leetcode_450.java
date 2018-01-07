package LeetCode;

/**
 450. Delete Node in a BST

 Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

 Basically, the deletion can be divided into two stages:

 Search for a node to remove.
 If the node is found, delete the node.
 Note: Time complexity should be O(height of tree).

 Example:

 root = [5,3,6,2,4,null,7]
 key = 3

 5
 / \
 3   6
 / \   \
 2   4   7

 Given key to delete is 3. So we find the node with value 3 and delete it.

 One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

 5
 / \
 4   6
 /     \
 2       7

 Another valid answer is [5,2,6,null,4,null,7].

 5
 / \
 2   6
 \   \
 4   7
 */
public class H_Leetcode_450 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @param key
     * @return
     *
     * since it's not required to make the result tree balanced. so after deletion, we can do-
     * 1. if cur has right child, connect pre to cur.right, connect left most node in right subtree of cur to left subtree of cur
     * 2. if cur doesn't have right child, connect pre to cur.left
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(0);
        dummy.right = root;

        TreeNode pre = dummy;
        TreeNode cur = root;
        while(cur != null && cur.val != key){
            pre = cur;
            if(cur.val < key) cur = cur.right;
            else cur = cur.left;
        }

        if(cur == null) return root;

        if(cur.right == null){
            if(pre.right == cur) pre.right = cur.left;
            else pre.left = cur.left;
        }else{
            if(pre.right == cur) pre.right = cur.right;
            else pre.left = cur.right;

            TreeNode tmp = cur.right;
            while(tmp.left != null) tmp = tmp.left;
            tmp.left = cur.left;
        }

        cur.left = null;
        cur.right = null;

        return dummy.right;
    }
}
