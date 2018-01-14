package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 523. Continuous Subarray Sum

 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

 Example 1:
 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:
 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 Note:
 The length of the array won't exceed 10,000.
 You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class H_Leetcode_523 {
    /**
     * @param nums
     * @param k
     * @return
     *
     * Sum up the nums in sum[], then sum[j] - sum[i] means the sum of subarray (i-j);
     * Then iterate from end of sum[] to check all the sum[i-j] where j-i>=2;
     *
     * Time - O(n ^ 2)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            sum[i + 1] = sum[i] + nums[i];
        }

        for(int i = nums.length; i >= 2; i--){
            for(int j = i-2; j >= 0; j--){
                if(k!= 0 && (sum[i] - sum[j]) % k == 0){
                    return true;
                }else if(k == 0 && sum[i] == sum[j]){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param nums
     * @param k
     * @return
     *
     * super elegant way.
     * iterate array and update sum, also calculate mod = sum % k, store mod in hash map.
     * if we get sum[i] % k == sum[j] % k && j - i >= 2, then return true;
     *
     * Time - O(n)
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        if(k == 0){
            for(int i = 1; i < nums.length; i++){
                if(nums[i] == 0 && nums[i - 1] == 0) return true;
            }
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int next = sum % k;
            if(map.containsKey(next) && i - map.get(next) >= 2) return true;
            if(!map.containsKey(next)) map.put(next, i);
        }

        return false;
    }
}
