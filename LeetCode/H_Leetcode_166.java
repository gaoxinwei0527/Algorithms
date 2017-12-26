package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 166. Fraction to Recurring Decimal

 Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

 If the fractional part is repeating, enclose the repeating part in parentheses.

 For example,

 Given numerator = 1, denominator = 2, return "0.5".
 Given numerator = 2, denominator = 1, return "2".
 Given numerator = 2, denominator = 3, return "0.(6)".
 Credits:
 Special thanks to @Shangrila for adding this problem and creating all test cases.
 */
public class H_Leetcode_166 {
    /**
     * @param numerator
     * @param denominator
     * @return
     *
     * steps to build the string-
     * 1. if num is 0, return "0"
     * 2. decide if negative, append '-'
     * 3. append integral part as num / den
     * 4. if num % den == 0, return current string
     * 5. get reminder num %= den, then record num & current position in hashmap.
     * 6. num *= 10, append num / den, and get next reminder num %= den, if num is already in map, means it's starting another repeating pattern.
     * then insert '(' to position map.get(num), append append ')' at end of current string, then we could return the result string.
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        if(numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) sb.append('-');
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        num %= den;
        if(num == 0) return sb.toString();

        sb.append('.');
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, sb.length());
        while(num > 0){
            num *= 10;
            sb.append(num / den);
            num %= den;
            if(map.containsKey(num)){
                sb.insert((int)map.get(num), "(");
                sb.append(')');
                return sb.toString();
            }
            map.put(num, sb.length());
        }

        return sb.toString();
    }
}
