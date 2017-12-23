package LeetCode;

/**
 153. Find Minimum in Rotated Sorted Array

 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 You may assume no duplicate exists in the array.
 */
public class L_Leetcode_153 {
    /**
     * @param nums
     * @return
     *
     * binary search
     * Time - O(logN)
     * Space - O(1)
     */
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(nums[m] > nums[r]){
                l = m + 1;
            }else if(nums[m] < nums[l]){
                r = m;
            }else{
                return nums[l];
            }
        }
        return nums[l];
    }
}
