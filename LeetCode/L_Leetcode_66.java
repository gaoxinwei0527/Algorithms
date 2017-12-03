package LeetCode;

/**
 66. Plus One

 Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

 You may assume the integer do not contain any leading zero, except the number 0 itself.

 The digits are stored such that the most significant digit is at the head of the list.
 */
public class L_Leetcode_66 {
    /**
     * @param digits
     * @return
     *
     * take care of carry
     *
     * Time - O(n)
     * Space - worst O(n), best O(1), in case we need to have extra digit, then need to allocate a new array with length n + 1;
     */
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for(int i = digits.length - 1; i >= 0; i--){
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }

        if(carry > 0){
            int[] res = new int[digits.length + 1];
            res[0] = carry;
            System.arraycopy(digits, 0, res, 1, digits.length);
            return res;
        }

        return digits;
    }
}
