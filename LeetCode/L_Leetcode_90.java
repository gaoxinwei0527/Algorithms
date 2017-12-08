package LeetCode;

import java.util.*;

/**
 90. Subsets II

 Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,2], a solution is:

 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]
 */
public class L_Leetcode_90 {
    /**
     * @param nums
     * @return
     *
     * dfs + backtrack
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, nums, 0);
        return new ArrayList<>(res);
    }

    private void helper(Set<List<Integer>> res, List<Integer> tmp, int[] nums, int i){
        if(i == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }

        helper(res, tmp, nums, i + 1);
        tmp.add(nums[i]);
        helper(res, tmp, nums, i + 1);
        tmp.remove(tmp.size() - 1);
    }
}
