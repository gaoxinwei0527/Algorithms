package LeetCode;

/**
 135. Candy

 There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 */
public class M_Leetcode_135 {
    /**
     * @param ratings
     * @return
     *
     * greedy, 2 pass way, left to right to fix ratings[i] > ratings[i - 1] && candy[i] <= candy[i - 1]
     * then right to left to fix ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1];
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        for(int i = 1; i < candy.length; i++){
            if(ratings[i] > ratings[i - 1]) candy[i] = candy[i - 1] + 1;
        }

        for(int i = candy.length - 2; i >= 0; i--){
            if(ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) candy[i] = candy[i + 1] + 1;
        }

        int res = ratings.length;
        for(int i = 0; i < ratings.length; i++){
            res += candy[i];
        }

        return res;
    }
}
