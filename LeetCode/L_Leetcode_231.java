package LeetCode;

/**
 231. Power of Two

 Given an integer, write a function to determine if it is a power of two.
 */
public class L_Leetcode_231 {
    /**
     * @param n
     * @return
     *
     * n & (n - 1) == 0 means n is power of 2
     */
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}
