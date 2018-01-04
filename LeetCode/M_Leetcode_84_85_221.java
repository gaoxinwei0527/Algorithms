package LeetCode;

import java.util.Stack;

public class M_Leetcode_84_85_221 {
    /**
     84. Largest Rectangle in Histogram

     Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

     For example,
     Given heights = [2,1,5,6,2,3],
     return 10.
     */

    /**
     * @param heights
     * @return
     *
     * use stack to keep the indexes in stack ascending in height
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max = 0;
        for(int i = 0; i < heights.length; i++){
            int h = heights[i];
            if(st.peek() == -1 || h >= heights[st.peek()]){
                st.push(i);
            }else{
                while(st.peek() != -1 && heights[st.peek()] > h){
                    int next = st.pop();
                    max = Math.max((i - st.peek() - 1) * heights[next], max);
                }
                st.push(i);
            }
        }

        while(st.peek() != -1){
            int next = st.pop();
            max = Math.max((heights.length - st.peek() - 1) * heights[next], max);
        }

        return max;
    }

    /**
     85. Maximal Rectangle

     Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     */
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

    /**
     221. Maximal Square

     Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 4.
     */

    /**
     * @param matrix
     * @return
     *
     * for each row, regard each col as a histogram, then process each row as (84. Largest Rectangle in Histogram)
     * only difference is, whenever calculating the area, always use the k = min(height, width), area = k * k;
     * Time - O(m * n)
     * Space - O(m * n)
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] h = new int[m][n];
        int max = 0;
        for(int i = 0; i < m; i++){
            Stack<Integer> st = new Stack<>();
            st.push(-1);
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0') h[i][j] = 0;
                else h[i][j] = (i == 0 ? 1 : 1 + h[i - 1][j]);
                while(st.peek() != -1 && h[i][st.peek()] > h[i][j]){
                    int next = h[i][st.pop()];
                    int k = Math.min(next, (j - st.peek() - 1));
                    max = Math.max(max, k * k);
                }
                st.push(j);
            }

            while(st.peek() != -1){
                int next = h[i][st.pop()];
                int k = Math.min(next, (n - st.peek() - 1));
                max = Math.max(max, k * k);
            }
        }

        return max;
    }

    /**
     * @param matrix
     * @return
     *
     * dp way
     *
     * subproblem S(i, j) - the length of max square with (i, j) as bottom-right corner
     * recurrence-
     * S(i, j):
     *   if(m[i][j] == '0') return 0;
     *   return min(S(i - 1, j), S(i, j - 1), S(i - 1, j - 1)) + 1
     *
     * original problem - max(S(i, j) * S(i, j)), for all (i, j) points
     * bottom up- dp[i][j] record the S(i, j) result.
     *
     * Time & Space - O(n ^ 2)
     */
    public int maximalSquare2(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for(int i = 0; i < n; i++){
            dp[0][i] = (matrix[0][i] == '1' ? 1 : 0);
            max = Math.max(dp[0][i], max);
        }

        for(int i = 0; i < m; i++){
            dp[i][0] = (matrix[i][0] == '1' ? 1 : 0);
            max = Math.max(dp[i][0], max);
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j] * dp[i][j], max);
                }
            }
        }

        return max;
    }
}
