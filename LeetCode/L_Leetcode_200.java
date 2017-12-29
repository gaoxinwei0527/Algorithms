package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 200. Number of Islands

 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class L_Leetcode_200 {
    /**
     * @param grid
     * @return
     *
     * iterative bfs
     */
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int[][] direction = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;
                    Queue<Integer> q = new LinkedList<>();
                    Set<Integer> visited = new HashSet<>();
                    q.offer(i * n + j);
                    visited.add(i * n + j);
                    while(!q.isEmpty()){
                        int next = q.poll();
                        int x = next / n;
                        int y = next % n;
                        grid[x][y] = '0';
                        for(int k = 0; k < direction.length; k++){
                            int a = x + direction[k][0];
                            int b = y + direction[k][1];
                            if(a >= 0 && a < m && b >= 0 && b < n && (!visited.contains(a * n + b)) && grid[a][b] == '1'){
                                q.offer(a * n + b);
                                visited.add(a * n + b);
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    /**
     * @param grid
     * @return
     *
     * recursive bfs
     */
    public int numIslands2(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    res++;
                    helper(grid, i, j, m, n);
                }
            }
        }

        return res;
    }

    private void helper(char[][] grid, int i, int j, int m, int n){
        if(i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1'){
            grid[i][j] = '0';
            helper(grid, i + 1, j, m, n);
            helper(grid, i - 1, j, m, n);
            helper(grid, i, j + 1, m, n);
            helper(grid, i, j - 1, m, n);
        }
    }
}
