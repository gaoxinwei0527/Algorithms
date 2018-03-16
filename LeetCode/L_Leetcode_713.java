package LeetCode;

/**
 713. Subarray Product Less Than K

 Your are given an array of positive integers nums.

 Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

 Example 1:
 Input: nums = [10, 5, 2, 6], k = 100
 Output: 8
 Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 Note:

 0 < nums.length <= 50000.
 0 < nums[i] < 1000.
 0 <= k < 10^6.
 */
public class L_Leetcode_713 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * sliding window
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int prod = 1;
        int i = 0;
        int j = 0;
        int n = nums.length;
        while(i < n){
            if(nums[i] >= k){
                i++;
                j = i;
                continue;
            }
            while(j < n && prod * nums[j] < k) prod *= nums[j++];

            res += (j - i);
            prod /= nums[i++];
        }

        return res;
    }
}
