package LeetCode;

import java.util.Stack;

/**
 156. Binary Tree Upside Down

 Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

 For example:
 Given a binary tree {1,2,3,4,5},
 1
 / \
 2   3
 / \
 4   5
 return the root of the binary tree [4,5,2,#,#,3,1].
 4
 / \
 5   2
 / \
 3   1
 confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 */
public class M_Leetcode_156 {
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
     * firstly the leftmost node must be the new root. so we can use stack to push in left children until reach the leftmost one.
     * then in stack from bottom to top, it would be all parent to left child pairs.
     * For each popped left child, set its left child to parent's right child, set its right child to its parent. That's it.
     *
     * Because right child would have at most single node. so no recursive on right subtree.
     *
     * Time - O(n)
     * Space - O(n), worst case there is no right subtrees.
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        while(root != null){
            st.push(root);
            root = root.left;
        }

        if(st.isEmpty()) return null;
        TreeNode newRoot = st.peek();

        while(!st.isEmpty()){
            TreeNode next = st.pop();
            if(!st.isEmpty()){
                next.left = st.peek().right;
                next.right = st.peek();
            }else{
                next.left = null;
                next.right = null;
            }
        }

        return newRoot;
    }
}
