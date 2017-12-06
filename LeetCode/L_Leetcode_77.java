package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 77. Combinations

 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class L_Leetcode_77 {
    /**
     * @param n
     * @param k
     * @return
     *
     * Simple backtrack
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, 1, k, n);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int i, int k, int n){
        if(k == tmp.size()){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int a = i; a <= n; a++){
            tmp.add(a);
            helper(res, tmp, a + 1, k, n);
            tmp.remove(tmp.size() - 1);
        }
    }
}
