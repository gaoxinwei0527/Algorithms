package LeetCode;

/**
 168. Excel Sheet Column Title

 Given a positive integer, return its corresponding column title as appear in an Excel sheet.

 For example:

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB
 Credits:
 Special thanks to @ifanchu for adding this problem and creating all test cases.
 */
public class L_Leetcode_168 {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 26){
            int reminder = n % 26;
            n /= 26;
            if(reminder == 0){
                sb.insert(0, 'Z');
                n--;
            }else{
                sb.insert(0, (char)('A' + reminder - 1));
            }
        }

        if(n > 0) sb.insert(0, (char)('A' + n - 1));
        return sb.toString();
    }

    /**
     * @param n
     * @return
     *
     * a cleaner version, instead of do 1 -> A ... 26 -> Z, do n-1 to make it 0 -> A, ... 25 -> Z;
     */
    public String convertToTitle2(int n) {
        String res = "";
        while(n != 0) {
            char ch = (char)((n - 1) % 26 + 65);
            n = (n - 1) / 26;
            res = ch + res;
        }
        return res;
    }
}
