package LeetCode;

/**
 256. Paint House

 There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.
 */
public class L_Leetcode_256 {
    /**
     * @param costs
     * @return
     *
     * dp.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0) return 0;
        int r = costs[0][0];
        int b = costs[0][1];
        int g = costs[0][2];

        for(int i = 1; i < n; i++){
            int next_r = Math.min(b, g) + costs[i][0];
            int next_b = Math.min(r, g) + costs[i][1];
            int next_g = Math.min(r, b) + costs[i][2];
            r = next_r;
            b = next_b;
            g = next_g;
        }

        return Math.min(r, Math.min(b, g));
    }
}
