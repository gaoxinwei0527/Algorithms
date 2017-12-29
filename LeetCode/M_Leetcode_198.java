package LeetCode;

/**
 198. House Robber

 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Credits:
 Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for adding additional test cases.
 */
public class M_Leetcode_198 {
    /**
     * @param nums
     * @return
     *
     * 1-dimensional dp, dp[i] means the max amount from [0 - i]
     * Time - O(n)
     * Space - O(1)
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        int max = nums[0];
        for(int i = 1; i < n; i++){
            int local = Math.max(nums[i - 1], nums[i] + (i > 1 ? nums[i - 2] : 0));
            nums[i] = local;
            max = Math.max(local, max);
        }
        return max;
    }
}
