package LeetCode;

import java.util.Arrays;

/**
 611. Valid Triangle Number

 Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 Example 1:
 Input: [2,2,3,4]
 Output: 3
 Explanation:
 Valid combinations are:
 2,3,4 (using the first 2)
 2,3,4 (using the second 2)
 2,2,3
 Note:
 The length of the given array won't exceed 1000.
 The integers in the given array are in the range of [0, 1000].
 */
public class M_Leetcode_611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for(int i = 0; i < nums.length - 2; i++){
            for(int j = i + 1; j < nums.length - 1; j++){
                int k = nums[i] + nums[j];
                int l = j + 1;
                int r = nums.length - 1;
                while(l <= r){
                    int mid = (l + r) >> 1;
                    if(nums[mid] >= k) r = mid - 1;
                    else l = mid + 1;
                }
                res += (l - j - 1);
            }
        }

        return res;
    }
}
