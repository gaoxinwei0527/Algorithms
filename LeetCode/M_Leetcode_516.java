package LeetCode;

/**
 516. Longest Palindromic Subsequence

 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:

 "bbbab"
 Output:
 4
 One possible longest palindromic subsequence is "bbbb".
 Example 2:
 Input:

 "cbbd"
 Output:
 2
 One possible longest palindromic subsequence is "bb".
 */
public class M_Leetcode_516 {
    /**
     * @param s
     * @return
     *
     * dp[i][j] - len of longest palindrome subsequence of s[i, j]
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if(n <= 1) return n;

        int[][] dp = new int[n][n];
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                if(i == j) dp[i][j] = 1;
                else{
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }else{
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
