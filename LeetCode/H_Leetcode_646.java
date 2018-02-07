package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 646. Maximum Length of Pair Chain

 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]
 Note:
 The number of given pairs will be in the range [1, 1000].
 */
public class H_Leetcode_646 {
    /**
     * @param pairs
     * @return
     *
     * dp
     *
     * T(i) means the smallest end with chain length as i
     * It's same as LIS problem, sort the intervals with left side then right side.
     * for each interval pair, find the insertion index for pair[0] as i, then T(i) = min(T(i), pair[1]);
     * if(i == len) then len++;
     *
     * return len;
     */
    public int findLongestChain(int[][] pairs) {
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int len = 0;
        Arrays.sort(pairs, (int[] a, int[] b)->{
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for(int i = 0; i < pairs.length; i++){
            int start = pairs[i][0];
            int end = pairs[i][1];
            int l = 0;
            int r = len - 1;
            while(l <= r){
                int m = (l + r) >> 1;
                if(dp[m] >= start) r = m - 1;
                else l = m + 1;
            }
            dp[l] = Math.min(dp[l], end);
            if(l == len) len++;
        }

        return len;
    }

    /*
    Sort the array with the right side of interval, then use greedy to find the longest chain length.
    After sort, first interval will always be first one in longest chain, because let's assume interval i is first interval in longest chain, i can always be replaced by first interval. For the same reason, second interval using greedy will always be the second interval in longest chain.
    */
    public int findLongestChain2(int[][] pairs) {
        if(pairs.length == 0){
            return 0;
        }

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

        int len = 1;
        int cur = pairs[0][1];
        for(int i = 1; i < pairs.length; i++){
            if(pairs[i][0] > cur){
                len++;
                cur = pairs[i][1];
            }
        }

        return len;
    }
}
