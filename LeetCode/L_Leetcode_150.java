package LeetCode;

import java.util.Stack;

/**
 150. Evaluate Reverse Polish Notation

 Evaluate the value of an arithmetic expression in Reverse Polish Notation.

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class L_Leetcode_150 {
    /**
     * @param tokens
     * @return
     *
     * use stack, whenever get operator, process it.
     *
     * Time - O(n), n means token number
     * Space - O(n)
     */
    public int evalRPN(String[] tokens) {
        Stack<String> st = new Stack<>();
        for(String token : tokens){
            if("+".equals(token)){
                st.push(Integer.toString(Integer.parseInt(st.pop()) + Integer.parseInt(st.pop())));
            }else if("-".equals(token)){
                int b = Integer.parseInt(st.pop());
                int a = Integer.parseInt(st.pop());
                st.push(Integer.toString(a - b));
            }else if("*".equals(token)){
                st.push(Integer.toString(Integer.parseInt(st.pop()) * Integer.parseInt(st.pop())));
            }else if("/".equals(token)){
                int b = Integer.parseInt(st.pop());
                int a = Integer.parseInt(st.pop());
                st.push(Integer.toString(a / b));
            }else{
                st.push(token);
            }
        }
        return Integer.parseInt(st.pop());
    }
}
