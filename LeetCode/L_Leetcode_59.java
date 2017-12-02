package LeetCode;

/**
 59. Spiral Matrix II

 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

 For example,
 Given n = 3,

 You should return the following matrix:
 [
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
 ]
 */
public class L_Leetcode_59 {
    /**
     * @param n
     * @return
     *
     * simple traverse
     *
     * Time - O(n ^ 2)
     * Space - O(n ^ 2)
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        helper(res, 0, n - 1, 0, n - 1, 1, n);
        return res;
    }

    private void helper(int[][] res, int a, int b, int i, int j, int k, int n){
        if(k > n * n){
            return;
        }

        for(int x = i; x <= j; x++){
            res[a][x] = k++;
        }

        if(a == b) return;

        for(int x = a + 1; x <= b; x++){
            res[x][j] = k++;
        }

        if(i == j) return;

        for(int x = j - 1; x >= i; x--){
            res[b][x] = k++;
        }

        for(int x = b - 1; x >= a + 1; x--){
            res[x][i] = k++;
        }

        helper(res, a + 1, b - 1, i + 1, j - 1, k, n);
    }
}
