package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 697. Degree of an Array

 Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

 Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

 Example 1:
 Input: [1, 2, 2, 3, 1]
 Output: 2
 Explanation:
 The input array has a degree of 2 because both elements 1 and 2 appear twice.
 Of the subarrays that have the same degree:
 [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 The shortest length is 2. So return 2.
 Example 2:
 Input: [1,2,2,3,1,4,2]
 Output: 6
 Note:

 nums.length will be between 1 and 50,000.
 nums[i] will be an integer between 0 and 49,999.
 */
public class L_Leetcode_697 {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, int[]> range = new HashMap<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            max = Math.max(count.get(nums[i]), max);
            if(!range.containsKey(nums[i])){
                range.put(nums[i], new int[]{i, i});
            }else{
                range.get(nums[i])[1] = i;
            }
        }

        int res = nums.length;
        for(int i : count.keySet()){
            if(count.get(i) == max){
                res = Math.min(res, range.get(i)[1] - range.get(i)[0] + 1);
            }
        }

        return res;
    }
}
