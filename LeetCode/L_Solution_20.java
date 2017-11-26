package LeetCode;

import java.util.Stack;

/**
 20. Valid Parentheses

 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class L_Solution_20 {
    /**
     * @param s
     * @return
     *
     * Use stack
     *
     * Time - O(n)
     * Space - O(n)
     */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '{' || c == '[' || c == '('){
                st.push(c);
            }else if(st.isEmpty() ||
                    (c == '}' && st.peek() != '{') ||
                    (c == ']' && st.peek() != '[') ||
                    (c == ')' && st.peek() != '(')){
                return false;
            }else{
                st.pop();
            }
        }

        return st.isEmpty();
    }
}
