package LeetCode;

/**
 410. Split Array Largest Sum

 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:

 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)
 Examples:

 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.
 */
public class H_Leetcode_410 {
    /**
     * @param nums
     * @param m
     * @return
     *
     * binary search the target max sum, init range is [max element ~ sum of the array];
     * to test the mid value of each iteration, we try to split array with max sum as mid value,
     * and in greedy way try to put as many elements as possible in each segment.
     * if we get segment num k <= m, means mid value is too big, thus can be decreased, if k > m, means mid value is too small
     */
    public int splitArray(int[] nums, int m) {
        long l = 0;
        long r = 0;
        for(int i : nums){
            l = Math.max(l, i);
            r += i;
        }

        while(l <= r){
            long sum = (l + r) >>> 1;
            int k = 0;
            int i = 0;
            long tmp = 0;
            while(i < nums.length){
                while(i < nums.length && ((tmp + nums[i]) <= sum)){
                    tmp += nums[i];
                    i++;
                }
                k++;
                tmp = 0;
            }

            if(k > m){
                l = sum + 1;
            }else{
                r = sum - 1;
            }
        }

        long res = 0;
        int i = 0;
        long tmp = 0;
        while(i < nums.length){
            while(i < nums.length && (tmp + nums[i]) <= l){
                tmp += nums[i];
                i++;
            }

            res = Math.max(tmp, res);
            tmp = 0;
        }

        return (int)res;
    }

    /**
     * @param nums
     * @param m
     * @return
     *
     * dp way
     *
     * subproblem - S(i, k) means the min max sum of k segments for first i elements in arr, k <= i
     * init - S(i, 1) = sum[0, i]
     *
     * S(i, k) Recurrence-
     * res init as MAX_INT
     * for x in range [k - 1, i - 1]
     *    local_max_sum = Math.max(S(x, k - 1), sum[x + 1, i]); // sum[x + 1, i] means the sum of kth segment
     *    res = Math.min(local_max_sum, res);
     *
     * original problem - S(n, m);
     * bottom up way - dp[i][k] records the min max sum of k segments for first i elements;
     */
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        if(m == 1) return sum[n];

        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            dp[i][1] = sum[i];
        }

        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= i && j <= m; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int x = j - 1; x < i; x++){
                    int tmp = Math.max(dp[x][j - 1], sum[i] - sum[x]);
                    dp[i][j] = Math.min(tmp, dp[i][j]);
                }
            }
        }

        return dp[n][m];
    }
}
