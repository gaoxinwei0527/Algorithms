package LeetCode;

/**
 209. Minimum Size Subarray Sum

 Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.

 click to show more practice.

 More practice:
 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class M_Leetcode_209 {
    /**
     * @param s
     * @param nums
     * @return
     *
     * sliding window
     * same as leetcode 76- Minimum Window Substring
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int j = 0;
        int k = 0;
        int res = Integer.MAX_VALUE;
        while(j < nums.length){
            k += nums[j++];
            while(k >= s) {
                res = Math.min(res, j - i);
                k -= nums[i++];
            }
        }

        if(res == Integer.MAX_VALUE) return 0;
        return res;
    }

    /**
     * @param s
     * @param nums
     * @return
     *
     * binary search way
     * traverse array, assume each index as the right end of sub array, try to search the left end.
     * use prefix sum array to accelerate.
     *
     * Time - O(nlogn)
     * Space - O(n)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int res = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + nums[i - 1];
            if(sum[i] >= s){
                int l = 0;
                int r = i - 1;
                while(l <= r){
                    int m = (l + r) >>> 1;
                    int tmp = sum[i] - sum[m];
                    if(tmp >= s){
                        l = m + 1;
                    }else{
                        r = m - 1;
                    }
                }

                res = Math.min(res, i - l + 1);
            }
        }

        if(res == Integer.MAX_VALUE) return 0;
        return res;
    }
}
