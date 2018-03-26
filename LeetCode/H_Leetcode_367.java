package LeetCode;

/**
 367. Valid Perfect Square

 Given a positive integer num, write a function which returns True if num is a perfect square else False.

 Note: Do not use any built-in library function such as sqrt.

 Example 1:

 Input: 16
 Returns: True
 Example 2:

 Input: 14
 Returns: False
 Credits:
 Special thanks to @elmirap for adding this problem and creating all test cases.
 */
public class H_Leetcode_367 {
    /**
     * @param num
     * @return
     *
     * binary search
     */
    public boolean isPerfectSquare(int num) {
        long l = 1;
        long r = num;
        while(l <= r){
            long m = (l + r) >> 1;
            if(m * m == num) return true;
            if(m * m < num) l = m + 1;
            else r = m - 1;
        }

        return false;
    }
}
