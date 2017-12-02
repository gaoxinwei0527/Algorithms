package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 54. Spiral Matrix

 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:

 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]

 You should return [1,2,3,6,9,8,7,4,5].
 */
public class L_Leetcode_54 {
    /**
     * @param matrix
     * @return
     *
     * simple traverse
     *
     * Time - O(m * n)
     * Space - O(m * n)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        helper(res, matrix, 0, m - 1, 0, n - 1);
        return res;
    }

    private void helper(List<Integer> res, int[][] matrix, int a, int b, int i, int j){
        if(a > b || i > j) return;
        for(int k = i; k <= j; k++){
            res.add(matrix[a][k]);
        }

        if(a == b) return;

        for(int k = a + 1; k <= b; k++){
            res.add(matrix[k][j]);
        }

        if(i == j) return;

        for(int k = j - 1; k >= i; k--){
            res.add(matrix[b][k]);
        }

        for(int k = b - 1; k >= a + 1; k--){
            res.add(matrix[k][i]);
        }

        helper(res, matrix, a + 1, b - 1, i + 1, j - 1);
    }
}
