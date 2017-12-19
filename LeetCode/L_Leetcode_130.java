package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 130. Surrounded Regions

 Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 For example,
 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X
 */
public class L_Leetcode_130 {
    /**
     * @param board
     *
     * bfs from all boundary 'O', and set them to '*', and set all remaining 'O' to 'X', then revert all '*' to 'O';
     *
     * time - O(m * n)
     * space - O(m * n)
     */
    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O') q.offer(new int[]{i, 0});
            if(board[i][n - 1] == 'O') q.offer(new int[]{i, n - 1});
        }

        for(int i = 0; i < n; i++){
            if(board[0][i] == 'O') q.offer(new int[]{0, i});
            if(board[m - 1][i] == 'O') q.offer(new int[]{m - 1, i});
        }

        while(!q.isEmpty()){
            int[] next = q.poll();
            int a = next[0];
            int b = next[1];
            if(board[a][b] == '*') continue;
            board[a][b] = '*';
            for (int[] aDirection : direction) {
                int x = a + aDirection[0];
                int y = b + aDirection[1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                    q.offer(new int[]{x, y});
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == '*') board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }
}
