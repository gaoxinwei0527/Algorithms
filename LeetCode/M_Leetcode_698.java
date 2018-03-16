package LeetCode;

/**
 698. Partition to K Equal Sum Subsets

 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 Example 1:
 Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 Output: True
 Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 Note:

 1 <= k <= len(nums) <= 16.
 0 < nums[i] < 10000.
 */
public class M_Leetcode_698 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * dfs + backtrack
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i : nums) sum += i;
        if(sum % k != 0) return false;
        int target = sum / k;

        boolean[] visited = new boolean[nums.length];
        return helper(nums, visited, 0, 0, target);
    }

    private boolean helper(int[] nums, boolean[] visited, int i, int cur, int target){
        if(cur == target){
            boolean finish = true;
            for(boolean v : visited) finish &= v;
            if(finish) return true;
            i = 0;
            cur = 0;
        }

        for(int k = i; k < nums.length; k++){
            if(!visited[k] && nums[k] + cur <= target){
                visited[k] = true;
                if(helper(nums, visited, k + 1, cur + nums[k], target)) return true;
                visited[k] = false;
            }
        }

        return false;
    }
}
