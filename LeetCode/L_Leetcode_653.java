package LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 653. Two Sum IV - Input is a BST

 Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

 Example 1:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 9

 Output: True
 Example 2:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 28

 Output: False
 */
public class L_Leetcode_653 {
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
     * in order traverse and use hash set to record visited nums
     */
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        Stack<TreeNode> st = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        while(root != null){
            st.push(root);
            root = root.left;
        }

        while(!st.isEmpty()){
            TreeNode next = st.pop();
            if(visited.contains(k - next.val)) return true;
            TreeNode right = next.right;
            while(right != null){
                st.push(right);
                right = right.left;
            }
            visited.add(next.val);
        }

        return false;
    }
}
