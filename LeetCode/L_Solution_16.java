package LeetCode;

import java.util.Arrays;

/**
 16. 3Sum Closest

 Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

 For example, given array S = {-1 2 1 -4}, and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
public class L_Solution_16 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * Similar to 3sum, maintain minimal delta and corresponding res.
     *
     * Time - O(n ^ 2)
     * Space - O(1)
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int delta = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++){
            int t = target - nums[i];
            int a = i + 1;
            int b = nums.length - 1;
            while(a < b){
                int sum = nums[a] + nums[b];
                if(sum == t){
                    return target;
                }

                if(delta > Math.abs(sum - t)){
                    delta = Math.abs(sum - t);
                    res = sum + nums[i];
                }

                if(sum > t){
                    b--;
                }else{
                    a++;
                }
            }
        }

        return res;
    }
}
