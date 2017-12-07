package LeetCode;

/**
 81. Search in Rotated Sorted Array II

 Follow up for "Search in Rotated Sorted Array":
 What if duplicates are allowed?

 Would this affect the run-time complexity? How and why?
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Write a function to determine if a given target is in the array.

 The array may contain duplicates.
 */
public class H_Leetcode_81 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * if there is no duplicates, then every time we need to first decide which half is in order, there are only 2 cases-
     * 1. nums[m] <= nums[r], [m, r] is in order, then we can decide if target is in [m, r] or not
     * 2. nums[m] >= nums[l], [l, m] is in order, then we can decide if target is in [l, r] or not
     *
     * now there are duplicates, the only difference is, nums[l] == nums[r] is possible.
     * so before each binary iteration, move l until nums[l] != nums[r], then do binary search.
     *
     * Time - O(n) worst, in the case nums[] has all same elements
     * Space - O(1)
     */
    public boolean search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            while(l <= r && nums[l] == nums[r]) l++;
            if(l > r) return nums[r] == target;

            int m = l + (r - l) / 2;
            if(nums[m] == target) return true;
            if(nums[m] >= nums[l]){
                if(nums[m] > target && target >= nums[l]){
                    r = m - 1;
                }else{
                    l = m + 1;
                }
            }else if(nums[m] <= nums[r]){
                if(nums[m] < target && target <= nums[r]){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
        }

        return false;
    }
}
