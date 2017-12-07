package LeetCode;

/**
 80. Remove Duplicates from Sorted Array II

 Follow up for "Remove Duplicates":
 What if duplicates are allowed at most twice?

 For example,
 Given sorted array nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */
public class L_Leetcode_80 {
    /**
     * @param nums
     * @return
     *
     * Two pointers
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while(j < nums.length){
            int cur = nums[j];
            if(j < nums.length - 1 && nums[j + 1] == nums[j]){
                nums[i++] = cur;
                nums[i++] = cur;
            }else{
                nums[i++] = cur;
            }

            while(j < nums.length && nums[j] == cur) j++;
        }

        return i;
    }
}
