package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 560. Subarray Sum Equals K

 Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2
 Note:
 The length of the array is in range [1, 20,000].
 The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class L_Leetcode_560 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * construct prefix sum array
     * then convert the problem to 2-sum problem.
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i <= n; i++){
            int target = sum[i] - k;
            res += map.getOrDefault(target, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }

        return res;
    }
}
