package LeetCode;

public class L_Leetcode_198_213 {
    /**
     198. House Robber

     You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

     Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

     Credits:
     Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for adding additional test cases.
     */

    /**
     * @param nums
     * @return
     *
     * 1-dimensional dp, dp[i] means the max amount from [0 - i]
     * Time - O(n)
     * Space - O(1)
     */
    public int rob1(int[] nums) {
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

    /**
     213. House Robber II

     Note: This is an extension of House Robber.

     After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

     Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     */

    /**
     * @param nums
     * @return
     *
     * Same as house robber I, just split the 1st house & last house.
     */
    public int rob2(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(max(nums, 0, nums.length - 2), max(nums, 1, nums.length - 1));
    }

    private int max(int[] nums, int i, int j){
        int s1 = nums[i];
        int s2 = 0;
        for(int k = i + 1; k <= j; k++){
            int tmp1 = s2 + nums[k];
            int tmp2 = Math.max(s1, s2);
            s1 = tmp1;
            s2 = tmp2;
        }
        return Math.max(s1, s2);
    }
}
