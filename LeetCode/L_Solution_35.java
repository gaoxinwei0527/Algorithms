package LeetCode;

/**
 35. Search Insert Position

 Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 You may assume no duplicates in the array.

 Example 1:

 Input: [1,3,5,6], 5
 Output: 2
 Example 2:

 Input: [1,3,5,6], 2
 Output: 1
 Example 3:

 Input: [1,3,5,6], 7
 Output: 4
 Example 1:

 Input: [1,3,5,6], 0
 Output: 0
 */
public class L_Solution_35 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * binary search
     *
     * Time - O(logN)
     * Space - O(1)
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = l + (r - l) / 2;
            if(nums[m] == target) return m;
            if(nums[m] > target){
                r = m - 1;
            }else{
                l = m + 1;
            }
        }

        return l;
    }
}
