package LeetCode;

import java.util.Arrays;

/**
 689. Maximum Sum of 3 Non-Overlapping Subarrays

 In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

 Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

 Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

 Example:
 Input: [1,2,1,2,6,7,5,1], 2
 Output: [0, 3, 5]
 Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 Note:
 nums.length will be between 1 and 20000.
 nums[i] will be between 1 and 65535.
 k will be between 1 and floor(nums.length / 3).
 */
public class S_Leetcode_689 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * get 3 helper array-
     * 1. prefix sum array
     * 2. left[i] means the max sum in nums[0 - i]
     * 3. right[i] means the max sum in nums[i - n-1]
     *
     * try to find the start index of mid range (in k to n - 2k), whenever we get a start index of mid range, we can get the corresponding max sum and update result correspondingly.
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[] left = new int[n];
        int[] right = new int[n];

        left[k - 1] = sum[k];
        for(int i = k + 1; i <= n; i++){
            left[i - 1] = Math.max(left[i - 2], sum[i] - sum[i - k]);
        }

        right[n - k] = sum[n] - sum[n - k];
        for(int i = n - k - 1; i >= 0; i--){
            right[i] = Math.max(right[i + 1], sum[i + k] - sum[i]);
        }

        int[] res = new int[3];
        int max = 0;
        for(int i = k; i <= (n - 2 * k); i++){
            int mid = sum[i + k] - sum[i];
            int l = left[i - 1];
            int r = right[i + k];
            if(max < mid + l + r){
                int l_id = 0;
                while(sum[l_id + k] - sum[l_id] != l) l_id++;

                int r_id = i + k;
                while(sum[r_id + k] - sum[r_id] != r) r_id++;

                res = new int[]{l_id, i, r_id};
                max = mid + l + r;
            }
        }

        return res;
    }
}
