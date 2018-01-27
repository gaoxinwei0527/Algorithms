package LeetCode;

/**
 63. Unique Paths II

 Follow up for "Unique Paths":

 Now consider if some obstacles are added to the grids. How many unique paths would there be?

 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 For example,
 There is one obstacle in the middle of a 3x3 grid as illustrated below.

 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 The total number of unique paths is 2.

 Note: m and n will be at most 100.
 */
public class L_Leetcode_63 {
    /**
     * @param obstacleGrid
     * @return
     *
     * same as Unique Paths I, just set dp[i][j] to 0 if obstacleGrid[i][j] == 1
     * reused obstacleGrid to save space
     *
     * Time - O(m * n)
     * Space - O(1)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                }else{
                    if(i == 0 && j == 0){
                        obstacleGrid[i][j] = 1;
                    }else if(i == 0){
                        obstacleGrid[i][j] += obstacleGrid[i][j - 1];
                    }else if(j == 0){
                        obstacleGrid[i][j] += obstacleGrid[i - 1][j];
                    }else{
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                    }
                }
            }
        }

        return obstacleGrid[m - 1][n - 1];
    }

    /**
     * @param obstacleGrid
     * @return
     *
     * record last row, so no need to modify original matrix.
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 1;
        if(obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] row = new int[n];
        row[0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacleGrid[i][j] == 1) row[j] = 0;
                else if(j > 0) row[j] += row[j - 1];
            }
        }

        return row[n - 1];
    }
}
