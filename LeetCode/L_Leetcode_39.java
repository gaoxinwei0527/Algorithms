package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 39. Combination Sum

 Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [2, 3, 6, 7] and target 7,
 A solution set is:
 [
 [7],
 [2, 2, 3]
 ]
 */
public class L_Leetcode_39 {
    /**
     * @param candidates
     * @param target
     * @return
     *
     * sort the array, then dfs + backtrack
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
                helper(res, num, i, tmp, cur + num[i], target);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
