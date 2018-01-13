package LeetCode;

import java.util.*;

/**
 505. The Maze II

 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 Output: 12
 Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

 Example 2

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (3, 2)

 Output: -1
 Explanation: There is no way for the ball to stop at the destination.

 Note:
 There is only one ball and one destination in the maze.
 Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class H_Leetcode_505 {
    class Point{
        public int x;
        public int y;
        public int val;
        public Point(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    Map<Integer, Point> map = new HashMap<>();
    PriorityQueue<Point> q = new PriorityQueue<>((Point a, Point b) ->{
        return a.val - b.val;
    });

    Set<Integer> visited = new HashSet<>();
    int[][] direct = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * @param maze
     * @param start
     * @param dest
     * @return
     *
     * Dijkstra algorithm
     */
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int m = maze.length;
        int n = maze[0].length;
        Point s = new Point(start[0], start[1], 0);
        q.offer(s);
        map.put(start[0] * n + start[1], s);

        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.x == dest[0] && p.y == dest[1]) return p.val;
            visited.add(p.x * n + p.y);

            for(int k = 0; k < direct.length; k++){
                int a = p.x;
                int b = p.y;
                while(a + direct[k][0] >= 0 && a + direct[k][0] < m && b + direct[k][1] >= 0 && b + direct[k][1] < n && maze[a + direct[k][0]][b + direct[k][1]] == 0){
                    a += direct[k][0];
                    b += direct[k][1];
                }

                int index = a * n + b;
                if(!visited.contains(index)){
                    if(map.containsKey(index)){
                        Point tmp = map.get(index);
                        int new_val = p.val + Math.abs(a - p.x) + Math.abs(b - p.y);
                        if(tmp.val > new_val){
                            tmp.val = new_val;
                            q.remove(tmp);
                            q.offer(tmp);
                        }
                    }else{
                        Point new_p = new Point(a, b, p.val + Math.abs(a - p.x) + Math.abs(b - p.y));
                        map.put(index, new_p);
                        q.offer(new_p);
                    }
                }
            }
        }

        return -1;
    }
}
