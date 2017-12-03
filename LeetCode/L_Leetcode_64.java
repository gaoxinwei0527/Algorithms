package LeetCode;

/**
 64. Minimum Path Sum

 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.

 Example 1:
 [[1,3,1],
 [1,5,1],
 [4,2,1]]
 Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
 */
public class L_Leetcode_64 {
    /**
     * @param grid
     * @return
     *
     * simple dp
     *
     * Time - O(m * n)
     * Space - O(1)
     */
    public int minPathSum(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) continue;
                if(i == 0) {
                    grid[i][j] += grid[i][j - 1];
                    continue;
                }

                if(j == 0){
                    grid[i][j] += grid[i - 1][j];
                    continue;
                }

                grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }

        return grid[m - 1][n - 1];
    }
}
