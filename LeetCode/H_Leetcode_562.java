package LeetCode;

/**
 562. Longest Line of Consecutive One in Matrix

 Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
 Example:
 Input:
 [[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
 Output: 3
 Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class H_Leetcode_562 {
    public int longestLine(int[][] M) {
        if(M.length == 0 || M[0].length == 0) return 0;
        int m = M.length;
        int n = M[0].length;

        int max = 0;
        int[][] dig = new int[m][n];
        for(int i = 0; i < m; i++){
            int row = 0;
            for(int j = 0; j < n; j++){
                if(M[i][j] == 0) row = 0;
                else{
                    row++;
                    max = Math.max(max, row);
                    dig[i][j] = M[i][j];
                    if(i > 0 && j < n - 1){
                        dig[i][j] += dig[i - 1][j + 1];
                        max = Math.max(max, dig[i][j]);
                    }
                }
            }
        }


        for(int i = 0; i < n; i++){
            int col = 0;
            for(int j = 0; j < m; j++){
                if(M[j][i] == 0) col = 0;
                else{
                    col++;
                    max = Math.max(max, col);

                    if(i > 0 && j > 0) {
                        M[j][i] += M[j - 1][i - 1];
                        max = Math.max(max, M[j][i]);
                    }
                }
            }
        }

        return max;
    }

    public static void main(String[] args){
        H_Leetcode_562 h_leetcode_562 = new H_Leetcode_562();
        int[][] M = new int[][]{{1,1,0,0,1,0,0,1,1,0},{1,0,0,1,0,1,1,1,1,1},{1,1,1,0,0,1,1,1,1,0},{0,1,1,1,0,1,1,1,1,1},{0,0,1,1,1,1,1,1,1,0},{1,1,1,1,1,1,0,1,1,1},{0,1,1,1,1,1,1,0,0,1},{1,1,1,1,1,0,0,1,1,1},{0,1,0,1,1,0,1,1,1,1},{1,1,1,0,1,0,1,1,1,1}};
        System.out.println(h_leetcode_562.longestLine(M));
    }
}
