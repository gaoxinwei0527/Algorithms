package LeetCode;

import java.util.*;

/**
 675. Cut Off Trees for Golf Event

 You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

 0 represents the obstacle can't be reached.
 1 represents the ground can be walked through.
 The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

 You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

 You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

 Example 1:
 Input:
 [
 [1,2,3],
 [0,0,4],
 [7,6,5]
 ]
 Output: 6
 Example 2:
 Input:
 [
 [1,2,3],
 [0,0,0],
 [7,6,5]
 ]
 Output: -1
 Example 3:
 Input:
 [
 [2,3,4],
 [0,0,5],
 [8,7,6]
 ]
 Output: 6
 Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 Hint: size of the given matrix will not exceed 50x50.
 */
public class M_Leetcode_675 {
    /**
     * @param forest
     * @return
     *
     * sort trees with height, then for each pair of trees, do bfs to find the steps.
     */
    public int cutOffTree(List<List<Integer>> forest) {
        if(forest.isEmpty() || forest.get(0).isEmpty()) return 0;
        int m = forest.size();
        int n = forest.get(0).size();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int k = forest.get(i).get(j);
                if(k != 0) {
                    q.offer(new int[]{i, j, k});
                }
            }
        }

        int step = 0;
        int[] cur = new int[]{0, 0, forest.get(0).get(0)};
        while(!q.isEmpty()){
            int[] next = q.poll();
            int next_step = bfs(forest, cur, next);
            if(next_step == -1) return -1;
            step += next_step;
            cur = next;
        }

        return step;
    }

    private int bfs(List<List<Integer>> forest, int[] cur, int[] next){
        int m = forest.size();
        int n = forest.get(0).size();
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[m][n];
        List<int[]> level = new ArrayList<>();
        level.add(cur);
        visited[cur[0]][cur[1]] = true;

        int count = 0;
        while(!level.isEmpty()){
            List<int[]> next_level = new ArrayList<>();
            for(int[] p : level){
                if(p[0] == next[0] && p[1] == next[1]) return count;
                for(int a = 0; a < direction.length; a++){
                    int x = p[0] + direction[a][0];
                    int y = p[1] + direction[a][1];
                    if(x >= 0 && x < m && y >= 0 && y < n && forest.get(x).get(y) != 0 && !visited[x][y]){
                        next_level.add(new int[]{x, y, forest.get(x).get(y)});
                        visited[x][y] = true;
                    }
                }
            }
            count++;
            level = next_level;
        }

        return -1;
    }
}
