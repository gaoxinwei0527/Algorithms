package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 40. Combination Sum II

 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 */
public class L_Leetcode_40 {
    /**
     * @param candidates
     * @param target
     * @return
     *
     * sort then dfs + backtrack, skip duplicates
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, candidates, 0, tmp, 0, target);
        return res;
    }

    private void helper(List<List<Integer>> res, int[] num, int k, List<Integer> tmp, int cur, int target){
        for(int i = k; i < num.length; i++){
            if(num[i] + cur == target){
                tmp.add(num[i]);
                res.add(new ArrayList<>(tmp));
                tmp.remove(tmp.size() - 1);
                break;
            }else if(num[i] + cur > target){
                break;
            }else{
                tmp.add(num[i]);
                helper(res, num, i + 1, tmp, cur + num[i], target);
                tmp.remove(tmp.size() - 1);
            }

            while(i < num.length - 1 && num[i + 1] == num[i]){
                i++;
            }
        }
    }
}
