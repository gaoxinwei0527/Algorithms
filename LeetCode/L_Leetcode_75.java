package LeetCode;

import java.util.HashMap;

/**
 75. Sort Colors

 Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.
 */
public class L_Leetcode_75 {
    /**
     * @param nums
     *
     * i is right bound of 0, j is right bound of 1, k is right bound of 2
     *
     * Time - O(n)
     * Space - O(1)
     */
    public void sortColors(int[] nums) {
        int i = 0;
        int j = 0;
        int k = 0;
        while(k < nums.length){
            if(nums[k] == 0){
                nums[k++] = 2;
                nums[j++] = 1;
                nums[i++] = 0;
            }else if(nums[k] == 1){
                nums[k++] = 2;
                nums[j++] = 1;
            }else{
                k++;
            }
        }
    }
}
