package LeetCode;

/**
 188. Best Time to Buy and Sell Stock IV

 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Credits:
 Special thanks to @Freezen for adding this problem and creating all test cases.
 */
public class H_Leetcode_188 {
    /**
     * @param k
     * @param prices
     * @return
     *
     * dp - O(nk)
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n < 2) return 0;

        /* if k >= prices.length - 1, it becomes unlimited transaction actually*/
        if(k >= n) {
            int res = 0;
            for(int i = 1; i < n; i++){
                if(prices[i] > prices[i - 1]) res += (prices[i] - prices[i - 1]);
            }
            return res;
        }

        // dp[i][j][0] means max profit at the end of day i without stock and with at most j transactions
        // dp[i][j][1] means max profit at the end of day i with stock and with at most j transactions
        int[][][] dp = new int[n][k + 1][2];

        // at day 0, no stock means no transaction, thus max profit is 0.
        // has stock means just bought stock on day 0, thus max profit is -prices[0];
        for(int i = 1; i <= k; i++){
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        // when we do the iteration
        // dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]); // rest or sell on day i
        // dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]); // rest or buy on day i
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= k; j++){
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[n - 1][k][0];
    }
}
