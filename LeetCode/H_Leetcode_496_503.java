package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class H_Leetcode_496_503 {
    /**
     496. Next Greater Element I

     You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

     The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

     Example 1:
     Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     Output: [-1,3,-1]
     Explanation:
     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
     For number 1 in the first array, the next greater number for it in the second array is 3.
     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
     Example 2:
     Input: nums1 = [2,4], nums2 = [1,2,3,4].
     Output: [3,-1]
     Explanation:
     For number 2 in the first array, the next greater number for it in the second array is 3.
     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
     Note:
     All elements in nums1 and nums2 are unique.
     The length of both nums1 and nums2 would not exceed 1000.
     */

    /**
     * @param nums1
     * @param nums2
     * @return
     *
     * use hashmap & stack to preprocess nums2 array.
     * stack is used to track next greater element
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] next = new int[nums2.length];

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < nums2.length; i++){
            while((!st.isEmpty()) && nums2[st.peek()] < nums2[i]){
                next[st.pop()] = nums2[i];
            }

            st.push(i);
            map.put(nums2[i], i);
        }

        while(!st.isEmpty()) next[st.pop()] = -1;

        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            res[i] = next[map.get(nums1[i])];
        }

        return res;
    }

    /**
     503. Next Greater Element II

     Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

     Example 1:
     Input: [1,2,1]
     Output: [2,-1,2]
     Explanation: The first 1's next greater number is 2;
     The number 2 can't find next greater number;
     The second 1's next greater number needs to search circularly, which is also 2.
     Note: The length of given array won't exceed 10000.
     */

    /**
     * @param nums
     * @return
     *
     * stack is used to maintain descending sequence & track next greater element
     *
     * need 2 iterations because next greater num can come from left.
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < nums.length; i++){
            if(st.isEmpty() || nums[i] <= nums[st.peek()]) st.push(i);
            else{
                while(!st.isEmpty() && nums[st.peek()] < nums[i]){
                    res[st.pop()] = nums[i];
                }
                st.push(i);
            }
        }

        for(int i = 0; i < nums.length; i++){
            while(nums[st.peek()] < nums[i]) res[st.pop()] = nums[i];
        }

        while(!st.isEmpty()){
            res[st.pop()] = -1;
        }
        return res;
    }
}
