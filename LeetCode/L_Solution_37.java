package LeetCode;

/**
 37. Sudoku Solver

 Write a program to solve a Sudoku puzzle by filling the empty cells.

 Empty cells are indicated by the character '.'.

 You may assume that there will be only one unique solution.
 */
public class L_Solution_37 {
    /**
     * @param board
     *
     * dfs + backtrack
     */
    public void solveSudoku(char[][] board) {
        int n = board.length;
        helper(board, 0, n);
    }

    private boolean helper(char[][] board, int k, int n){
        while(k < n * n && board[k / n][k % n] != '.'){
            k++;
        }

        if(k == n * n) return true;
        int a = k / n;
        int b = k % n;
        for(int i = 1; i <= 9; i++){
            char c = (char)('0' + i);
            if(check(board, a, b, c)){
                board[a][b] = c;
                if(helper(board, k + 1, n)) return true;
                board[a][b] = '.';
            }
        }

        return false;
    }

    private boolean check(char[][] board, int a, int b, char c){
        for(int i = 0; i < board.length; i++){
            if(board[a][i] == c || board[i][b] == c) return false;
        }

        for(int i = (a / 3) * 3; i <= (a / 3) * 3 + 2; i++){
            for(int j = (b / 3) * 3; j <= (b / 3) * 3 + 2; j++){
                if(board[i][j] == c) return false;
            }
        }

        return true;
    }
}
