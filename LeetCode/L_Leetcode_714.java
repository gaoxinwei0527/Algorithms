package LeetCode;

/**
 714. Best Time to Buy and Sell Stock with Transaction Fee

 Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

 You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

 Return the maximum profit you can make.

 Example 1:
 Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 Output: 8
 Explanation: The maximum profit can be achieved by:
 Buying at prices[0] = 1
 Selling at prices[3] = 8
 Buying at prices[4] = 4
 Selling at prices[5] = 9
 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 Note:

 0 < prices.length <= 50000.
 0 < prices[i] < 50000.
 0 <= fee < 50000.
 */
public class L_Leetcode_714 {
    /**
     * @param prices
     * @param fee
     * @return
     *
     * T(i, 0) means the max profit without stock at day i
     * T(i, 1) means the max profit with stock at day i
     *
     * recurrence-
     * T(i, 0) = max(T(i - 1, 0), T(i - 1, 1) + prices[i] - fee)
     * T(i, 1) = max(T(i - 1, 1), T(i - 1, 0) - prices[i])
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[0][1] = -100000000;

        for(int i = 1; i <= n; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
        }

        return dp[n][0];
    }
}
