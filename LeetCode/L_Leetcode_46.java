package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 46. Permutations

 Given a collection of distinct numbers, return all possible permutations.

 For example,
 [1,2,3] have the following permutations:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 */
public class L_Leetcode_46 {
    /**
     * @param nums
     * @return
     *
     * dfs + backtrack
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        helper(res, tmp, nums, visited);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] visited){
        if(tmp.size() == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(!visited[i]){
                tmp.add(nums[i]);
                visited[i] = true;
                helper(res, tmp, nums, visited);
                tmp.remove(tmp.size() - 1);
                visited[i] = false;
            }
        }
    }
}
