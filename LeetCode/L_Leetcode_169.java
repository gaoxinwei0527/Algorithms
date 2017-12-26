package LeetCode;

/**
 169. Majority Element

 Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class L_Leetcode_169 {
    /**
     * @param nums
     * @return
     *
     * classic Boyer–Moore majority vote
     *
     * Time - O(n)
     * Space - O(1)
     */
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == res){
                count++;
            }else{
                if(count == 0) res = nums[i];
                else count--;
            }
        }

        return res;
    }
}
