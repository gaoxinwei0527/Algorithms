package LeetCode;

/**
 43. Multiply Strings

 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

 Note:

 The length of both num1 and num2 is < 110.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class L_Leetcode_43 {
    /**
     * @param num1
     * @param num2
     * @return
     *
     * finish the multiply first and store temporary result without carry in an int array, then iterate to handle the carry.
     *
     * Time - O(m * n)
     * Space - O(m + n)
     */
    public String multiply(String num1, String num2) {
        char[] a = num1.toCharArray();
        char[] b = num2.toCharArray();
        int m = a.length;
        int n = b.length;

        int[] res = new int[m + n];
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                res[i + j + 1] += ((int)(a[i] - '0') * (int)(b[j] - '0'));
            }
        }

        int carry = 0;
        for(int i = m + n - 1; i >= 0; i--){
            int next = res[i] + carry;
            res[i] = next % 10;
            carry = next / 10;
        }

        int i = 0;
        while(i < m + n && res[i] == 0) i++;
        if(i == m + n) return "0";
        StringBuilder sb = new StringBuilder();
        while(i < m + n){
            sb.append(res[i++]);
        }

        return sb.toString();
    }
}
