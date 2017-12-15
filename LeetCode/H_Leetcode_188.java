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
        if(prices.length < 2) return 0;

        /* if k >= prices.length - 1, it becomes unlimited transaction actually*/
        if(prices.length <= k + 1){
            int res = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[i] > prices[i - 1]) res += (prices[i] - prices[i - 1]);
            }
            return res;
        }

        // dp[i][k][0] means max profit at ith day without stock and at most k transactions
        // dp[i][k][1] means max profit at ith day with stock and at most k transactions
        int[][][] dp = new int[prices.length + 1][k + 1][2];

        // dp[0][k][0] should all be 0, because no transaction will happened even before day 1
        // dp[0][k][1] should all be Integer.MIN_VALUE, because it's impossible we have stock on day 0
        for(int i = 0; i <= k; i++){
            dp[0][i][0] = 0;
            dp[0][i][1] = Integer.MIN_VALUE;
        }

        // when we do the iteration
        // dp[i][0][0] is anyway 0, because no transaction happened
        // dp[i][0][1] is anyway Integer.MIN_VALUE, because it's impossible
        // dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]); // rest or sell on day i
        // dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i - 1]); // rest or buy on day i
        for(int i = 1; i <= prices.length; i++){
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
            for(int j = 1; j <= k; j++){
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
            }
        }
        return dp[prices.length][k][0];
    }
}
