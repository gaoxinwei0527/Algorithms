package LeetCode;

/**
 55. Jump Game

 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.
 */
public class L_Leetcode_55 {
    /**
     * @param nums
     * @return
     *
     * Greedy, for each jump, try to reach the max index
     *
     * Time - O(n)
     * Space - O(1)
     */
    public boolean canJump(int[] nums) {
        int l = 0;
        int r = 0;
        while(l <= r && r < nums.length - 1){
            int next = 0;
            for(int i = l; i <= r; i++){
                next = Math.max(nums[i] + i, next);
            }

            l = r + 1;
            r = next;
        }

        return r >= (nums.length - 1);
    }
}
