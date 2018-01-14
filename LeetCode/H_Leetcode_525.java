package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 525. Contiguous Array

 Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

 Example 1:
 Input: [0,1]
 Output: 2
 Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 Example 2:
 Input: [0,1,0]
 Output: 2
 Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 Note: The length of the given binary array will not exceed 50,000.
 */
public class H_Leetcode_525 {
    /**
     * @param nums
     * @return
     *
     * elegant way. change all 0s in nums to -1.
     * then what we need to do is find a range, whose sum is 0.
     *
     * with prefix sum array, this problem becomes a two sum problem.
     *
     * Tip - with prefix sum array, to find a fixed value of range sum is always two sum problem,
     * which can be accelerated by hash map. For this problem, we were initially trying to find a range sum value
     * which is depending on the length of range. We should try to find a way to convert target sum value to a fixed value.
     * In this problem, we converted all 0 to -1.
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(nums[i] == 0) nums[i] = -1;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int res = 0;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(map.containsKey(sum)) res = Math.max(res, i - map.get(sum));
            if(!map.containsKey(sum)) map.put(sum, i);
        }

        return res;
    }
}
