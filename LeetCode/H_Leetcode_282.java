package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 282. Expression Add Operators

 Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []
 Credits:
 Special thanks to @davidtan1890 for adding this problem and creating all test cases.
 */
public class H_Leetcode_282 {
    /**
     * @param num
     * @param target
     * @return
     *
     * dfs, the trick is, when we try to * some num, we need to minus previous added num, multiply the num with current processing num, and add the result back.
     * because '*' has higher priority than '+' & '-', it will happen first.
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num.length() == 0) return res;
        helper(res, num, "", 0, 0, target, 0);
        return res;
    }

    private void helper(List<String> res, String num, String build, int cur, long tmp, long target, long prev){
        if(cur == num.length()){
            if(tmp == target){
                res.add(build);
            }
            return;
        }

        if(cur == 0){
            if(num.charAt(cur) == '0'){
                helper(res, num, "0", cur + 1, 0, target, 0);
            }else{
                for(int i = cur + 1; i <= num.length(); i++){
                    long next = Long.parseLong(num.substring(cur, i));
                    helper(res, num, num.substring(cur, i), i, next, target, next);
                }
            }
        }else{
            if(num.charAt(cur) == '0'){
                helper(res, num, build + "+0", cur + 1, tmp, target, 0);
                helper(res, num, build + "-0", cur + 1, tmp, target, 0);
                helper(res, num, build + "*0", cur + 1, tmp - prev, target, 0);
            }else{
                for(int i = cur + 1; i <= num.length(); i++){
                    long next = Long.parseLong(num.substring(cur, i));
                    helper(res, num, build + "+" + next, i, tmp + next, target, next);
                    helper(res, num, build + "-" + next, i, tmp - next, target, -next);
                    helper(res, num, build + "*" + next, i, tmp - prev + (prev * next), target, prev * next);
                }
            }
        }
    }
}
