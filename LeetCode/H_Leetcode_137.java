package LeetCode;

/**
 137. Single Number II

 Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class H_Leetcode_137 {
    /**
     * @param nums
     * @return
     *
     * get a bits[] array to count on each bit, for each bit, do %3 we should get the bit of result num.
     * then construct result num from the bits.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for(int num : nums){
            for(int i = 0; i < 32; i++){
                bits[i] += ((num >> i) & 1);
            }
        }

        int res = 0;
        for(int i = 0; i < 32; i++){
            res |= ((bits[i] % 3) << i);
        }
        return res;
    }
}
