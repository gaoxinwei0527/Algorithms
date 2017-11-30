package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 47. Permutations II

 Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 For example,
 [1,1,2] have the following unique permutations:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */
public class L_Leetcode_47 {
    /**
     * @param nums
     * @return
     *
     * dfs + backtrack + skip duplicates
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
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
                while(i < nums.length - 1 && nums[i + 1] == nums[i]){
                    i++;
                }
            }
        }
    }
}
