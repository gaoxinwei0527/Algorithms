package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 695. Max Area of Island

 Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

 Example 1:
 [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 Example 2:
 [[0,0,0,0,0,0,0,0]]
 Given the above grid, return 0.
 Note: The length of each dimension in the given grid does not exceed 50.
 */
public class L_Leetcode_695 {
    /**
     * @param grid
     * @return
     *
     * bfs
     */
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    int count = 1;
                    grid[i][j] = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    while(!q.isEmpty()){
                        int[] next = q.poll();
                        for(int[] d : dir){
                            int x = next[0] + d[0];
                            int y = next[1] + d[1];
                            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                                grid[x][y] = 0;
                                q.offer(new int[]{x, y});
                                count++;
                            }
                        }
                    }

                    res = Math.max(res, count);
                }
            }
        }

        return res;
    }
}
