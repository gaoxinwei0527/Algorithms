package LeetCode;

import java.util.*;

/**
 694. Number of Distinct Islands

 Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

 Example 1:
 11000
 11000
 00011
 00011
 Given the above grid map, return 1.
 Example 2:
 11011
 10000
 00001
 11011
 Given the above grid map, return 3.

 Notice that:
 11
 1
 and
 1
 11
 are considered different island shapes, because we do not consider reflection / rotation.
 Note: The length of each dimension in the given grid does not exceed 50.
 */
public class M_Leetcode_694 {
    /**
     * @param grid
     * @return
     *
     * need find a way to serialize the shape, i used string here
     * //TODO Set<Set<Integer>> should also work
     */
    public int numDistinctIslands(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int count = 0;
        Set<String> island = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    List<int[]> nodes = new ArrayList<>();
                    nodes.add(new int[]{0, 0});

                    while(!q.isEmpty()){
                        int[] next = q.poll();
                        for(int[] d : dir){
                            int x = next[0] + d[0];
                            int y = next[1] + d[1];
                            if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                                grid[x][y] = 0;
                                q.offer(new int[]{x, y});
                                nodes.add(new int[]{x - i, y - j});
                            }
                        }
                    }

                    nodes.sort((int[] a, int[] b) -> {
                        if (a[0] != b[0]) return a[0] - b[0];
                        return a[1] - b[1];
                    });

                    StringBuilder sb = new StringBuilder();
                    for(int[] node : nodes){
                        sb.append("[").append(node[0]).append(",").append(node[1]).append("]");
                    }

                    String key = sb.toString();
                    if(!island.contains(key)){
                        count++;
                        island.add(key);
                    }
                }
            }
        }

        return count;
    }
}
