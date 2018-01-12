package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
public class H_Leetcode_496 {
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
}
