package LeetCode;

/**
 201. Bitwise AND of Numbers Range

 Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

 For example, given the range [5, 7], you should return 4.

 Credits:
 Special thanks to @amrsaqr for adding this problem and creating all test cases.
 */
public class M_Leetcode_201 {
    /**
     * @param m
     * @param n
     * @return
     *
     * bit manipulation problem, principle is simple, if m < n, then at least there is one odd num and one even num in the range.
     * then right-most bit of result would be 0. we keep right shift m and n until m == n, then we can know how many bits on right side of result should be 0.
     * then we shift back the remaining m, means the 1s inside it would be retained in result.
     *
     * Time & Space are both O(1)
     */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        while(m < n){
            m >>>= 1;
            n >>>= 1;
            shift++;
        }
        return (m << shift);
    }
}
