package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 216. Combination Sum III

 Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]

 Example 2:

 Input: k = 3, n = 9

 Output:

 [[1,2,6], [1,3,5], [2,3,4]]
 */
public class L_Leetcode_216 {
    /**
     * @param k
     * @param n
     * @return
     *
     * dfs + backtrack
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, 1, k, 0, n);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int i, int k, int sum, int n){
        if(sum == n && tmp.size() == k){
            res.add(new ArrayList<>(tmp));
            return;
        }

        if(i > 9 || tmp.size() >= k || sum > n) return;
        for(int j = i; j <= 9; j++){
            tmp.add(j);
            helper(res, tmp, j + 1, k, sum + j, n);
            tmp.remove(tmp.size() - 1);
        }
    }
}
