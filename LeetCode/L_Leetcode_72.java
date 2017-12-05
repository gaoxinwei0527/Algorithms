package LeetCode;

/**
 72. Edit Distance

 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character
 */
public class L_Leetcode_72 {
    /**
     * @param word1
     * @param word2
     * @return
     *
     * dp[i][j] means the min ops needed to sync first i chars in word1 and first j chars in word2
     *
     * Time - O(m * n)
     * Space - O(m * n), can be optimized to one dimension
     */
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int m = w1.length;
        int n = w2.length;
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i <= n; i++){
            dp[0][i] = i;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                if(w1[i - 1] == w2[j - 1]){
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }else{
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
