package LeetCode;

/**
 487. Max Consecutive Ones II

 Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

 Example 1:
 Input: [1,0,1,1,0]
 Output: 4
 Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 After flipping, the maximum number of consecutive 1s is 4.
 Note:

 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000
 Follow up:
 What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */
public class M_Leetcode_487 {
    /**
     * @param nums
     * @return
     *
     * this question can be generalized to at most flip k times
     * we only need to record previous k + 1 zero positions. first one is left bound, other k positions can be fliped.
     * whenever we encounter another 0, we shift the positions.
     *
     * Time - O(n)
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int z1 = -1;
        int z2 = -1;

        int res = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                res = Math.max(res, i - z1 - 1);
                z1 = z2;
                z2 = i;
            }
        }

        res = Math.max(res, nums.length - z1 - 1);
        return res;
    }
}
