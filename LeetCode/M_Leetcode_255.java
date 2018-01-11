package LeetCode;

import java.util.Stack;

/**
 255. Verify Preorder Sequence in Binary Search Tree

 Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

 You may assume each number in the sequence is unique.

 Follow up:
 Could you do it using only constant space complexity?
 */
public class M_Leetcode_255 {
    /**
     * @param preorder
     * @return
     *
     * recursive way
     */
    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1);
    }

    private boolean verify(int[] preorder, int i, int j){
        if(i > j) return true;

        int root = preorder[i];
        int k = i + 1;
        for(; k <= j; k++){
            if(preorder[k] > root) break;
        }

        for(int m = k; m <= j; m++){
            if(preorder[m] < root) return false;
        }

        return verify(preorder, i + 1, k - 1) && verify(preorder, k, j);
    }

    /**
     * @param preorder
     * @return
     *
     * iterative way, simulate preorder traverse
     */
    public boolean verifyPreorder2(int[] preorder) {
        if(preorder.length <= 1) return true;
        Stack<Integer> st = new Stack<>();
        st.push(preorder[0]);

        int i = 1;
        while(i < preorder.length && preorder[i] < st.peek()) st.push(preorder[i++]);

        int low = st.peek();
        while(i < preorder.length){
            if(preorder[i] < low) return false;
            while(!st.isEmpty() && st.peek() < preorder[i]) low = st.pop();
            st.push(preorder[i++]);
        }

        return true;
    }
}
