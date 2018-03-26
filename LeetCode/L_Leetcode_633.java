package LeetCode;

/**
 633. Sum of Square Numbers

 Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

 Example 1:
 Input: 5
 Output: True
 Explanation: 1 * 1 + 2 * 2 = 5
 Example 2:
 Input: 3
 Output: False
 */
public class L_Leetcode_633 {
    public boolean judgeSquareSum(int c) {
        for(int i = 0; i * i <= c / 2; i++){
            int remain = c - i * i;
            int sqrt = (int) Math.sqrt(remain);
            if(sqrt * sqrt == remain) return true;
        }
        return false;
    }
}
