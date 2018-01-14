package LeetCode;

/**
 517. Super Washing Machines

 You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

 For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

 Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

 Example1

 Input: [1,0,5]

 Output: 3

 Explanation:
 1st move:    1     0 <-- 5    =>    1     1     4
 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 3rd move:    2     1 <-- 3    =>    2     2     2
 Example2

 Input: [0,3,0]

 Output: 2

 Explanation:
 1st move:    0 <-- 3     0    =>    1     2     0
 2nd move:    1     2 --> 0    =>    1     1     1
 Example3

 Input: [0,2,0]

 Output: -1

 Explanation:
 It's impossible to make all the three washing machines have the same number of dresses.
 Note:
 The range of n is [1, 10000].
 The range of dresses number in a super washing machine is [0, 1e5].
 */
public class H_Leetcode_517 {
    /**
     * @param machines
     * @return
     *
     * we can assume all the machines make up a pipeline, and the bandwidth is 1.
     * and for each machine i, we can calculate how many clothes need to go through i to left & how many would go through i to right.
     * them sum up pass_left[i] + pass_right[i] for each i, the machine with the max sum would be the bottleneck, and the max sum is the result.
     * (because each machine can pass 1 cloth each round, so it needs max_sum rounds to finish the bottleneck's task)
     */
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += machines[i];
        }

        if(sum % n != 0) return -1;
        int k = sum / n;

        int[] pass_left = new int[n];
        int[] pass_right = new int[n];

        sum = 0;
        for(int i = 0; i < n; i++){
            if(sum < i * k){
                pass_left[i] = i * k - sum;
            }
            sum += machines[i];
        }

        sum = 0;
        for(int i = n - 1; i >= 0; i--){
            if(sum < (n - i - 1) * k){
                pass_right[i] = (n - i - 1) * k - sum;
            }
            sum += machines[i];
        }

        int res = 0;
        for(int i = 0; i < n; i++){
            res = Math.max(pass_left[i] + pass_right[i], res);
        }

        return res;
    }
}
