package LeetCode;

/**
 7. Reverse Integer

 Given a 32-bit signed integer, reverse digits of an integer.

 Example 1:

 Input: 123
 Output:  321
 Example 2:

 Input: -123
 Output: -321
 Example 3:

 Input: 120
 Output: 21
 Note:
 Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range.
 For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class L_Solution_7 {
    /**
     * @param x
     * @return
     *
     * To avoid overflow, use long type for all inter-mediate results
     *
     * Time - O(1), because long / int type has fixed bit num
     * Space - O(1)
     */
    public int reverse(int x) {
        boolean neg = (x < 0);
        String reversed = new StringBuilder(Long.toString(Math.abs((long)x))).reverse().toString();
        long res = Long.parseLong(reversed);
        if((!neg && res > Integer.MAX_VALUE) || (neg && -res < Integer.MIN_VALUE)){
            return 0;
        }

        return (int)(neg ? (-res) : res);
    }
}
