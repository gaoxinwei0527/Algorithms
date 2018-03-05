package LeetCode;

/**
 678. Valid Parenthesis String

 Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

 Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 Any right parenthesis ')' must have a corresponding left parenthesis '('.
 Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 An empty string is also valid.
 Example 1:
 Input: "()"
 Output: True
 Example 2:
 Input: "(*)"
 Output: True
 Example 3:
 Input: "(*))"
 Output: True
 Note:
 The string size will be in the range [1, 100].
 */
public class H_Leetcode_678 {
    /**
     * @param s
     * @return
     *
     * T(i, j) means if s[i, j] is valid parenthesis
     *
     * recurrence-
     * if(s[j] == '*' && T(i, j - 1))
     *   T(i, j) = true; // s[j] not used
     * else
     *   foreach k in [i, j - 1]
     *     if(match(s[k], s[j]) && T(i, k - 1) && T(k + 1, j - 1))
     *       T(i, j) = true; // s[j] used
     *
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        if(n == 0) return true;
        boolean[][] dp = new boolean[n][n];
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                char c = s.charAt(j);
                if(c == '*' && (i > j - 1 || dp[i][j - 1])) dp[i][j] = true;
                else{
                    for(int k = j - 1; k >= i; k--){
                        if(match(s.charAt(k), c) && (i > k - 1 || dp[i][k - 1]) && (k + 1 > j - 1 || dp[k + 1][j - 1])){
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    private boolean match(char a, char b){
        return (a == '(' && b == ')') ||
                (a == '*' && b == ')') ||
                (a == '(' && b == '*');
    }
}
