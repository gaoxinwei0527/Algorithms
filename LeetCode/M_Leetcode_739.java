package LeetCode;

import java.util.Stack;

/**
 739. Daily Temperatures

 Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

 For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

 Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class M_Leetcode_739 {
    /**
     * @param temperatures
     * @return
     *
     * use stack
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!st.isEmpty() && temperatures[st.peek()] < temperatures[i]){
                res[st.peek()] = (i - st.pop());
            }
            st.push(i);
        }
        return res;
    }
}
