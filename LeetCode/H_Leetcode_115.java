package LeetCode;

/**
 115. Distinct Subsequences

 Given a string S and a string T, count the number of distinct subsequences of S which equals T.

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Here is an example:
 S = "rabbbit", T = "rabbit"

 Return 3.
 */
public class H_Leetcode_115 {
    /**
     * @param s
     * @param t
     * @return
     *
     * dp[i][j] - num of sub sequences from first i chars in s that same as first j chars in t
     * if(s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j] // s[i - 1] not used
     *                                                 + dp[i - 1][j - 1] // s[i - 1] matches t[j - 1]
     * else
     *    dp[i][j] = dp[i - 1][j]
     *
     * Time - O(m * n)
     * Space - O(m * n), can be optimized
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + (s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
            }
        }

        return dp[m][n];
    }
}
