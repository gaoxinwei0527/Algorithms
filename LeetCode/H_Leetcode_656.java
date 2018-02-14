package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 656. Coin Path

 Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.

 Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

 If there are multiple paths with the same cost, return the lexicographically smallest such path.

 If it's not possible to reach the place indexed N then you need to return an empty array.

 Example 1:
 Input: [1,2,4,-1,2], 2
 Output: [1,3,5]
 Example 2:
 Input: [1,2,4,-1,2], 1
 Output: []
 Note:
 Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
 A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
 Length of A is in the range of [1, 1000].
 B is in the range of [1, 100].
 */
public class H_Leetcode_656 {
    /**
     * @param A
     * @param B
     * @return
     *
     * T(i) means shortest path from Ai to An
     * Recurrence-
     * T(i)-
     *   if(Ai == -1) T(i) = {}
     *   else
     *     foreach k in [i + 1, i + B]
     *       T(i) = i + min(T(k));
     *
     * Init- Tn = An
     * original problem - T(1);
     *
     * TODO- conclude parent pointers
     */
    public List<Integer> cheapestJump(int[] A, int B) {
        int N = A.length;
        if(A[N - 1] == -1) return new ArrayList<>();
        int[] dp = new int[N + 1];
        List<Integer>[] path = new ArrayList[N + 1];
        dp[N] = A[N - 1];
        path[N] = new ArrayList<>();
        path[N].add(N);

        for(int i = N - 1; i >= 1; i--){
            dp[i] = 1000000;
            path[i] = new ArrayList<>();
            if(A[i - 1] != -1){
                for(int k = i + 1; k <= i + B && k <= N; k++){
                    if(A[k - 1] != -1 && A[i - 1] + dp[k] < dp[i]){
                        dp[i] = A[i - 1] + dp[k];
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.addAll(path[k]);
                        path[i] = list;
                    }
                }
            }
        }

        if(dp[1] == 1000000) return new ArrayList<>();
        return path[1];
    }
}
