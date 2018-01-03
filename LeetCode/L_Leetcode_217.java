package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 217. Contains Duplicate

 Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 */
public class L_Leetcode_217 {
    /**
     * @param nums
     * @return
     *
     * hash set, O(n)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            if(set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }

    /**
     * @param nums
     * @return
     *
     * sort, O(nlogn), but beats 97% on leetcode. LOL
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
