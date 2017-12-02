package LeetCode;

/**
 62. Unique Paths

 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 */
public class L_Leetcode_62 {
    /**
     * @param m
     * @param n
     * @return
     *
     * simple dp. dp[i][j] = dp[i-1][j] + dp[i][j-1];
     * since we only need to maintain the last row, so 1-dimensional array is fine
     *
     * Time - O(m * n)
     * Space - O(n)
     */
    public int uniquePaths(int m, int n) {
        int[] row = new int[n];
        row[0] = 1;
        for(int i = 0; i < m; i++){
            int[] new_row = new int[n];
            new_row[0] = row[0];
            for(int j = 1; j < n; j++){
                new_row[j] = new_row[j - 1] + row[j];
            }
            row = new_row;
        }

        return row[n - 1];
    }
}
