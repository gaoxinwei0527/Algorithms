package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 99. Recover Binary Search Tree

 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Note:
 A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class H_Leetcode_99 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     *
     * Morris Traverse - inorder
     *
     * Time - O(n)
     * Space - O(1)
     */
    public void recoverTree(TreeNode root) {
        TreeNode first = null; // first swapped node
        TreeNode second = null; // second swapped node
        TreeNode pre = null; // previous node in inorder sequence

        while(root != null){
            // compare prev with cur, if not in order, record
            if(pre != null && pre.val > root.val){
                if(first == null){
                    first = pre;
                    second = root;
                }else{
                    second = root;
                }
            }


            TreeNode left = root.left;
            if(left == null){
                // if cur has no left child, means it should go ahead traverse right subtree
                pre = root;
                root = root.right; // note that, at this point, the rightmost node of left subtree of root is pointing to root,
                                   // so if cur is the rightmost node of left subtree, the next will be set to root.
            }else{
                // find the rightmost node of left subtree
                while(left.right != null && left.right != root){
                    left = left.right;
                }

                if(left.right != root){ // means first time visit root, set the connection from the rightmost node of left subtree to root
                                        // and move on to left subtree, note that in this case, we haven't found the next node in inorder sequence,
                                        // so we don't update the prev pointer
                    left.right = root;
                    root = root.left;
                }else{ // means second time visit root, revert the connection, and move on to right subtree
                    left.right = null;
                    pre = root;
                    root = root.right;
                }
            }
        }

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    /**
     * @param root
     *
     * normal recursive inorder traverse
     *
     * Time - O(n)
     * Space - O(n)
     */
    public void recoverTree2(TreeNode root) {
        ArrayList<TreeNode> traverse=new ArrayList<>();
        inorder(traverse, root);
        TreeNode l=null, r=null;
        for(int i=0;i<traverse.size()-1;i++){
            if(traverse.get(i).val>traverse.get(i+1).val){
                if(l==null){
                    l=traverse.get(i);
                    r=traverse.get(i+1);
                }else{
                    r=traverse.get(i+1);
                }
            }
        }
        int tmp=l.val;
        l.val=r.val;
        r.val=tmp;
    }

    private void inorder(List<TreeNode> traverse, TreeNode root){
        if(root==null) return;
        inorder(traverse, root.left);
        traverse.add(root);
        inorder(traverse, root.right);
    }
}
