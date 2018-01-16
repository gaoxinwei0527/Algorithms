package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 279. Perfect Squares

 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class H_Leetcode_279 {
    /**
     * @param n
     * @return
     *
     * dp[i] means min num of perfect squares to construct i
     */
    public int numSquares(int n) {
        List<Integer> ps = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n); i++){
            ps.add(i * i);
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = 0; j < ps.size(); j++){
                if(ps.get(j) > i) break;
                if(ps.get(j) == i) dp[i] = 1;
                dp[i] = Math.min(dp[i], dp[i - ps.get(j)] + 1);
            }
        }

        return dp[n];
    }
}
