package LeetCode;

/**
 53. Maximum Subarray

 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class L_Leetcode_53 {
    /**
     * @param nums
     * @return
     *
     * simple dp use nums[i] represent max value of subarrays end with index i
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }
}
