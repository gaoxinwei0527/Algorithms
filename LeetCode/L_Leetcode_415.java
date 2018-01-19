package LeetCode;

/**
 415. Add Strings

 Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Note:

 The length of both num1 and num2 is < 5100.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class L_Leetcode_415 {
    public String addStrings(String num1, String num2) {
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int m = arr1.length;
        int n = arr2.length;

        char[] res = new char[(m > n ? m + 1 : n + 1)];
        int carry = 0;
        int i = m - 1;
        int j = n - 1;
        int k = res.length - 1;
        while(i >= 0 && j >= 0){
            int next = (arr1[i--] - '0') + (arr2[j--] - '0') + carry;
            res[k--] = (char)((next % 10) + '0');
            carry = next / 10;
        }

        while(i >= 0){
            int next = (arr1[i--] - '0') + carry;
            res[k--] = (char)((next % 10) + '0');
            carry = next / 10;
        }

        while(j >= 0){
            int next = (arr2[j--] - '0') + carry;
            res[k--] = (char)((next % 10) + '0');
            carry = next / 10;
        }

        if(carry == 0){
            return new String(res).substring(1);
        }else{
            res[0] = (char)(carry + '0');
            return new String(res);
        }
    }
}
