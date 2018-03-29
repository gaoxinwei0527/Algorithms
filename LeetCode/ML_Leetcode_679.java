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
public class ML_Leetcode_679 {
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

    private static class Number{
        public int num;
        public int dem;
        public Number(int num, int dem){
            this.num = num;
            this.dem = dem;
        }
    }

    /**
     * @param nums
     * @return
     *
     * instead of double, we can use fraction number to prevent any precision loss.
     */
    public boolean judgePoint24_2(int[] nums) {
        List<Number> list = new ArrayList<>();
        for(int i : nums) list.add(new Number(i, 1));
        return helper(list);
    }

    private boolean helper(List<Number> list){
        if(list.size() == 1){
            if(list.get(0).num == 24 && list.get(0).dem == 1) return true;
            return false;
        }

        for(int i = 0; i < list.size() - 1; i++){
            for(int j = i + 1; j < list.size(); j++){
                List<Number> next = new ArrayList<>(list);
                Number a = next.remove(j);
                Number b = next.remove(i);
                Number k1 = plus(a, b);
                Number k2 = minus(a, b);
                Number k3 = minus(b, a);
                Number k4 = multiply(a, b);

                next.add(k1);
                if(helper(next)) return true;
                next.remove(next.size() - 1);

                next.add(k2);
                if(helper(next)) return true;
                next.remove(next.size() - 1);

                next.add(k3);
                if(helper(next)) return true;
                next.remove(next.size() - 1);

                next.add(k4);
                if(helper(next)) return true;
                next.remove(next.size() - 1);

                if(b.num != 0){
                    Number k5 = divide(a, b);
                    next.add(k5);
                    if(helper(next)) return true;
                    next.remove(next.size() - 1);
                }

                if(a.num != 0){
                    Number k6 = divide(b, a);
                    next.add(k6);
                    if(helper(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }

        return false;
    }

    private Number plus(Number a, Number b){
        int dem = a.dem * b.dem;
        int num = a.num * b.dem + b.num * a.dem;
        boolean neg = ((dem * num) < 0);
        dem = Math.abs(dem);
        num = Math.abs(num);
        int gcd = gcd(num, dem);
        dem /= gcd;
        num /= gcd;
        return new Number((neg ? -num : num), dem);
    }

    private Number minus(Number a, Number b){
        int dem = a.dem * b.dem;
        int num = a.num * b.dem - b.num * a.dem;
        boolean neg = ((dem * num) < 0);
        dem = Math.abs(dem);
        num = Math.abs(num);
        int gcd = gcd(num, dem);
        dem /= gcd;
        num /= gcd;
        return new Number((neg ? -num : num), dem);
    }

    private Number multiply(Number a, Number b){
        int dem = a.dem * b.dem;
        int num = a.num * b.num;
        boolean neg = ((dem * num) < 0);
        dem = Math.abs(dem);
        num = Math.abs(num);
        int gcd = gcd(num, dem);
        dem /= gcd;
        num /= gcd;
        return new Number((neg ? -num : num), dem);
    }

    private Number divide(Number a, Number b){
        int dem = a.dem * b.num;
        int num = a.num * b.dem;
        boolean neg = ((dem * num) < 0);
        dem = Math.abs(dem);
        num = Math.abs(num);
        int gcd = gcd(num, dem);
        dem /= gcd;
        num /= gcd;
        return new Number((neg ? -num : num), dem);
    }

    private int gcd(int x, int y){
        if(x > y) return gcd(y, x);
        if(x == 0) return y;
        return gcd(y % x, x);
    }
}
