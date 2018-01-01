package LeetCode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 407. Trapping Rain Water II

 Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

 Note:
 Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 Example:

 Given the following 3x6 height map:
 [
 [1,4,3,1,3,2],
 [3,2,1,3,2,4],
 [2,3,3,2,3,1]
 ]

 Return 4.

 The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.


 After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 */
public class H_Leetcode_407 {
    /**
     * @param heightMap
     * @return
     *
     *
    Use priority queue-
    Maintain priority queue q, when start each iteration, q should store all the boundary walls that defending the water. initialized as all the boundary points of the matrix.

    W W W W
    W A A W
    W A X Y
    W W W W

    we can find the lowest block in the wall as Y (木桶原理), and assume the current water level is height[Y];
    Then we can do bfs traverse from Y until we hit some cell with height > height[Y] or is already visited. Let's say we find unvisted neighbor X-
    1. if height[X] > height[Y], means X will be next wall when Y is removed / overwhelmed. So we can just put X to q for future process.
    2. else, means X can trap some water, because X is surrounded by walls with lowest height as height[Y], which is higher than height[X].
    (explain - if there is Z with lower height and surround X, then X should already be visited when we process Z / water level is height[Z]).
    Then we can update result-
    res += (height[Y] - height[X]) --> the number of water that X can trap.
    And we should keep bfs traverse all X's neighbors, because now water is at level height[Y], X is already overwhelmed, so water would keep flowing until it hit some cell higher than Y.
     */
    public int trapRainWater(int[][] heightMap) {
        if(heightMap.length == 0 || heightMap[0].length == 0) return 0;
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> heightMap[a[0]][a[1]]));
        Set<Integer> visited = new HashSet<>();

        for(int i = 0; i < m; i++){
            int[] a = new int[]{i, 0};
            int[] b = new int[]{i, n - 1};
            q.offer(a);
            q.offer(b);
            visited.add(i * n);
            visited.add(i * n + n - 1);
        }

        for(int i = 1; i < n - 1; i++){
            int[] a = new int[]{0, i};
            int[] b = new int[]{m - 1, i};
            q.offer(a);
            q.offer(b);
            visited.add(i);
            visited.add((m - 1) * n + i);
        }

        int res = 0;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while(!q.isEmpty()){
            int level = heightMap[q.peek()[0]][q.peek()[1]];
            while((!q.isEmpty()) && (heightMap[q.peek()[0]][q.peek()[1]] <= level)){
                int[] next = q.poll();
                res += (level - heightMap[next[0]][next[1]]);
                for (int[] aDirection : direction) {
                    int a = next[0] + aDirection[0];
                    int b = next[1] + aDirection[1];
                    int[] neighbor = new int[]{a, b};
                    if (a >= 0 && a < m && b >= 0 && b < n && !visited.contains(a * n + b)) {
                        q.offer(neighbor);
                        visited.add(a * n + b);
                    }
                }
            }
        }

        return res;
    }
}
