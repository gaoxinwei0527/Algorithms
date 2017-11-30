package LeetCode;

/**
 45. Jump Game II

 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 Note:
 You can assume that you can always reach the last index.
 */
public class M_Leetcode_45 {
    /**
     * @param nums
     * @return
     *
     * Because we can assume it can always reach the last index,
     * So every jump, we get the max index we can get as m, so next jump should happen in last max index i+1 to m.
     * This is greedy strategy to get the last index.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int jump(int[] nums) {
        int res = 0;
        int l = 0;
        int r = 0;
        while(r < nums.length - 1){
            res++;
            int max = 0;
            for(int i = l; i <= r; i++){
                max = Math.max(max, i + nums[i]);
            }
            l = r + 1;
            r = max;
        }

        return res;
    }
}
