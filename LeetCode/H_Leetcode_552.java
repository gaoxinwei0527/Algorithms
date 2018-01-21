package LeetCode;

/**
 552. Student Attendance Record II

 Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

 A student attendance record is a string that only contains the following three characters:

 'A' : Absent.
 'L' : Late.
 'P' : Present.
 A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

 Example 1:
 Input: n = 2
 Output: 8
 Explanation:
 There are 8 records with length 2 will be regarded as rewardable:
 "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 Only "AA" won't be regarded as rewardable owing to more than one absent times.
 Note: The value of n won't exceed 100,000.
 */
public class H_Leetcode_552 {
    /**
     * @param n
     * @return
     *
     * subproblem T(i, a, l) - num of rewardable sequences with length i and num a of 'A' and end with num l of 'L'
     * recurrence-
     * if l == 0, means s[i] == 'A' || s[i] == 'P'
     *   then T(i, a, l) = T(i - 1, a, [0 - 2]) // add a 'P'
     *                     T(i - 1, a - 1, [0 - 2]) // add a 'A'
     * else, means s[i] == 'L'
     *   then T(i, a, l) = T(i - 1, a, l - 1)
     *
     * original problem -
     *   for a in [0, 1] & l in [0, 2]
     *     res += T(n, a, l);
     *
     */
    public int checkRecord(int n) {
        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;
        int mod = 1000000007;

        for(int i = 1; i <= n; i++){
            for(int a = 0; a < 2; a++){
                for(int l = 0; l < 3; l++){
                    if(l == 0){
                        for(int k = 0; k < 3; k++){
                            dp[i][a][l] += dp[i - 1][a][k];
                            dp[i][a][l] %= mod;
                        }

                        if(a > 0){
                            for(int k = 0; k < 3; k++){
                                dp[i][a][l] += dp[i - 1][a - 1][k];
                                dp[i][a][l] %= mod;
                            }
                        }
                    }else{
                        dp[i][a][l] = dp[i - 1][a][l - 1];
                    }
                }
            }
        }

        int res = 0;
        for(int a = 0; a < 2; a++){
            for(int l = 0; l < 3; l++){
                res += dp[n][a][l];
                res %= mod;
            }
        }

        return res;
    }
}
