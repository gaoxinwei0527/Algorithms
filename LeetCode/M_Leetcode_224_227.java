package LeetCode;

import java.util.Stack;

public class M_Leetcode_224_227 {
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

    /**
     227. Basic Calculator II

     Implement a basic calculator to evaluate a simple expression string.

     The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

     You may assume that the given expression is always valid.

     Some examples:
     "3+2*2" = 7
     " 3/2 " = 1
     " 3+5 / 2 " = 5
     Note: Do not use the eval built-in library function.
     */

    /**
     * @param s
     * @return
     *
     * calculate '*' and '/' firstly, then leave '+' and '-' in stack and calculate later.
     */
    public int calculate2(String s) {
        s = s.replaceAll("\\s+", "");
        if(s.length() == 0) return 0;
        Stack<String> st = new Stack<>();
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i) == '+' || s.charAt(i) == '-'){
                st.push(s.substring(i, i + 1));
                i++;
            }else if(s.charAt(i) == '*' || s.charAt(i) == '/'){
                char op = s.charAt(i);
                int num1 = Integer.parseInt(st.pop());
                i++;
                int j = i + 1;
                while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') j++;
                int num2 = Integer.parseInt(s.substring(i, j));
                int res = 0;
                if(op == '*') res = num1 * num2;
                else res = num1 / num2;
                st.push("" + res);
                i = j;
            }else{
                int j = i + 1;
                while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') j++;
                st.push(s.substring(i, j));
                i = j;
            }
        }

        Stack<String> eval = new Stack<>();
        while(!st.isEmpty()) eval.push(st.pop());
        int res = Integer.parseInt(eval.pop());
        while(!eval.isEmpty()){
            String op = eval.pop();
            int next = Integer.parseInt(eval.pop());
            if("+".equals(op)) res += next;
            else res -= next;
        }
        return res;
    }
}
