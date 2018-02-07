package LeetCode;

/**
 645. Set Mismatch

 The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

 Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

 Example 1:
 Input: nums = [1,2,2,4]
 Output: [2,3]
 Note:
 The given array size will in the range [2, 10000].
 The given array's numbers won't have any order.
 */
public class L_Leetcode_645 {
    /**
     * @param nums
     * @return
     *
     * use nums as indexes, set visited indexes to negative
     * dup = index visited twice
     * miss = index not visited
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int dup = -1;
        int miss = -1;
        for(int i = 0; i < n; i++){
            int k = (nums[i] > 0 ? nums[i] : -nums[i]);
            if(nums[k - 1] < 0) dup = k;
            else nums[k - 1] = -nums[k - 1];
        }

        for(int i = 0; i < n; i++){
            if(nums[i] > 0) miss = i + 1;
        }

        return new int[]{dup, miss};
    }
}
