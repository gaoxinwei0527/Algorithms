package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 736. Parse Lisp Expression

 You are given a string expression representing a Lisp-like expression to return the integer value of.

 The syntax for these expressions is given as follows.

 An expression is either an integer, a let-expression, an add-expression, a mult-expression, or an assigned variable. Expressions always evaluate to a single integer.
 (An integer could be positive or negative.)
 A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let", then there are 1 or more pairs of alternating variables and expressions, meaning that the first variable v1 is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2, and so on sequentially; and then the value of this let-expression is the value of the expression expr.
 An add-expression takes the form (add e1 e2) where add is always the string "add", there are always two expressions e1, e2, and this expression evaluates to the addition of the evaluation of e1 and the evaluation of e2.
 A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always two expressions e1, e2, and this expression evaluates to the multiplication of the evaluation of e1 and the evaluation of e2.
 For the purposes of this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits. Additionally for your convenience, the names "add", "let", or "mult" are protected and will never be used as variable names.
 Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal. Please see the examples for more details on scope.
 Evaluation Examples:
 Input: (add 1 2)
 Output: 3

 Input: (mult 3 (add 2 3))
 Output: 15

 Input: (let x 2 (mult x 5))
 Output: 10

 Input: (let x 2 (mult x (let x 3 y 4 (add x y))))
 Output: 14
 Explanation: In the expression (add x y), when checking for the value of the variable x,
 we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
 Since x = 3 is found first, the value of x is 3.

 Input: (let x 3 x 2 x)
 Output: 2
 Explanation: Assignment in let statements is processed sequentially.

 Input: (let x 1 y 2 x (add x y) (add x y))
 Output: 5
 Explanation: The first (add x y) evaluates as 3, and is assigned to x.
 The second (add x y) evaluates as 3+2 = 5.

 Input: (let x 2 (add (let x 3 (let x 4 x)) x))
 Output: 6
 Explanation: Even though (let x 4 x) has a deeper scope, it is outside the context
 of the final x in the add-expression.  That final x will equal 2.

 Input: (let a1 3 b2 (add a1 1) b2)
 Output 4
 Explanation: Variable names can contain digits after the first character.

 Note:

 The given string expression is well formatted: There are no leading or trailing spaces, there is only a single space separating different components of the string, and no space between adjacent parentheses. The expression is guaranteed to be legal and evaluate to an integer.
 The length of expression is at most 2000. (It is also non-empty, as that would not be a legal expression.)
 The answer and all intermediate calculations of that answer are guaranteed to fit in a 32-bit integer.
 */
public class H_Leetcode_736 {
    /**
     * @param expression
     * @return
     *
     * trick - need to take care of the scope of the variables.
     * for each 'let' expression, we should have a new map with all existing variables and we can put in / overwrite new variable values.
     * and use the new map to evaluate expression inside the current 'let' scope. These variable assignment inside 'let' scope should not be escaped to outside.
     */
    public int evaluate(String expression) {
        Map<String, Integer> map = new HashMap<>();
        return helper(expression, map);
    }

    private int helper(String expression, Map<String, Integer> map){
        if(expression.charAt(0) == '(') expression = expression.substring(1, expression.length() - 1);
        expression = expression.trim();
        if(expression.startsWith("let")){
            List<String> tokens = split(expression.substring(4));
            Map<String, Integer> newScope = new HashMap<>(map);
            for(int i = 0; i < tokens.size() - 1; i += 2){
                String key = tokens.get(i);
                int val = helper(tokens.get(i + 1), newScope);
                newScope.put(key, val);
            }
            return helper(tokens.get(tokens.size() - 1), newScope);
        }else if(expression.startsWith("add")){
            List<String> tokens = split(expression.substring(4));
            int a = helper(tokens.get(0), map);
            int b = helper(tokens.get(1), map);
            return a + b;
        }else if(expression.startsWith("mult")){
            List<String> tokens = split(expression.substring(5));
            int a = helper(tokens.get(0), map);
            int b = helper(tokens.get(1), map);
            return a * b;
        }else if(expression.charAt(0) >= '0' && expression.charAt(0) <= '9' || expression.charAt(0) == '-'){
            return Integer.parseInt(expression);
        }else{
            return map.get(expression);
        }
    }

    private List<String> split(String expression){
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while(i < expression.length()){
            if(expression.charAt(i) == '('){
                int count = 1;
                int j = i + 1;
                while(count > 0){
                    if(expression.charAt(j) == '(') count++;
                    else if(expression.charAt(j) == ')') count--;
                    j++;
                }
                tokens.add(expression.substring(i, j));
                i = j + 1;
            }else{
                int j = i;
                while(j < expression.length() && expression.charAt(j) != ' ') j++;
                tokens.add(expression.substring(i, j));
                i = j + 1;
            }
        }
        return tokens;
    }
}
