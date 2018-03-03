package LeetCode;

/**
 673. Number of Longest Increasing Subsequence

 Given an unsorted array of integers, find the number of longest increasing subsequence.

 Example 1:
 Input: [1,3,5,4,7]
 Output: 2
 Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 Example 2:
 Input: [2,2,2,2,2]
 Output: 5
 Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class L_Leetcode_673 {
    /**
     * @param nums
     * @return
     *
     * T(i) means num of LIS end with nums[i]
     * L(i) means len of LIS end with nums[i]
     *
     * recurrence-
     *  foreach k in [0, i - 1]
     *    if(nums[k] < nums[i])
     *      if(L(k) + 1 > L(i))
     *        L(i) = L(k) + 1
     *        T(i) = T(k)
     *      else if(L(k) + 1 == L(i))
     *        T(i) += T(k)
     *  also update max_len
     *
     *  original problem-
     *  sum of T(i) foreach i where L(i) == max_len
     *
     *  bottom-up:
     *  dp[i] record T(i)
     *  lis[i] record L(i)
     *
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if(len <= 1) return len;

        int[] dp = new int[len];
        dp[0] = 1;
        int[] lis = new int[len];
        lis[0] = 1;
        int max = 1;

        for(int i = 1; i < len; i++){
            dp[i] = 1;
            lis[i] = 1;
            for(int k = 0; k < i; k++){
                if(nums[k] < nums[i]){
                    if(lis[k] + 1 > lis[i]){
                        lis[i] = lis[k] + 1;
                        dp[i] = dp[k];
                    }else if(lis[k] + 1 == lis[i]){
                        dp[i] += dp[k];
                    }
                }
            }

            max = Math.max(max, lis[i]);
        }

        int res = 0;
        for(int i = 0; i < len; i++){
            if(max == lis[i]) res += dp[i];
        }

        return res;
    }
}
