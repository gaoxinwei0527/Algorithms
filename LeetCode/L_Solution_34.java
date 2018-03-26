package LeetCode;

/**
 34. Search for a Range

 Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 */
public class L_Solution_34 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * binary search for both left and right side
     *
     * Time - O(logN)
     * Space - O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = l + (r - l) / 2;
            if(nums[m] >= target){
                r = m - 1;
            }else{
                l = m + 1;
            }
        }

        int i = l;
        if(i > nums.length - 1 || nums[i] != target) return new int[]{-1, -1};

        l = 0;
        r = nums.length - 1;
        while(l <= r){
            int m = l + (r - l) / 2;
            if(nums[m] <= target){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }

        return new int[]{i, l - 1};
    }
}
