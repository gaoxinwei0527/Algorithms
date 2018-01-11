package LeetCode;

/**
 258. Add Digits

 Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

 For example:

 Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

 Follow up:
 Could you do it without any loop/recursion in O(1) runtime?

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class M_Leetcode_258 {
    /**
     * @param num
     * @return
     *
     * calculate digital root, if %9 != 0, return %9, otherwise return 9.
     */
    public int addDigits(int num) {
        if(num < 10) return num;
        if(num % 9 == 0) return 9;
        return num % 9;
    }
}
