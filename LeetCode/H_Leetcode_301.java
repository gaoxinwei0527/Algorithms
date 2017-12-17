package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 301. Remove Invalid Parentheses

 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
 Credits:
 Special thanks to @hpplayer for adding this problem and creating all test cases.
 */
public class H_Leetcode_301 {
    /**
     * @param s
     * @return
     *
     * bfs, for each string with current length, remove each '(' & ')' to form a new String, until we find a valid string
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> level = new ArrayList<>();
        level.add(s);
        boolean found = false;
        while(!found){
            Set<String> visited = new HashSet<>();
            for(String n : level){
                if(valid(n)){
                    found = true;
                    break;
                }
                for(int i = 0; i <  n.length(); i++){
                    if(n.charAt(i) == '(' || n.charAt(i) == ')'){
                        String modified = new StringBuilder().append(n.substring(0, i)).append(n.substring(i + 1)).toString();
                        visited.add(modified);
                    }
                }
            }

            if(!found) level = new ArrayList<>(visited);
        }

        List<String> res = new ArrayList<>();
        for(String n : level){
            if(valid(n)) res.add(n);
        }
        return res;
    }

    private boolean valid(String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                count++;
            }else if(s.charAt(i) == ')'){
                if(count == 0) return false;
                count--;
            }
        }

        return count == 0;
    }
}
