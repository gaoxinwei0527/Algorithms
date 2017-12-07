package LeetCode;

/**
 79. Word Search

 Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

 For example,
 Given board =

 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 */
public class L_Leetcode_79 {
    /**
     * @param board
     * @param word
     * @return
     *
     * dfs + backtrack
     */
    public boolean exist(char[][] board, String word) {
        if(board.length == 0 || board[0].length == 0) return false;
        if(word.length() == 0) return true;
        char[] arr = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == arr[0]){
                    board[i][j] = '.';
                    if(helper(board, arr, i, j, 1)) return true;
                    board[i][j] = arr[0];
                }
            }
        }

        return false;
    }

    private boolean helper(char[][] board, char[] arr, int i, int j, int k){
        if(k == arr.length) return true;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for(int a = 0; a < direction.length; a++){
            int x = i + direction[a][0];
            int y = j + direction[a][1];
            if(x >= 0 && x < board.length && y >= 0 && y < board[x].length && board[x][y] == arr[k]){
                char tmp = board[x][y];
                board[x][y] = '.';
                if(helper(board, arr, x, y, k + 1)) return true;
                board[x][y] = tmp;
            }
        }

        return false;
    }
}
