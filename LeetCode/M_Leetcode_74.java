package LeetCode;

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
public class M_Leetcode_74 {
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
            int mid = l + (r - l) / 2;
            int x = mid / n;
            int y = mid % n;
            if(matrix[x][y] == target) return true;
            if(matrix[x][y] < target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        return false;
    }
}
