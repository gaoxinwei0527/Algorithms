package LeetCode;

/**
 494. Target Sum

 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20.
 The sum of elements in the given array will not exceed 1000.
 Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class M_Leetcode_494 {
    /**
     * @param nums
     * @param S
     * @return
     *
     * S(i, j) means the num of ways for first nums [0 - i] to form target j
     *
     * init - S(0, nums[0]) = 1; S(0, -nums[0]) = 1;
     * recurrence -
     * foreach k in range [-total, total]
     *   if(S(i - 1, k) > 0)
     *      S(i, k + nums[0]) += S(i - 1, k)
     *      S(i, k - nums[0]) += S(i - 1, k)
     *
     *  original problem-
     *  S(n - 1, target)
     *
     *  bottom up-
     *  dp[i][j] store the result of S(i, j)
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(S > sum || S < -sum || nums.length == 0) return 0;

        int[][] dp = new int[nums.length][2 * sum + 1];
        dp[0][nums[0] + sum]++;
        dp[0][-nums[0] + sum]++;
        for(int i = 1; i < nums.length; i++){
            for(int k = -sum; k <= sum; k++){
                if(dp[i - 1][k + sum] > 0) {
                    dp[i][nums[i] + k + sum] += dp[i - 1][k + sum];
                    dp[i][-nums[i] + k + sum] += dp[i - 1][k + sum];
                }
            }
        }

        return dp[nums.length - 1][S + sum];
    }
}
