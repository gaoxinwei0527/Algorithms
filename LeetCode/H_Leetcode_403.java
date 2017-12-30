package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 403. Frog Jump

 A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

 Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

 If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.

 Note:

 The number of stones is ≥ 2 and is < 1,100.
 Each stone's position will be a non-negative integer < 231.
 The first stone's position is always 0.
 Example 1:

 [0,1,3,5,6,8,12,17]

 There are a total of 8 stones.
 The first stone at the 0th unit, second stone at the 1st unit,
 third stone at the 3rd unit, and so on...
 The last stone at the 17th unit.

 Return true. The frog can jump to the last stone by jumping
 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 2 units to the 4th stone, then 3 units to the 6th stone,
 4 units to the 7th stone, and 5 units to the 8th stone.
 Example 2:

 [0,1,2,3,4,8,9,11]

 Return false. There is no way to jump to the last stone as
 the gap between the 5th and 6th stone is too large.
 */
public class H_Leetcode_403 {
    /**
     * @param stones
     * @return
     *
     * 1st dp way
     *
     * subproblem J(i) -> all possible choices of end step nums to reach stone i
     * init -> J(1) = {1}
     *
     * Subproblem in recurrence-
     * J(i) : res{}
     *   foreach k in [1, i - 1]
     *      if(J(k).contains(i - k) || J(k).contains(i - k - 1) || J(k).contains(i - k + 1)) then res.add(i - k);
     *
     * Subproblem dependency-
     * J(i) <- {J(i - 1), J(i - 2), ..., J(1)}
     * J(i - 1) <- {J(i - 2), ..., J(1)}
     * ....
     * J(2) <- J(1)
     *
     * original problem -> if J(n - 1) is empty or not
     * bottom-up way- dp[i] record all possible end step nums landing ith stone
     */
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if(n <= 1) return true;
        if(stones[1] != 1) return false;
        for(int i = 2; i < n; i++){
            if(stones[i] - stones[i - 1] > i) return false;
        }
        Set<Integer>[] dp = new Set[n];
        dp[1] = new HashSet<>();
        dp[1].add(1);
        for(int i = 2; i < n; i++){
            dp[i] = new HashSet<>();
            for(int j = i - 1; j > 0; j--){
                int k = stones[i] - stones[j];
                if(dp[j].contains(k) || dp[j].contains(k - 1) || dp[j].contains(k + 1)) dp[i].add(k);
            }
        }

        return (!dp[n - 1].isEmpty());
    }

    /**
     * @param stones
     * @return
     *
     * 2nd way of dp
     *
     * subproblem J(i, k) -> if possible or not to reach stone i with ending step num as k
     * init -> J(1, 1) = true
     *
     * Subproblem in recurrence-
     * J(i, k): init as false
     *   foreach j in [1, i - 1]
     *      k = stones[i] - stones[j];
     *      if(J(j, k) || J(j, k - 1) || J(j, k + 1)), then J(i, k) = true;
     *
     * subproblem dependency-
     * J(i, k) <- {J(i - k, k), J(i - k, k - 1), J(i - k, k + 1)}
     *
     * original problem - J(n - 1, 1) || J(n - 1, 2) || ... || J(n - 1, n - 1), note that k is at most same as i.
     * because even every time we increment the step num by 1, when we reach stone i, step num would be just i.
     *
     * bottom-up way- dp[i][k] records whether or not possible to reach stone i with ending step num as k
     */
    public boolean canCross2(int[] stones) {
        int n = stones.length;
        if(n <= 1) return true;
        if(stones[1] != 1) return false;
        for(int i = 2; i < n; i++){
            if(stones[i] - stones[i - 1] > i) return false;
        }

        boolean[][] dp = new boolean[n][n];
        dp[1][1] = true;
        for(int i = 2; i < n; i++){
            for(int j = i - 1; j > 0; j--){
                int k = stones[i] - stones[j];
                if(k < 0 || k >= n) continue;
                boolean a = (k - 1 >= 0 && k - 1 < n) && dp[j][k - 1];
                boolean b = (k + 1 >= 0 && k + 1 < n) && dp[j][k + 1];
                dp[i][k] = (dp[j][k] | a | b);
            }
        }

        boolean res = false;
        for(int i = 0; i < n; i++){
            res |= dp[n - 1][i];
        }

        return res;
    }
}
