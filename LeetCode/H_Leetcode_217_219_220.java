package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class H_Leetcode_217_219_220 {
    /**
     217. Contains Duplicate

     Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
     */

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

    /**
     219. Contains Duplicate II
     Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     */

    /**
     * @param nums
     * @return
     *
     * hash set on window with k size
     * time - O(n)
     * Space - O(k)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.size() <= k && set.contains(nums[i])) return true;
            set.add(nums[i]);
            if(set.size() > k) set.remove(nums[i - k]);
        }
        return false;
    }

    /**
     220. Contains Duplicate III

     Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
     */

    /**
     * @param nums
     * @param k
     * @param t
     * @return
     *
     * use tree set (binary search tree) to maintain a sorted set for window of size k.
     * for each i, if ceiling(nums[i] - t) <= nums[i] || floor(nums[i] + t) >= nums[i], return true;
     *
     * Time - O(nlogk), binary search tree insert / delete require O(logk) time
     * Space - O(k)
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>(); // use Long to avoid overflow
        for(int i = 0; i < nums.length; i++){
            Long low = set.ceiling((long)nums[i] - t);
            if(low != null && low <= nums[i]) return true;

            Long high = set.floor((long)nums[i] + t);
            if(high != null && high >= nums[i]) return true;

            set.add((long)nums[i]);
            if(set.size() > k) set.remove((long)nums[i - k]);
        }
        return false;
    }
}
