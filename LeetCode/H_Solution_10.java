package LeetCode;

/**
 10. Regular Expression Matching

 Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 */
public class H_Solution_10 {
    /**
     * @param s
     * @param p
     * @return
     *
     * dp[i][j] means if S[0, i) and P[0, j) matches，there are 3 cases:
     * 1.  P[i][j] = P[i - 1][j - 1], if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.');
     * 2.  P[i][j] = P[i][j - 2], if p[j - 1] == '*' and the pattern repeats for 0 times;
     * 3.  P[i][j] = P[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'), if p[j - 1] == '*' and the pattern repeats for at least 1 times.
     *
     * Time - O(m * n)
     * Space - O(m * n)
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int i = 0; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') && dp[i-1][j-1]){
                    dp[i][j] = true;
                }

                if(p.charAt(j-1) == '*' && dp[i][j-2]){
                    dp[i][j] = true;
                }

                if(p.charAt(j-1) == '*' && i > 0 && dp[i-1][j] && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')){
                    dp[i][j] = true;
                }
            }
        }

        return dp[m][n];
    }

    /**
     * @param s
     * @param p
     * @return
     *
     * Recursive solution
     * Still 3 cases that s and p can match:
     * p[1] is ‘*’ and pattern repeats for 0 time. isMatch(s, p) = isMatch(s, p.substring(2));
     * p[1] is ‘*’ and p[0] matches s[0] (pattern repeats for multiple times). isMatch(s, p) = isMatch(s.substring(1), p);
     * p[1] is not ‘*’ and p[0] matches s[0]. isMatch(s, p) = isMatch(s.substring(1), p.substring(1));
     *
     */
    public boolean isMatchRecur(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p));
        } else {
            return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        }
    }
}
