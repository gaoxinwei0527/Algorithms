package LeetCode;

/**
 660. Remove 9

 Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

 So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

 Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

 Example 1:
 Input: 9
 Output: 10
 Hint: n will not exceed 9 x 10^8.
 */
public class M_Leetcode_660 {
    /**
     * @param n
     * @return
     *
     * radix problem, to remove all 9s, means we only have 9 instead of 10 buckets for each num-
     * 0, 1, 2, 3, 4, 5, 6, 7, 8
     *
     * Then the question is, input decimal num n, convert it to 9-based num
     */
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }
}
