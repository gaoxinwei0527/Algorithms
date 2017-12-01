package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 51. N-Queens

 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 For example,
 There exist two distinct solutions to the 4-queens puzzle:

 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 */
public class M_Leetcode_51 {
    /**
     * @param n
     * @return
     *
     * dfs + backtrack
     */
    public List<List<String>> solveNQueens(int n) {
        boolean[] col = new boolean[n];
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        helper(res, board, col, 0, n);
        return res;
    }

    private void helper(List<List<String>> res, char[][] board, boolean[] col, int i, int n){
        if(i == n){
            List<String> next = new ArrayList<>();
            for(int a = 0; a < board.length; a++){
                StringBuilder sb = new StringBuilder();
                for(int b = 0; b < board[a].length; b++){
                    sb.append(board[a][b]);
                }
                next.add(sb.toString());
            }
            res.add(next);
            return;
        }

        for(int j = 0; j < n; j++){
            if(!col[j]){
                boolean dig = false;
                int[][] direction = new int[][]{{-1, -1}, {-1, 1}};
                for(int k = 0; k < direction.length; k++){
                    int a = i + direction[k][0];
                    int b = j + direction[k][1];

                    while(a >= 0 && a < n && b >= 0 && b < n){
                        if(board[a][b] == 'Q'){
                            dig = true;
                            break;
                        }
                        a += direction[k][0];
                        b += direction[k][1];
                    }
                }

                if(dig) continue;
                board[i][j] = 'Q';
                col[j] = true;
                helper(res, board, col, i + 1, n);
                board[i][j] = '.';
                col[j] = false;
            }
        }
    }
}
