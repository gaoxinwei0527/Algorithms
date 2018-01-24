package LeetCode;

import java.util.Arrays;

/**
 581. Shortest Unsorted Continuous Subarray

 Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

 You need to find the shortest such subarray and output its length.

 Example 1:
 Input: [2, 6, 4, 8, 10, 9, 15]
 Output: 5
 Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 Note:
 Then length of the input array is in range [1, 10,000].
 The input array may contain duplicates, so ascending order here means <=.
 */
public class H_Leetcode_581 {
    /**
     * @param nums
     * @return
     *
     * sort & compare the diff
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = new int[nums.length];
        System.arraycopy(nums, 0, arr, 0, nums.length);
        Arrays.sort(arr);

        int i = 0;
        while(i < nums.length && nums[i] == arr[i]) i++;

        int j = nums.length - 1;
        while(j > i && nums[j] == arr[j]) j--;

        if(i == j) return 0;
        return j - i + 1;
    }
}
