package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 325. Maximum Size Subarray Sum Equals k

 Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 Note:
 The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

 Example 2:
 Given nums = [-2, -1, 2, 1], k = 1,
 return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

 Follow Up:
 Can you do it in O(n) time?
 */
public class H_Leetcode_325 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * use prefix sum and hash map to record previous prefix sums to index mapping, and search for sum[i] - k.
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for(int i = 1; i <= nums.length; i++){
            sum[i] = sum[i - 1] + nums[i - 1];
            if(map.containsKey(sum[i] - k)){
                max = Math.max(i - map.get(sum[i] - k), max);
            }

            if(!map.containsKey(sum[i])) map.put(sum[i], i);
        }
        return max;
    }
}
