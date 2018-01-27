package LeetCode;

/**
 583. Delete Operation for Two Strings

 Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

 Example 1:
 Input: "sea", "eat"
 Output: 2
 Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 Note:
 The length of given words won't exceed 500.
 Characters in given words can only be lower-case letters.
 */
public class M_Leetcode_583 {
    /**
     * @param word1
     * @param word2
     * @return
     *
     * find LCS length, return m + n - 2 * lcs_len
     */
    public int minDistance(String word1, String word2) {
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();

        int m = arr1.length;
        int n = arr2.length;
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if(arr1[i - 1] == arr2[j - 1]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }

        return m + n - 2 * dp[m][n];
    }
}
