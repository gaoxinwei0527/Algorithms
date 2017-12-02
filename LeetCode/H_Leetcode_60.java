package LeetCode;

/**
 60. Permutation Sequence

 The set [1,2,3,…,n] contains a total of n! unique permutations.

 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.

 Note: Given n will be between 1 and 9 inclusive.
 */
public class H_Leetcode_60 {
    /**
     * @param n
     * @param k
     * @return
     *
     * Every time try to find max x where x * (n - 1)! < k, then next num would be the (x + 1)th num in the remaining digits
     * And k -= x * (n - 1)!
     *
     * Time - O(n)
     * Space - O(n)
     */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n + 1];
        helper(sb, visited, n, k);
        return sb.toString();
    }

    private void helper(StringBuilder sb, boolean[] visited, int n, int k){
        if(k == 0) return;
        if(n == 1){
            for(int i = 1; i < visited.length; i++){
                if(!visited[i]){
                    sb.append(i);
                    return;
                }
            }
        }

        int base = 1;
        for(int i = n - 1; i >= 1; i--){
            base *= i;
        }

        int next = k / base;
        next = k % base == 0 ? next : next + 1;
        int tmp = next - 1;
        for(int i = 1; i < visited.length; i++){
            if(!visited[i]){
                next--;
                if(next == 0){
                    sb.append(i);
                    visited[i] = true;
                    helper(sb, visited, n - 1, k - tmp * base);
                    break;
                }
            }
        }
    }
}
