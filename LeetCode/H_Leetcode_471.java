package LeetCode;

/**
 471. Encode String with Shortest Length

 Given a non-empty string, encode the string such that its encoded length is the shortest.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

 Note:
 k will be a positive integer and encoded string will not be empty or have extra space.
 You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
 If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 Example 1:

 Input: "aaa"
 Output: "aaa"
 Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 Example 2:

 Input: "aaaaa"
 Output: "5[a]"
 Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 Example 3:

 Input: "aaaaaaaaaa"
 Output: "10[a]"
 Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 Example 4:

 Input: "aabcaabcd"
 Output: "2[aabc]d"
 Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 Example 5:

 Input: "abbbabbbcabbbabbbc"
 Output: "2[2[abbb]c]"
 Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "
 */
public class H_Leetcode_471 {
    /**
     * @param s
     * @return
     *
     * subproblem S(i, j)-
     * means the shortest encoded string for s[i, j];
     *
     * initialize- S(i, j) = s.substring(i, j + 1)
     *
     * recurrence-
     * foreach x in [i, j)
     *     // if S(i, j) is concatenate of S(i, x) and S(x + 1, j)
     *     S(i, j) = min (S(i, x) + S(x + 1, j), S(i, j));
     *
     *     // if S(i, j) is repeat pattern of S(i, x)
     *     S(i, j) = min (repeat(S(i, x)), S(i, j));
     *
     * Original problem - S(0, n - 1);
     * bottom up - dp[i][j] stores the result for S(i, j)
     *
     * normally if we model subproblem as substring problem like S(i, j), then it most likely depends on following subproblems-
     * 1. S(i + 1, j)
     * 2. S(i, j - 1)
     * 3. S(i, k) + S(k + 1, j)
     *
     * And for problems, S(i, j) not just depends on results of subproblems, but also the original input[i, j],
     * it could also generate part of the local optimized result.
     */
    public String encode(String s) {
        int n = s.length();
        if(n <= 4) return s;
        String[][] dp = new String[n][n];
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);
                dp[i][j] = sub;
                if(len <= 4) continue;

                for(int k = i; k < j; k++){
                    String append = dp[i][k] + dp[k + 1][j];
                    if(dp[i][j].length() > append.length()) dp[i][j] = append;

                    int sub_len = k - i + 1;
                    String pattern = s.substring(i, k + 1);
                    if(len % sub_len == 0 && "".equals(sub.replaceAll(pattern, ""))){
                        String repeat = "" + (len / sub_len) + "[" + dp[i][k] + "]";
                        if(dp[i][j].length() > repeat.length()) dp[i][j] = repeat;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
