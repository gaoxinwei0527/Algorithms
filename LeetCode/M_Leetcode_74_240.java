package LeetCode;

public class M_Leetcode_74_240 {
    /**
     74. Search a 2D Matrix

     Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

     Integers in each row are sorted from left to right.
     The first integer of each row is greater than the last integer of the previous row.
     For example,

     Consider the following matrix:

     [
     [1,   3,  5,  7],
     [10, 11, 16, 20],
     [23, 30, 34, 50]
     ]
     Given target = 3, return true.
     */

    /**
     * @param matrix
     * @param target
     * @return
     *
     * Binary search
     *
     * Time - O(log(m * n))
     * Space - O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0;
        int r = m * n - 1;

        while(l <= r){
            int mid = (l + r) >>> 1;
            int x = mid / n;
            int y = mid % n;
            if(matrix[x][y] == target) return true;
            if(matrix[x][y] < target) l = mid + 1;
            else r = mid - 1;
        }

        return false;
    }

    /**
     240. Search a 2D Matrix II

     Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

     Integers in each row are sorted in ascending from left to right.
     Integers in each column are sorted in ascending from top to bottom.
     For example,

     Consider the following matrix:

     [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
     ]
     Given target = 5, return true.

     Given target = 20, return false.
     */

    /**
     * @param matrix
     * @param target
     * @return
     *
     * start at right-top corner.
     * 1. if matrix[i][j] == target, return true;
     * 2. if matrix[i][j] < target, means current row is too small, i++
     * 3. if matrix[i][j] > target, means current col is too big (because matrix[i][j] is smallest in current col), j--;
     *
     * in this way, every iteration we can rule out either one row or one column;
     *
     * Time - O(m + n)
     * Space - O(1)
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while(i < m && j >= 0){
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] < target) i++;
            else j--;
        }

        return false;
    }
}
