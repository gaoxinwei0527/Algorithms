package LeetCode;

/**
 96. Unique Binary Search Trees

 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3

 */
public class M_Leetcode_96 {
    /**
     * @param n
     * @return
     *
     * dp[i] means the num of unique BSTs with i nodes.
     * for each j >= 0 && j <= i - 1, j means the num of nodes in left subtree in i-node BST, then dp[i] += (dp[j] * dp[i - 1 - j])
     *
     * Time - O(n ^ 2)
     * Space - O(n)
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= i - 1; j++){
                dp[i] += (dp[j] * dp[i - 1 - j]);
            }
        }

        return dp[n];
    }
}
