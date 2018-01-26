package LeetCode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class H_Leetcode_42_407 {
    /**
     42. Trapping Rain Water

     Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

     For example,
     Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     */

    /**
     * @param height
     * @return
     *
     * For index i, the capacity[i] is depending on the highest left and right elevation. So do two iterations to get all left_max and right_max for every index.
     * Then iterate the array, for index i, if Math.min(left_max, right_max) > height[i], then res can add the capacity, which is Math.min(left_max, right_max) - height[i];
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int trap(int[] height) {
        if(height == null || height.length < 3){
            return 0;
        }

        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        int l_max = height[0];
        int r_max = height[height.length-1];

        for(int i = 1; i<height.length; i++){
            left_max[i] = l_max;
            l_max = Math.max(height[i], l_max);
        }

        for(int j = height.length - 2; j >= 0; j--){
            right_max[j] = r_max;
            r_max = Math.max( height[j], r_max);
        }

        int res = 0;
        for(int k = 1; k < height.length - 1; k++){
            int min = Math.min(left_max[k], right_max[k]);
            if(min > height[k]){
                res += (min - height[k]);
            }
        }

        return res;
    }

    /**
     * @param height
     * @return
     *
     * 木桶原理
     * This is a way inspired by trap rain water II, only need one pass traverse.
     * in trap rain water II, we use priority queue to store current defending walls to get the shortest wall.
     * in this case, it's doing the same thing, but we don't need a queue, because it's 2-D, so we would always only have 2 walls.
     *
     * initially, the walls l & r would be index 0 & n - 1.
     * we detect the shortest wall (bottleneck) by compare l and r, whichever shorter moves.
     *
     * E.g. l < r, we move l, but before we really moves l, we need to process all l's shorter neighbors. This is exactly same as trap water II.
     * Just like the water level is at l's height, then l's shorter neighbors will be overwhelmed, if l's neighbor is higher, then it will become a new wall.
     *
     * say for index in [l + 1, l + k], they all shorter then l, then we do res += (h[l] - h[l + 1]), res += (h[l] - h[l + 2])..etc, cause l is shortest outer wall now.
     * all other walls in queue (only r in this case) are higher than l.
     */
    public int trap2(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int res = 0;
        while(r - l > 1){ // if l & r are adjacent, then they could not trap any more water
            if(height[l] > height[r]){
                int k = r - 1;
                while(height[k] < height[r]){
                    res += (height[r] - height[k]);
                    k--;
                }
                r = k;
            }else{
                int k = l + 1;
                while(height[k] < height[l]){
                    res += (height[l] - height[k]);
                    k++;
                }
                l = k;
            }
        }
        return res;
    }

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
