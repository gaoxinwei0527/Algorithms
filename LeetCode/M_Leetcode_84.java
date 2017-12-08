package LeetCode;

import java.util.Stack;

/**
 84. Largest Rectangle in Histogram

 Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

 For example,
 Given heights = [2,1,5,6,2,3],
 return 10.
 */
public class M_Leetcode_84 {
    /**
     * @param heights
     * @return
     *
     * use stack to keep the indexes in stack ascending in height
     *
     * Time - O(n)
     * Space - O(n)
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int max = 0;
        for(int i = 0; i < heights.length; i++){
            int h = heights[i];
            if(st.peek() == -1 || h >= heights[st.peek()]){
                st.push(i);
            }else{
                while(st.peek() != -1 && heights[st.peek()] > h){
                    int next = st.pop();
                    max = Math.max((i - st.peek() - 1) * heights[next], max);
                }
                st.push(i);
            }
        }

        while(st.peek() != -1){
            int next = st.pop();
            max = Math.max((heights.length - st.peek() - 1) * heights[next], max);
        }

        return max;
    }
}
