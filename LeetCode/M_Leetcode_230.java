package LeetCode;

import java.util.Stack;

/**
 230. Kth Smallest Element in a BST

 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class M_Leetcode_230 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param root
     * @param k
     * @return
     *
     * in-order traverse and return the kth num
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        while(root != null){
            st.push(root);
            root = root.left;
        }

        while(!st.isEmpty()){
            TreeNode next = st.pop();
            k--;
            if(k == 0) return next.val;
            TreeNode right = next.right;
            while(right != null){
                st.push(right);
                right = right.left;
            }
        }

        return 0;
    }

    /**
     * @param root
     * @param k
     * @return
     *
     * binary search by count left subtree nodes.
     */
    public int kthSmallest2(TreeNode root, int k) {
        int cnt = count(root.left);
        if(cnt + 1 == k) return root.val;
        if(cnt + 1 > k){
            return kthSmallest(root.left, k);
        }else{
            return kthSmallest(root.right, k - cnt - 1);
        }
    }

    private int count(TreeNode root){
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
}
