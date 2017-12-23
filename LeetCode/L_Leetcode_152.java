package LeetCode;

/**
 152. Maximum Product Subarray

 Find the contiguous subarray within an array (containing at least one number) which has the largest product.

 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.
 */
public class L_Leetcode_152 {
    /**
     * @param nums
     * @return
     *
     * when process each num i, try to find the max & min product end with i. (because there is negative nums, previous min product multiply negative num could become max).
     * Time - O(n)
     * Space - O(1)
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int max = nums[0];
        int cur_max = nums[0];
        int cur_min = nums[0];

        for(int i = 1; i < n; i++){
            int next_max = Math.max(Math.max(cur_max * nums[i], cur_min * nums[i]), nums[i]);
            int next_min = Math.min(Math.min(cur_max * nums[i], cur_min * nums[i]), nums[i]);
            max = Math.max(next_max, max);
            cur_max = next_max;
            cur_min = next_min;
        }

        return max;
    }
}
