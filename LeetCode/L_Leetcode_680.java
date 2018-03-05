package LeetCode;

/**
 680. Valid Palindrome II

 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class L_Leetcode_680 {
    public boolean validPalindrome(String s) {
        int n = s.length();
        if(n <= 2) return true;
        char[] arr = s.toCharArray();
        int i = 0;
        int j = n - 1;
        while(i < j){
            if(arr[i] != arr[j]){
                return isValid(arr, i + 1, j) || isValid(arr, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isValid(char[] arr, int i, int j){
        while(i < j){
            if(arr[i++] != arr[j--]) return false;
        }
        return true;
    }
}
