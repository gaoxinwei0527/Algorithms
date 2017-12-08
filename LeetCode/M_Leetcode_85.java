package LeetCode;

import java.util.Stack;

/**
 85. Maximal Rectangle

 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 */
public class M_Leetcode_85 {
    /**
     * @param matrix
     * @return
     *
     * for each row, regard each col as a histogram, then process each row as (84. Largest Rectangle in Histogram)
     * Time - O(m * n)
     * Space - O(m * n)
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pp = new int[m][n];
        int max = 0;
        for(int i = 0; i < m; i++){
            Stack<Integer> st = new Stack<>();
            st.push(-1);
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0') pp[i][j] = 0;
                else pp[i][j] = 1 + (i > 0 ? pp[i - 1][j] : 0);

                int h = pp[i][j];
                while(st.peek() != -1 && pp[i][st.peek()] > h){
                    int next = st.pop();
                    max = Math.max(max, (j - st.peek() - 1) * pp[i][next]);
                }

                st.push(j);
            }

            while(st.peek() != -1){
                int next = st.pop();
                max = Math.max(max, (n - st.peek() - 1) * pp[i][next]);
            }
        }

        return max;
    }
}
