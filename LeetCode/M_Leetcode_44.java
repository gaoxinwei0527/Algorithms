package LeetCode;

/**
 44. Wildcard Matching

 Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 */
public class M_Leetcode_44 {
    /**
     * @param s
     * @param p
     * @return
     *
     * three cases-
     * 1. p[i - 1] is not '*', then dp[i][j] = j > 0 && dp[i - 1][j - 1] && (a[i - 1] == '?' || a[i - 1] == b[j - 1]);
     * 2. p[i - 1] is '*' and it matches empty, then dp[i][j] = dp[i - 1][j]
     * 3. p[i - 1] is '*' and it matches something, then dp[i][j] = (j > 0 && dp[i][j - 1])
     *
     * Time - O(m * n)
     * Space - O(m * n)
     */
    public boolean isMatch(String s, String p) {
        char[] a = p.toCharArray();
        char[] b = s.toCharArray();
        int m = a.length;
        int n = b.length;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 1; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(a[i - 1] != '*'){
                    dp[i][j] = j > 0 && dp[i - 1][j - 1] && (a[i - 1] == '?' || a[i - 1] == b[j - 1]);
                }else{
                    dp[i][j] = dp[i - 1][j] || (j > 0 && dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
