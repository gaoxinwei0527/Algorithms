package LeetCode;

/**
 33. Search in Rotated Sorted Array

 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 */
public class M_Solution_33 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * Binary search, but need to decide the mid position.
     * For binary search, if we are doing exact matching, like find target in array, we can do while(l <= r);
     *
     * Time - O(logN)
     * Space - O(1)
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = l + (r - l) / 2;
            if(nums[m] == target) return m;
            if(nums[m] > target){
                if(nums[m] >= nums[l] && nums[l] > target){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }else{
                if(nums[m] <= nums[r] && nums[r] < target){
                    r = m - 1;
                }else{
                    l = m + 1;
                }
            }
        }

        return -1;
    }

    /**
     * @param nums
     * @param target
     * @return
     *
     * Firstly find the min value index in nums. Then do normal binary search with shifting
     *
     * Time - O(logN)
     * Space - O(1)
     */
    public int search2(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int m = l + (r - l) / 2;
            if(nums[m] > nums[r]){
                l = m + 1;
            }else{
                r = m;
            }
        }

        int realM = l;
        l = 0;
        r = nums.length - 1;
        while(l <= r){
            int m = l + (r - l) / 2;
            int real = (m + realM) % nums.length;
            if(nums[real] == target) return real;
            if(nums[real] < target) l = m + 1;
            else r = m - 1;
        }

        return -1;
    }
}
