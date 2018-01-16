package LeetCode;

import java.util.Arrays;

/**
 164. Maximum Gap

 Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

 Try to solve it in linear time/space.

 Return 0 if the array contains less than 2 elements.

 You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

 Credits:
 Special thanks to @porker2008 for adding this problem and creating all test cases.
 */
public class S_Leetcode_164 {
    /**
     * @param nums
     * @return
     *
     * bucket sort.
     * Suppose there are N elements in the array, the min value is min and the max value is max.
     * Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].
     *
     * When we do bucket sort, we can make bucket size < ceiling[(max - min ) / (N - 1)]. Then we can say the max gap won't be inside a single bucket.
     * For each bucket, we only record max / min value of current bucket. Then we iterate all the buckets to update max gap.
     * max gap = bucket_min[i] - bucket_max[j], where i > j;
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if(n < 2) return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;


        for(int i = 0; i < n; i++){
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }

        int bucket_size = (max - min) / n + 1;
        int bucket_num = (max - min) / bucket_size + 1;
        int[] b_max = new int[bucket_num]; // max in bucket
        int[] b_min = new int[bucket_num]; // min in bucket
        Arrays.fill(b_max, -1);
        Arrays.fill(b_min, -1);

        for(int i : nums){
            int idx = (i - min) / bucket_size;
            b_max[idx] = b_max[idx] == -1 ? i : Math.max(i, b_max[idx]);
            b_min[idx] = b_min[idx] == -1 ? i : Math.min(i, b_min[idx]);
        }

        int low = -1;
        int res = 0;
        for(int i = 0; i < bucket_num; i++){
            if(b_max[i] != -1){
                if(low == -1){
                    low = b_max[i];
                }else{
                    res = Math.max(res, b_min[i] - low);
                    low = b_max[i];
                }
            }
        }
        return res;
    }
}
