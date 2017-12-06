package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 78. Subsets

 Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 For example,
 If nums = [1,2,3], a solution is:

 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class L_Leetcode_78 {
    /**
     * @param nums
     * @return
     *
     * simple backtrack
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        helper(res, tmp, nums, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, int i){
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
