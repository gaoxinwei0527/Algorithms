package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 594. Longest Harmonious Subsequence

 We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

 Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

 Example 1:
 Input: [1,3,2,2,5,2,3,7]
 Output: 5
 Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 Note: The length of the input array will not exceed 20,000.
 */
public class L_Leetcode_594 {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if(map.containsKey(i + 1)) res = Math.max(res, map.get(i) + map.get(i + 1));
            if(map.containsKey(i - 1)) res = Math.max(res, map.get(i) + map.get(i - 1));
        }

        return res;
    }
}
