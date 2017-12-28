package LeetCode;

/**
 172. Factorial Trailing Zeroes

 Given an integer n, return the number of trailing zeroes in n!.

 Note: Your solution should be in logarithmic time complexity.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class M_Leetcode_172 {
    /**
     * @param n
     * @return
     *
     * to get a trailing zero, we need one pair of 2 & 5; So this question is actually asking how many 2 & 5 pairs are there.
     * since 2 is always more than 5, so we can just focus on how many 5 we have. The pattern-
     * 1. every 5 nums, we get a 5
     * 2. every 25 nums, we get an extra 5
     * 3. every 125 nums, we get an extra 5.
     * ........
     */
    public int trailingZeroes(int n) {
        int res = 0;
        while(n > 0){
            n /= 5;
            res += n;
        }
        return res;
    }
}
