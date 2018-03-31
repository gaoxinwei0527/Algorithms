package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 733. Flood Fill

 An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

 Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

 To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

 At the end, return the modified image.

 Example 1:
 Input:
 image = [[1,1,1],[1,1,0],[1,0,1]]
 sr = 1, sc = 1, newColor = 2
 Output: [[2,2,2],[2,2,0],[2,0,1]]
 Explanation:
 From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 by a path of the same color as the starting pixel are colored with the new color.
 Note the bottom corner is not colored 2, because it is not 4-directionally connected
 to the starting pixel.
 Note:

 The length of image and image[0] will be in the range [1, 50].
 The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class L_Leetcode_733 {
    /**
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     *
     * normal bfs
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int prev = image[sr][sc];
        if(prev == newColor) return image;
        int m = image.length;
        int n = image[0].length;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        while(!q.isEmpty()){
            int[] next = q.poll();
            image[next[0]][next[1]] = -1;
            for(int[] d : dir){
                int x = next[0] + d[0];
                int y = next[1] + d[1];
                if(x >= 0 && x < m && y >= 0 && y < n && image[x][y] == prev){
                    q.offer(new int[]{x, y});
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(image[i][j] == -1) image[i][j] = newColor;
            }
        }

        return image;
    }
}
