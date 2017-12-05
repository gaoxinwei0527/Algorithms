package LeetCode;

/**
 73. Set Matrix Zeroes

 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class M_Leetcode_73 {
    /**
     * @param matrix
     *
     * Use 1st row / col to store if corresponding row / col should be set to 0, firstly need to process 1st col / row.
     *
     * Time - O(m * n)
     * Space - O(1)
     */
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        if(matrix[0][0] == 0){
            firstColZero = true;
            firstRowZero = true;
        }

        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0) {
                firstColZero = true;
            }
        }

        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0) {
                firstRowZero = true;
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for(int i = 1; i < m; i++){
            if(matrix[i][0] == 0){
                for(int j = 1; j < n; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < n; i++){
            if(matrix[0][i] == 0){
                for(int j = 1; j < m; j++){
                    matrix[j][i] = 0;
                }
            }
        }

        if(firstRowZero){
            for(int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }

        if(firstColZero){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
