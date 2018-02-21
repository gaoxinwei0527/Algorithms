package LeetCode;

/**
 664. Strange Printer

 There is a strange printer with the following two special requirements:

 The printer can only print a sequence of the same character each time.
 At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

 Example 1:
 Input: "aaabbb"
 Output: 2
 Explanation: Print "aaa" first and then print "bbb".
 Example 2:
 Input: "aba"
 Output: 2
 Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 Hint: Length of the given string will not exceed 100.
 */
public class S_Leetcode_664 {
    /**
     * @param s
     * @return
     *
     * dp[i][j] means min ops needed to print s[i, j];
     * foreach k in [i + 1, j],
     *   local[i][j] = dp[i][k - 1] + dp[k][j]  //like split s[i, j] at k and print separately.
     *   if(s[k - 1] == s[j]) local[i][j]--; // means when connect s[i, k - 1] and s[k, j], if s[k - 1] == s[j], we can save one op
     */
    public int strangePrinter(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if(n <= 1) return n;
        int[][] dp = new int[n][n];
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                if(i == j) dp[i][j] = 1;
                else{
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                    for(int k = i + 1; k <= j; k++){
                        int tmp = dp[i][k - 1] + dp[k][j];
                        if(s.charAt(k - 1) == s.charAt(j)) tmp--;
                        dp[i][j] = Math.min(dp[i][j], tmp);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    //TODO, use remove box way, like print 'aba' can converted to-
    // 1. remove 'b'
    // 2. then remove 'aa'
}
