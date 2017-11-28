package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 36. Valid Sudoku

 Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


 A partially filled sudoku which is valid.

 Note:
 A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.


 */
public class L_Solution_36 {
    /**
     * @param board
     * @return
     *
     * construct unique strings for row / col / cube
     *
     * Time - O(n ^ 2)
     * Space - O(n ^ 2)
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> row = new HashSet<>();
        Set<String> col = new HashSet<>();
        Set<String> cube = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.'){
                    String r = "" + i + ":" + board[i][j];
                    String c = "" + j + ":" + board[i][j];
                    String cu = "" + ((i / 3) * 3 + j / 3) + ":" + board[i][j];
                    if(row.contains(r) || col.contains(c) || cube.contains(cu)) return false;
                    row.add(r);
                    col.add(c);
                    cube.add(cu);
                }
            }
        }

        return true;
    }
}
