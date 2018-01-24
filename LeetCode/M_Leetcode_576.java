package LeetCode;

/**
 576. Out of Boundary Paths

 There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

 Example 1:
 Input:m = 2, n = 2, N = 2, i = 0, j = 0
 Output: 6
 Explanation:

 Example 2:
 Input:m = 1, n = 3, N = 3, i = 0, j = 1
 Output: 12
 Explanation:

 Note:
 Once you move the ball out of boundary, you cannot move it back.
 The length and height of the grid is in range [1,50].
 N is in range [0,50].
 */
public class M_Leetcode_576 {
    /**
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     *
     * T(x, y, k) - num of paths to reach (x, y) with k steps.
     * init - T(i, j, 0) = 1;
     *
     * recurrence-
     * T(x, y, k) = T(x - 1, y, k - 1) + T(x + 1, y, k - 1) + T(x, y - 1, k - 1) + T(x, y + 1, k - 1);
     *
     * original problem-
     * sum(T(0, y, k)) + sum(T(m - 1, y, k)) + sum(T(x, 0, k)) + sum(T(x, n - 1, k));
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        if(N == 0) return 0;
        int[][][] dp = new int[N][m][n];
        dp[0][i][j] = 1;

        for(int x = 1; x < N; x++){
            for(int y = 0; y < m; y++){
                for(int z = 0; z < n; z++){
                    if(y > 0) {
                        dp[x][y][z] += dp[x - 1][y - 1][z];
                        dp[x][y][z] %= 1000000007;
                    }

                    if(y < m - 1) {
                        dp[x][y][z] += dp[x - 1][y + 1][z];
                        dp[x][y][z] %= 1000000007;
                    }

                    if(z > 0){
                        dp[x][y][z] += dp[x - 1][y][z - 1];
                        dp[x][y][z] %= 1000000007;
                    }

                    if(z < n - 1){
                        dp[x][y][z] += dp[x - 1][y][z + 1];
                        dp[x][y][z] %= 1000000007;
                    }
                }
            }
        }

        int res = 0;
        for(int x = 0; x < m; x++){
            for(int k = 0; k < N; k++){
                res += dp[k][x][0];
                res %= 1000000007;
                res += dp[k][x][n - 1];
                res %= 1000000007;
            }
        }

        for(int x = 0; x < n; x++){
            for(int k = 0; k < N; k++){
                res += dp[k][0][x];
                res %= 1000000007;
                res += dp[k][m - 1][x];
                res %= 1000000007;
            }
        }

        return res;
    }
}
