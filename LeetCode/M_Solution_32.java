package LeetCode;

/**
 32. Longest Valid Parentheses

 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class M_Solution_32 {
    /**
     * @param s
     * @return
     *
     * dp[i] means the length of longest valid parentheses end with ith char in s.
     * if(s[i - 1] == '(') dp[i] = 0;
     * if(s[i - 1] == ')') there are two cases-
     *  1. if s[i - 2] == '(', dp[i] = dp[i - 2] + 2;
     *  2. if s[i - 2] == ')' && s[i - 2 - dp[i - 1]] == '(', means there is one pair of parentheses wrapping the dp[i - 1] sequence, then
     *     dp[i] = dp[i - 1] + dp[i - 2 - dp[i - 1]] + 2;
     *     We need to add dp[i - 2 - dp[i - 1]] because e.g. '()(())', for the last ')', the longest valid parentheses is '()(())' instead of '(())';
     *
     * by validate parentheses with ending ')', we can count '(' as -1 and ')' as 1, whenever the count reach 0, means the substring is valid parentheses.
     *
     * Time - O(n ^ 2)
     * Space - O(n)
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int max = 0;
        char[] arr = s.toCharArray();
        int[] dp = new int[n + 1];
        for(int i = 2; i <= n; i++){
            if(arr[i - 1] == ')'){
                if(arr[i - 2] == '('){
                    dp[i] = dp[i - 2] + 2;
                }else if(i - 2 - dp[i - 1] >= 0 && arr[i - 2 - dp[i - 1]] == '('){
                    dp[i] = dp[i - 1] + dp[i - 2 - dp[i - 1]] + 2;
                }

                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }
}
