package LeetCode;

import java.util.Arrays;

/**
 259. 3Sum Smaller

 Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

 For example, given nums = [-2, 0, 1, 3], and target = 2.

 Return 2. Because there are two triplets which sums are less than 2:

 [-2, 0, 1]
 [-2, 0, 3]
 Follow up:
 Could you solve it in O(n2) runtime?
 */
public class L_Leetcode_259 {
    /**
     * @param nums
     * @param target
     * @return
     *
     * sort
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++){
            int t = target - nums[i];
            int m = i + 1;
            int n = nums.length - 1;
            while(m < n){
                while(m < n && nums[m] + nums[n] >= t) n--;
                res += (n - m);
                m++;
            }
        }

        return res;
    }
}
