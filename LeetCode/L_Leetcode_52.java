package LeetCode;

/**
 52. N-Queens II

 Follow up for N-Queens problem.

 Now, instead outputting board configurations, return the total number of distinct solutions.
 */
public class L_Leetcode_52 {
    /**
     * @param n
     * @return
     *
     * Same as NQueens I, just count
     */
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        int[] res = new int[1];
        helper(res, board, col, 0, n);
        return res[0];
    }

    private void helper(int[] res, char[][] board, boolean[] col, int i, int n){
        if(i == n){
            res[0]++;
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
