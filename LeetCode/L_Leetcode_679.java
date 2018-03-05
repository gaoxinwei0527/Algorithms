package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 679. 24 Game

 You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

 Example 1:
 Input: [4, 1, 8, 7]
 Output: True
 Explanation: (8-4) * (7-1) = 24
 Example 2:
 Input: [1, 2, 1, 2]
 Output: False
 Note:
 The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class L_Leetcode_679 {
    /**
     * @param nums
     * @return
     *
     * try all combinations as there are only 4 nums
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> d_nums = new ArrayList<>();
        for(int i : nums) d_nums.add((double) i);
        return judge(d_nums);
    }

    private boolean judge(List<Double> nums){
        if(nums.size() == 1){
            return Math.abs(nums.get(0) - 24.0) < 0.00001;
        }

        for(int i = 0; i < nums.size() - 1; i++){
            for(int j = i + 1; j < nums.size(); j++){
                double a = nums.get(i);
                double b = nums.get(j);
                List<Double> base = new ArrayList<>(nums);
                base.remove(j);
                base.remove(i);
                if(judge(construct(base, a + b))) return true;
                if(judge(construct(base, a * b))) return true;
                if(judge(construct(base, a - b))) return true;
                if(judge(construct(base, b - a))) return true;
                if(b != 0 && judge(construct(base, a / b))) return true;
                if(a != 0 && judge(construct(base, b / a))) return true;
            }
        }
        return false;
    }

    private List<Double> construct(List<Double> base, double num){
        List<Double> next = new ArrayList<>(base);
        next.add(num);
        return next;
    }
}
