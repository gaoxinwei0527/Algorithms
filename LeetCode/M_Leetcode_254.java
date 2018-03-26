package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 254. Factor Combinations

 Numbers can be regarded as product of its factors. For example,

 8 = 2 x 2 x 2;
 = 2 x 4.
 Write a function that takes an integer n and return all possible combinations of its factors.

 Note:
 You may assume that n is always positive.
 Factors should be greater than 1 and less than n.
 Examples:
 input: 1
 output:
 []
 input: 37
 output:
 []
 input: 12
 output:
 [
 [2, 6],
 [2, 2, 3],
 [3, 4]
 ]
 input: 32
 output:
 [
 [2, 16],
 [2, 2, 8],
 [2, 2, 2, 4],
 [2, 2, 2, 2, 2],
 [2, 4, 4],
 [4, 8]
 ]
 */
public class M_Leetcode_254 {
    /**
     * @param n
     * @return
     *
     * write a recursive helper to include self. then call helper(n, 2), means minimal factor 2.
     * remove the first list from result, which include n self;
     */
    public List<List<Integer>> getFactors(int n) {
        if(n <= 1) return new ArrayList<>();
        List<List<Integer>> res = helper(n, 2);
        res.remove(0); // remove self
        return res;
    }

    private List<List<Integer>> helper(int n, int min){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> self = new ArrayList<>();
        self.add(n);
        res.add(self);

        for(int i = min; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                List<List<Integer>> next = helper(n / i, i);
                for(List<Integer> k : next){
                    k.add(0, i);
                }
                res.addAll(next);
            }
        }
        return res;
    }
}
