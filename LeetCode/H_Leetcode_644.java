package LeetCode;

/**
 644. Maximum Average Subarray II

 Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

 Example 1:
 Input: [1,12,-5,-6,50,3], k = 4
 Output: 12.75
 Explanation:
 when length is 5, maximum average value is 10.8,
 when length is 6, maximum average value is 9.16667.
 Thus return 12.75.
 Note:
 1 <= k <= n <= 10,000.
 Elements of the given array will be in range [-10,000, 10,000].
 The answer with the calculation error less than 10-5 will be accepted.
 */
public class H_Leetcode_644 {
    private boolean check(int[] nums, int k, double mid){
        int n = nums.length;
        double[] sum = new double[n + 1];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + nums[i - 1] - mid;
        }

        double max = sum[k];
        if(max >= 0) return true;

        double minPre = sum[0];
        int i = k;
        while(i < n){
            i++;
            minPre = Math.min(minPre, sum[i - k]);
            max = sum[i];
            if((max - minPre) >= 0) return true;
        }

        return false;
    }

    /**
     * @param nums
     * @param k
     * @return
     *
     * binary search, l = -10000, r = 10000;
     * check mid-
     *   foreach num in nums[], do (num -= mid);
     *   then construct prefix sum array of altered nums[]
     *   then what we need is find (i, j) in sum[] where j - i >= k && sum[j] - sum[i] >= 0, that means max avg is >= mid (at least avg of subarray [i - j] is >= mid)
     *
     *   if we found such (i, j), means mid <= target, l = mid;
     *   else r = mid
     */
    public double findMaxAverage(int[] nums, int k) {
        double l = -10000L;
        double r = 10000L;

        while(l < r){
            if(r - l <= 1e-5) return l;
            double m = (l + r) / 2.0;
            if(check(nums, k, m)) l = m;
            else r = m;
        }

        return l;
    }
}
