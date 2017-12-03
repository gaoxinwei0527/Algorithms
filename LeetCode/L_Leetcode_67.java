package LeetCode;

/**
 67. Add Binary

 Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".
 */
public class L_Leetcode_67 {
    /**
     * @param a
     * @param b
     * @return
     *
     * take care of carry
     *
     * Time - O(n)
     * Space - O(n)
     */
    public String addBinary(String a, String b) {
        char[] arr_a = a.toCharArray();
        char[] arr_b = b.toCharArray();
        char[] res = new char[arr_a.length > arr_b.length ? (arr_a.length + 1) : (arr_b.length + 1)];
        int carry = 0;
        int i = arr_a.length - 1;
        int j = arr_b.length - 1;
        int k = res.length - 1;
        while(i >= 0 && j >= 0){
            int next = (int)(arr_a[i--] - '0') + (int)(arr_b[j--] - '0') + carry;
            res[k--] = (char)(next % 2 + '0');
            carry = next / 2;
        }

        while(i >= 0){
            int next = (int)(arr_a[i--] - '0') + carry;
            res[k--] = (char)(next % 2 + '0');
            carry = next / 2;
        }

        while(j >= 0){
            int next = (int)(arr_b[j--] - '0') + carry;
            res[k--] = (char)(next % 2 + '0');
            carry = next / 2;
        }

        res[k] = (char)(carry + '0');
        if(carry > 0) return new String(res);
        return new String(res).substring(1);
    }
}
