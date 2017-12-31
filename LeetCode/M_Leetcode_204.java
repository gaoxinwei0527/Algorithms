package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 204. Count Primes

 Description:
 Count the number of prime numbers less than a non-negative number, n.
 */
public class M_Leetcode_204 {
    /**
     * @param n
     * @return
     *
     * TLE on leetcode. Since Sieve algorithms are proven most efficient in prime finding problems.
     * But this is a dp way to solve the problem
     *
     * Subproblem - P(i) means all primes in range [2, i - 1];
     * init - P(2) = {}, P(3) = {2}, P(4) = {2, 3}, etc.
     *
     * Recurrence way for P(i):
     *   init P(i) = P(i - 1)
     *   foreach k in P(i - 1)
     *      if((i - 1) % k == 0) go to P(i + 1);
     *   P(i).add(i - 1);
     *
     * Dependency DAG:
     *   P(2) -> P(3) -> P(4) -> ... -> P(n - 1) -> P(n)
     *
     * original problem - find P(n).size()
     * bottom up way - dp[i] records all primes in range [2, i - 1];
     */
    public int countPrimes(int n) {
        if(n <= 2) return 0;
        List<Integer>[] dp = new List[n + 1];
        dp[2] = new ArrayList<>();
        for(int i = 3; i <= n; i++){
            dp[i] = new ArrayList<>(dp[i - 1]);
            boolean isPrime = true;
            for(int prime : dp[i - 1]){
                if((i - 1) % prime == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime) dp[i].add(i - 1);
        }

        return dp[n].size();
    }

    /**
     * @param n
     * @return
     *
     * classic Sieve of Eratosthenes (埃氏筛)
     *
     * Time - O(nloglogn)
     * Space - O(n)
     */
    public int countPrimes2(int n) {
        if(n <= 2) return 0;
        boolean[] composite = new boolean[n + 1];
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(!composite[i]){
                // any composite smaller than i * i (e.g. 2 * i, 3 * i, etc) would already be marked as composite
                // this is also the reason that the outer loop can stop at Math.sqrt(n).
                // because nothing need be marked with prime > Math.sqrt(n);
                for(int j = i * i; j < n; j += i){
                    composite[j] = true;
                }
            }
        }

        int count = 0;
        for(int i = 2; i < n; i++){
            if(!composite[i]) count++;
        }
        return count;
    }
}
