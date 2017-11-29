package LeetCode;

/**
 41. First Missing Positive

 Given an unsorted integer array, find the first missing positive integer.

 For example,
 Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.

 Your algorithm should run in O(n) time and uses constant space.
 */
public class M_Leetcode_41 {
    /**
     * @param nums
     * @return
     *
     * 1. make all non-positive value to INT_MAX
     * 2. for each nums[i], set nums[nums[i]] to negative if possible
     * 3. check first non-negtive value and return its index + 1
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0) nums[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < nums.length; i++){
            int k = nums[i] < 0 ? -nums[i] : nums[i];
            if(k > 0 && k <= nums.length){
                nums[k - 1] = -Math.abs(nums[k - 1]);
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= 0){
                return i + 1;
            }
        }

        return nums.length + 1;
    }
}
