package LeetCode;

/**
 132. Palindrome Partitioning II

 Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class H_Leetcode_132 {
    /**
     * @param s
     * @return
     *
     * dp[i] means min cut num for s[0 - i];
     * palin[i][j] means if s[i - j] is palindrome.
     *
     * so for each i, dp[i] is initialized as dp[i - 1] + 1;
     * then for each j in [0, i - 1], if s[j] == s[i] && palin[j + 1][i - 1], then dp[i] = Math.min(dp[i], dp[j - 1] + 1);
     *
     * Time - O(n ^ 2)
     * Space - O(n ^ 2)
     */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] palin = new boolean[n][n];
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            palin[i][i] = true;
        }

        for(int i = 1; i < n; i++){
            dp[i] = dp[i - 1] + 1;
            for(int j = i - 1; j >= 0; j--){
                if(s.charAt(i) == s.charAt(j) && (i - j == 1 || palin[j + 1][i - 1])){
                    palin[j][i] = true;
                    int m = (j == 0 ? 0 : dp[j - 1] + 1);
                    dp[i] = Math.min(dp[i], m);
                }
            }
        }

        return dp[n - 1];
    }
}
