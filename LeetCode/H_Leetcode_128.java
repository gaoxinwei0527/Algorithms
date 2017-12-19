package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 128. Longest Consecutive Sequence

 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.
 */
public class H_Leetcode_128 {
    /**
     * @param nums
     * @return
     *
     * use hash set to store all nums, then for each num, query for num-1, num-2, etc and num+1, num+2, etc.
     * Time - O(n)
     * Space - O(n)
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }

        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                int l = nums[i];
                int r = nums[i];
                while(set.contains(l - 1)){
                    l--;
                    set.remove(l);
                }

                while(set.contains(r + 1)){
                    r++;
                    set.remove(r);
                }
                set.remove(nums[i]);
                res = Math.max(res, r - l + 1);
            }
        }

        return res;
    }
}
