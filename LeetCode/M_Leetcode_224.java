package LeetCode;

import java.util.Stack;

/**
 224. Basic Calculator

 Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 You may assume that the given expression is always valid.

 Some examples:
 "1 + 1" = 2
 " 2-1 + 2 " = 3
 "(1+(4+5+2)-3)+(6+8)" = 23
 Note: Do not use the eval built-in library function.
 */
public class M_Leetcode_224 {
    /**
     * @param s
     * @return
     *
     * use stack to handle '(' & ')'
     */
    public int calculate(String s) {
        s = s.replaceAll("\\s+", "");
        if(s.length() == 0) return 0;
        Stack<String> st = new Stack<>();
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) == '(' || s.charAt(i) == '+' || s.charAt(i) == '-'){
                st.push(s.substring(i, i + 1));
                i++;
            }else if(s.charAt(i) != ')'){
                int j = i + 1;
                while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') j++;
                st.push(s.substring(i, j));
                i = j;
            }else{
                Stack<String> eval = new Stack<>();
                while(!st.peek().equals("(")){
                    eval.push(st.pop());
                }
                st.pop();
                int res = eval(eval);
                st.push("" + res);
                i++;
            }
        }

        Stack<String> eval = new Stack<>();
        while(!st.isEmpty()) eval.push(st.pop());
        return eval(eval);
    }

    private int eval(Stack<String> eval){
        int res = Integer.parseInt(eval.pop());
        while(!eval.isEmpty()){
            String op = eval.pop();
            int next = Integer.parseInt(eval.pop());
            if("-".equals(op)) res -= next;
            else res += next;
        }
        return res;
    }
}
