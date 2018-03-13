package LeetCode;

/**
 688. Knight Probability in Chessboard

 On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

 A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.


 Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

 The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

 Example:
 Input: 3, 2, 0, 0
 Output: 0.0625
 Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 From each of those positions, there are also two moves that will keep the knight on the board.
 The total probability the knight stays on the board is 0.0625.
 Note:
 N will be between 1 and 25.
 K will be between 0 and 100.
 The knight always initially starts on the board.
 */
public class H_Leetcode_688 {
    /*
    T(i, j, k) means the probability to stop on (i, j) at step k
    Recurrence-
    T(i, j, k) -> foreach d in direction
                    T(i, j, k) += T(i + d[0], j + d[1], k - 1) * 0.125;

    original problem-
    sum of all T(i, j, K)

    bottom up-
    dp[i][j][k] store the result of T(i, j, k);
    */
    private int[][] dir = new int[][]{{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N][N][K + 1];
        dp[r][c][0] = 1.0;
        for(int k = 1; k <= K; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int[] d : dir){
                        int x = i + d[0];
                        int y = j + d[1];
                        if(x >= 0 && x < N && y >= 0 && y < N) dp[i][j][k] += (dp[x][y][k - 1] * 0.125);
                    }
                }
            }
        }

        double res = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                res += dp[i][j][K];
            }
        }

        return res;
    }
}
