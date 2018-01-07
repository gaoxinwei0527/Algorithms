package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 241. Different Ways to Add Parentheses

 Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.


 Example 1
 Input: "2-1-1".

 ((2-1)-1) = 0
 (2-(1-1)) = 2
 Output: [0, 2]


 Example 2
 Input: "2*3-4*5"

 (2*(3-(4*5))) = -34
 ((2*3)-(4*5)) = -14
 ((2*(3-4))*5) = -10
 (2*((3-4)*5)) = -10
 (((2*3)-4)*5) = 10
 Output: [-34, -14, -10, -10, 10]

 Credits:
 Special thanks to @mithmatt for adding this problem and creating all test cases.
 */
public class H_Leetcode_241 {
    Map<String, List<Integer>> cache = new HashMap<>();

    /**
     * @param input
     * @return
     *
     * divide & conquer, but need memo, kinda like dp, because there are repeated sub-problems.
     * recurse + memo, can also be written as bottom up
     *
     * Subproblem - S(i, j) means all possible results from input.substring(i, j)
     * Initial state - for each (i, j) that substring doesn't contain op, then S(i, j) = {Integer.parseInt(substring)};
     *
     * Recurrence-
     * S(i, j):
     *   for k in [i, j]
     *     if s[k] == '+' || s[k] == '-' || s[k] == '*':
     *        S(i, j) += permutation of (S(i, k - 1), S(k + 1, j), op);
     *
     * original problem: S(0, n - 1)
     * bottom up - dp[i][j] can store all result permutations of substring(i, j);
     */
    public List<Integer> diffWaysToCompute(String input) {
        if(input.length() == 0) return new ArrayList<>();
        if(cache.containsKey(input)) return cache.get(input);

        List<Integer> res = new ArrayList<>();
        if(!(input.contains("*") || input.contains("+") || input.contains("-"))){
            res.add(Integer.parseInt(input));
            cache.put(input, res);
            return res;
        }

        char[] arr = input.toCharArray();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '*' || arr[i] == '+' || arr[i] == '-'){
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for(int x : left){
                    for(int y : right){
                        res.add(calc(x, y, arr[i]));
                    }
                }
            }
        }

        cache.put(input, res);
        return res;
    }

    private int calc(int a, int b, char op){
        if(op == '*') return a * b;
        if(op == '+') return a + b;
        return a - b;
    }
}
