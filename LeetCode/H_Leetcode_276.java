package LeetCode;

/**
 276. Paint Fence

 There is a fence with n posts, each post can be painted with one of the k colors.

 You have to paint all the posts such that no more than two adjacent fence posts have the same color.

 Return the total number of ways you can paint the fence.

 Note:
 n and k are non-negative integers.
 */
public class H_Leetcode_276 {
    /**
     * @param n
     * @param k
     * @return
     *
     * subproblem-
     * T(i, 0) -> ways to paint post[0, i] where i & i-1 are different
     * T(i, 1) -> ways to paint post[0, i] where i & i - 1 are same
     *
     * init-
     * T(0, 0) = k;
     * T(0, 1) = 0;
     *
     * recurrence-
     * T(i, 0) = (T(i - 1, 0) + T(i - 1, 1)) * (k - 1);
     * T(i, 1) = T(i - 1, 0);
     *
     * original problem-
     * T(n - 1, 0) + T(n - 1, 1);
     */
    public int numWays(int n, int k) {
        if(n == 0) return 0;
        if(n <= 2) return (int)Math.pow(k, n);
        int t_0 = k;
        int t_1 = 0;

        for(int i = 1; i < n; i++){
            int a = (t_0 + t_1) * (k - 1);
            int b = t_0;
            t_0 = a;
            t_1 = b;
        }

        return t_0 + t_1;
    }
}
