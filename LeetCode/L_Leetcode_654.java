package LeetCode;

/**
 654. Maximum Binary Tree

 Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

 The root is the maximum number in the array.
 The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 Construct the maximum tree by the given array and output the root node of this tree.

 Example 1:
 Input: [3,2,1,6,0,5]
 Output: return the tree root node representing the following tree:

 6
 /   \
 3     5
 \    /
 2  0
 \
 1
 Note:
 The size of the given array will be in the range [1,1000].
 */
public class L_Leetcode_654 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * @param nums
     * @return
     *
     * recursive
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        return helper(nums, 0, n - 1);
    }

    private TreeNode helper(int[] nums, int i, int j){
        if(i > j) return null;
        int k = i;
        for(int x = i + 1; x <= j; x++){
            if(nums[x] > nums[k]) k = x;
        }

        TreeNode root = new TreeNode(nums[k]);
        root.left = helper(nums, i, k - 1);
        root.right = helper(nums, k + 1, j);
        return root;
    }
}
