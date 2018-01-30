package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 592. Fraction Addition and Subtraction

 Given a string representing an expression of fraction addition and subtraction, you need to return the calculation result in string format. The final result should be irreducible fraction. If your final result is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this case, 2 should be converted to 2/1.

 Example 1:
 Input:"-1/2+1/2"
 Output: "0/1"
 Example 2:
 Input:"-1/2+1/2+1/3"
 Output: "1/3"
 Example 3:
 Input:"1/3-1/2"
 Output: "-1/6"
 Example 4:
 Input:"5/3+1/3"
 Output: "2/1"
 Note:
 The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
 Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
 The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
 The number of given fractions will be in the range [1,10].
 The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
 */
public class M_Leetcode_592 {
    class Fraction{
        int num;
        int den;
        Fraction(int num, int den){
            this.num = num;
            this.den = den;
        }
    }

    public String fractionAddition(String expression) {
        List<Fraction> frac = new ArrayList<>();
        int i = 0;
        while(i < expression.length()){
            int j = i + 1;
            while(j < expression.length() && expression.charAt(j) != '+' && expression.charAt(j) != '-') j++;
            String next = expression.substring(i, j);
            String[] nums = next.split("/");
            Fraction f = new Fraction(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
            frac.add(f);
            i = j;
        }

        while(frac.size() > 1){
            Fraction a = frac.remove(frac.size() - 1);
            Fraction b = frac.remove(frac.size() - 1);
            frac.add(add(a, b));
        }

        Fraction res = frac.get(0);
        return "" + res.num + "/" + res.den;
    }

    private Fraction add(Fraction a, Fraction b){
        int k = gcd(a.den, b.den);
        int lcm = (a.den / k) * b.den;
        int num1 = a.num * (lcm / a.den);
        int num2 = b.num * (lcm / b.den);
        int num = num1 + num2;
        int k2 = gcd(Math.abs(num), lcm);
        num /= k2;
        lcm /= k2;
        return new Fraction(num, lcm);
    }

    private int gcd(int x, int y){
        if(x == 0) return y;
        return gcd(y % x, x);
    }
}
