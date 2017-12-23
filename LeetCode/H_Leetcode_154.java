package LeetCode;

/**
 154. Find Minimum in Rotated Sorted Array II

 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 The array may contain duplicates.
 */
public class H_Leetcode_154 {
    /**
     * @param nums
     * @return
     *
     * same as leetcode 153, the only diff is, since nums[l] could be same as nums[r], increment l until nums[l] != nums[r].
     * also, eliminate duplicates on right side
     *
     * Time - O(n), in worst case, the whole array has same elements
     * Space - O(1)
     */
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            while(l <= r && nums[l] == nums[r]){
                l++;
            }
            if(l > r) return nums[r]; // all of remaining nums are same
            while(nums[r - 1] == nums[r]) r--;

            int m = (l + r) >>> 1;
            if(nums[m] > nums[r]){
                l = m + 1;
            }else if(nums[m] < nums[l]){
                r = m;
            }else{
                return nums[l]; // nums[l] <= nums[m] <= nums[r], then minimal must be nums[l]
            }
        }
        return nums[l];
    }
}
