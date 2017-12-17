package LeetCode;

/**
 312. Burst Balloons

 Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

 Find the maximum coins you can collect by bursting the balloons wisely.

 Note:
 (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

 Example:

 Given [3, 1, 5, 8]

 Return 167

 nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class H_Leetcode_312 {
    /**
     * @param nums
     * @return
     *
     * 2-dimentional dp, dp[i][j] means the maximum coin we can get from the range i to j (i and j are exclusive);
    So we can conclude if j == i, or j == i+1, dp[i][j] should be 0 because there is no inclusive balloon (this is by default initialized by the dp array).
    Let's say distance = j - i; Then the minimal distance we should calculate against is 2.
    Firstly we choose a distance from 2 to length-1.
    Then we choose a start point as i, and end point as j.
    Then iterate from i+1 to j-1, during each iteration, the current balloon k, we can assume k is the last balloon to be bursted in range i+1 to j-1. Then it means last round coin is num[i] * num[k] * num[j], as i and j are the boundary.
    And range i+1 to k-1 can give maxmimun dp[i][k] coins, range k+1 to j-1 can give maximum dp[k][j] coins. So,
    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + num[i] * num[k] * num[j])

    tip: nums[] can be expanded to fullNums[nums.length + 2], and fullNums[0] = 1, fullNums[nums.length+1] = 1 as boundary.
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] nums2 = new int[n + 2];
        nums2[0] = 1;
        nums2[n + 1] = 1;
        System.arraycopy(nums, 0, nums2, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for(int len = 3; len <= n + 2; len++){
            for(int i = 0; i + len <= n + 2; i++){
                for(int j = i + 1; j < i + len - 1; j++){
                    dp[i][i + len - 1] = Math.max(dp[i][i + len - 1], dp[i][j] + dp[j][i + len - 1] + nums2[i] * nums2[i + len - 1] * nums2[j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
