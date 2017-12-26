package LeetCode;

/**
 162. Find Peak Element

 A peak element is an element that is greater than its neighbors.

 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that num[-1] = num[n] = -∞.

 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 click to show spoilers.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class M_Leetcode_162 {
    /**
     * @param nums
     * @return
     *
     * binary search
     * basic idea is-
     * 1. if m is on upward direction, means there is peak on right side of m
     * 2. if m is on downward direction, means there is peak on left side of m
     * 3. if m is on peak, return m
     * 4. if m is on valley, means there is peak on both side, so we can move either way.
     *
     * Time - O(logN)
     * Space - O(1)
     */
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if((m == 0 || nums[m - 1] < nums[m]) && (m == nums.length - 1 || nums[m] > nums[m + 1])) return m;

            if(m == 0){
                l = m + 1;
            }else if(m == nums.length - 1){
                r = m - 1;
            }else if(nums[m - 1] < nums[m] && nums[m] < nums[m + 1]){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }

        return l;
    }
}
