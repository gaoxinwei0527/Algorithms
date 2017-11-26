package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 22. Generate Parentheses

 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class M_Solution_22 {
    /**
     * @param n
     * @return
     *
     * dfs + backtrack, every time add a '(' or ')' to tmp string.
     */
    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        helper(res, sb, 0, n);
        return new ArrayList<>(res);
    }

    private void helper(List<String> res, StringBuilder sb, int k, int n){
        if(sb.length() == n * 2){
            res.add(sb.toString());
            return;
        }

        if(k < n){
            sb.append('(');
            helper(res, sb, k + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(sb.length() < k * 2){
            sb.append(')');
            helper(res, sb, k, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * @param n
     * @return
     *
     * Iterative way-
     * For the result of f(n-1), we can deduce f(n) by-
     * 1. insert "()" after each '(', for all the strings in f(n-1);
     * 2. add "()" to the start of each string in f(n-1);
     *
     * We can insert "()" to any other places, but that would only generate duplicate results.
     */
    public List<String> generateParenthesis2(int n) {
        Set<String> res = new HashSet<>();
        res.add("");
        for(int i = 0; i < n; i++){
            Set<String> next = new HashSet<>();
            for(String s : res){
                next.add("()" + s);
                for(int k = 0; k < s.length(); k++){
                    if(s.charAt(k) == '('){
                        next.add(s.substring(0, k + 1) + "()" + s.substring(k + 1));
                    }
                }
            }
            res = next;
        }

        return new ArrayList<>(res);
    }
}
