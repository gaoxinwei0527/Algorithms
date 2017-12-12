package LeetCode;

/**
 108. Convert Sorted Array to Binary Search Tree

 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.


 Example:

 Given the sorted array: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

 0
 / \
 -3   9
 /   /
 -10  5
 */
public class L_Leetcode_108 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int i, int j){
        if(i > j) return null;
        if(i == j) return new TreeNode(nums[i]);
        int m = i + (j - i) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = build(nums, i, m - 1);
        root.right = build(nums, m + 1, j);
        return root;
    }
}
