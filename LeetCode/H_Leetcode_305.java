package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 305. Number of Islands II

 A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example:

 Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

 0 0 0
 0 0 0
 0 0 0
 Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

 1 0 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

 1 1 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

 1 1 0
 0 0 1   Number of islands = 2
 0 0 0
 Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

 1 1 0
 0 0 1   Number of islands = 3
 0 1 0
 We return the result as an array: [1, 1, 2, 3]

 Challenge:

 Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class H_Leetcode_305 {
    /**
     * @param m
     * @param n
     * @param positions
     * @return
     *
     * union-find way. record each processed positions. for current position, initialize root to itself
     * then check root of its processed neighbors (4 directions), union all their roots to single point
     *
     * Time - O(k * log(m * n))
     * Space - O(m * n)
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] root = new int[m * n];
        int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for(int i = 0; i < positions.length; i++){
            int a = positions[i][0];
            int b = positions[i][1];
            int cur = a * n + b;
            root[cur] = cur;
            count++;
            for(int k = 0; k < direction.length; k++){
                int x = a + direction[k][0];
                int y = b + direction[k][1];
                if(x >= 0 && x < m && y >= 0 && y < n && visited.contains(x * n + y)){
                    int neig = x * n + y;

                    // find root
                    while(root[neig] != neig){
                        neig = root[neig];
                    }

                    // union
                    if(root[neig] != root[cur]){
                        root[neig] = root[cur];
                        count--;
                    }
                }
            }
            visited.add(cur);
            res.add(count);
        }
        return res;
    }
}
