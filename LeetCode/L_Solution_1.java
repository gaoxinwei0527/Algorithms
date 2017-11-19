package LeetCode;

/*
1. Two Sum

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

import java.util.HashMap;
import java.util.Map;

public class L_Solution_1 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * Maintain value to index mapping for nums array.
     * For the index i that we are processing, if the map the contains key as (target - nums[i]), means we find the result-
     * {map.get(target - nums[i]), i}
     *
     * Time - O(n);
     * Space - O(n);
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(m.containsKey(target - nums[i])){
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }

        return null;
    }
}
