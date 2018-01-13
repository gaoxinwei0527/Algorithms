package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 501. Find Mode in Binary Search Tree

 Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.
 For example:
 Given BST [1,null,2,2],
 1
 \
 2
 /
 2
 return [2].

 Note: If a tree has more than one mode, you can return them in any order.

 Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class M_Leetcode_501 {
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
     * inorder traverse
     */
    public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        Stack<TreeNode> st = new Stack<>();
        while(root != null){
            st.push(root);
            root = root.left;
        }

        List<Integer> res = new ArrayList<>();
        int max = 0;
        int count = 0;
        int cur = 0;
        while(!st.isEmpty()){
            TreeNode next = st.pop();
            if(count == 0 || cur == next.val){
                count++;
                cur = next.val;
            }else{
                if(max < count){
                    res.clear();
                    res.add(cur);
                    max = count;
                }else if(max == count){
                    res.add(cur);
                }
                count = 1;
                cur = next.val;
            }

            TreeNode right = next.right;
            while(right != null){
                st.push(right);
                right = right.left;
            }
        }

        if(max < count){
            res.clear();
            res.add(cur);
            max = count;
        }else if(max == count){
            res.add(cur);
        }

        int[] r = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            r[i] = res.get(i);
        }

        return r;
    }
}
