package LeetCode;

/**
 730. Count Different Palindromic Subsequences

 Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

 A subsequence of a string S is obtained by deleting 0 or more characters from S.

 A sequence is palindromic if it is equal to the sequence reversed.

 Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

 Example 1:
 Input:
 S = 'bccb'
 Output: 6
 Explanation:
 The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 Note that 'bcb' is counted only once, even though it occurs twice.
 Example 2:
 Input:
 S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 Output: 104860361
 Explanation:
 There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 Note:

 The length of S will be in the range [1, 1000].
 Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 */
public class S_Leetcode_730 {
    /**
     * @param S
     * @return
     *
     * T(i, j) means num of different sub-seqs in S[i, j]
     *
     * recurrence-
     * if(S[i] != S[j]) T(i, j) = T(i + 1, j) + T(i, j - 1) + T(i + 1, j - 1);
     * else{
     *     x = i + 1; y = j - 1;
     *     while(x <= y && S[i] != S[x]) x++;
     *     while(x <= y && S[i] != S[y]) y--;
     *     if(x > y) T(i, j) = 2 * T(i + 1, j - 1) + 2 // 2 is for 'S[i]' and 'S[i]S[i]'
     *     else if(x == y) T(i, j) = 2 * T(i + 1, j - 1) + 1 // 1 is for 'S[i]S[i]'
     *     else T(i, j) = 2 * T(i + 1, j - 1) - T(x + 1, y - 1);
     * }
     */
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int mod = 1000000007;
        long[][] dp = new long[n][n];
        char[] arr = S.toCharArray();
        for(int len = 1; len <= n; len++){
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                if(len == 1) dp[i][j] = 1;
                else if(arr[i] != arr[j]) dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]) % mod;
                else{
                    int x = i + 1;
                    int y = j - 1;
                    while(x <= y && arr[x] != arr[i]) x++;
                    while(y >= x && arr[y] != arr[i]) y--;
                    if(x > y) dp[i][j] = (2 * dp[i + 1][j - 1] + 2) % mod;
                    else if(x == y) dp[i][j] = (2 * dp[i + 1][j - 1] + 1) % mod;
                    else {
                        long remain = (y - x >= 2 ? dp[x + 1][y - 1] : 0);
                        dp[i][j] = (2 * dp[i + 1][j - 1] - remain) % mod;
                    }
                }
            }
        }

        return (int) ((dp[0][n - 1] + mod) % mod); // +mod to prevent overflow.
    }
}
