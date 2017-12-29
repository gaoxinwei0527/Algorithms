package LeetCode;

/**
 191. Number of 1 Bits

 Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

 For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class L_Leetcode_191 {
    /**
     * @param n
     * @return
     *
     * n & (-n) could extract the right most 1 of n
     * n & 0x00000000ffffffffL would convert signed integer n to unsigned int (with long type)
     */
    public int hammingWeight(int n) {
        long x = ((long)n & 0x00000000ffffffffL);
        int res = 0;
        while(x > 0){
            x -= (x & (-x));
            res++;
        }
        return res;
    }
}
