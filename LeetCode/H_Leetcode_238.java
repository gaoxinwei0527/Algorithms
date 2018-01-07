package LeetCode;

/**
 238. Product of Array Except Self

 Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Solve it without division and in O(n).

 For example, given [1,2,3,4], return [24,12,8,6].

 Follow up:
 Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class H_Leetcode_238 {
    /**
     * @param nums
     * @return
     *
     * 2 passes. firstly from left to right, then from right to left.
     * Time - O(n)
     * Extra space - O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        if(nums.length <= 1) return res;

        int base = 1;
        for(int i = 0; i < nums.length; i++){
            res[i] = base;
            base *= nums[i];
        }

        base = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            res[i] *= base;
            base *= nums[i];
        }

        return res;
    }
}
