package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 202. Happy Number

 Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example: 19 is a happy number

 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1
 Credits:
 Special thanks to @mithmatt and @ts for adding this problem and creating all test cases.
 */
public class L_Leetcode_202 {
    /**
     * @param n
     * @return
     *
     * record visited nums and try next one.
     */
    public boolean isHappy(int n) {
        if(n == 1) return true;
        Set<Integer> visited = new HashSet<>();
        while(n != 1){
            if(visited.contains(n)) return false;
            visited.add(n);
            n = next(n);
        }

        return true;
    }

    /**
     * @param n
     * @return
     *
     * finding a loop here is very similar to finding a cycle in linked list.
     * we can use slow / fast pointer to do the job. Space usage would be O(1)
     */
    public boolean isHappy2(int n) {
        if(n == 1) return true;
        int slow = n;
        int fast = n;
        do{
            slow = next(slow);
            fast = next(fast);
            fast = next(fast);
        }while(fast != 1 && slow != fast);

        return (fast == 1);
    }

    private int next(int n){
        int res = 0;
        while(n > 0){
            int k = n % 10;
            res += (k * k);
            n /= 10;
        }
        return res;
    }
}
