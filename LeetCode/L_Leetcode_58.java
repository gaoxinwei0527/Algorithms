package LeetCode;

/**
 58. Length of Last Word

 Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 Example:

 Input: "Hello World"
 Output: 5
 */
public class L_Leetcode_58 {
    /**
     * @param s
     * @return
     *
     * simple count
     *
     * Time - O(m), m means the length of last word
     * Space - O(1)
     */
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if(s.length() == 0) return 0;
        int i = s.length() - 1;
        while(i >= 0 && s.charAt(i) != ' '){
            i--;
        }
        return s.length() - 1 - i;
    }
}
