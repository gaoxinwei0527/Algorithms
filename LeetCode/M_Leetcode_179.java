package LeetCode;

import java.util.Arrays;
import java.util.Comparator;

/**
 179. Largest Number

 Given a list of non negative integers, arrange them such that they form the largest number.

 For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

 Note: The result may be very large, so you need to return a string instead of an integer.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class M_Leetcode_179 {
    /**
     * @param nums
     * @return
     *
     * sort based upon concatenated Strings.
     *
     * Time - O(nlogn), where n is number of nums
     * Space - O(n)
     */
    public String largestNumber(int[] nums) {
        if(nums.length == 0) return "";
        String[] converted = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            converted[i] = "" + nums[i];
        }
        Arrays.sort(converted, (a, b) -> {
            String x = a + b;
            String y = b + a;
            return y.compareTo(x);
        });

        if("0".equals(converted[0])) return "0";
        StringBuilder res = new StringBuilder();
        for(String i : converted){
            res.append(i);
        }

        return res.toString();
    }
}
