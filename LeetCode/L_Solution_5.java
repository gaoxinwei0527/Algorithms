package LeetCode;

/**
 5. Longest Palindromic Substring

 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:

 Input: "babad"

 Output: "bab"

 Note: "aba" is also a valid answer.
 Example:

 Input: "cbbd"

 Output: "bb"

 */
public class L_Solution_5 {
    /**
     * @param s
     * @return
     *
     * maintain boolean[][] dp, where dp[i][j] means if s.substring(i, j+1) is palindrome.
     * keep updating the max length of substring and corresponding start point of substring
     *
     * Time - O(n ^ 2)
     * Space - O(n ^ 2)
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n <= 1) return s;
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int max = 0;
        int left = -1;
        for(int len = 1; len <= n; len++){
            for(int i = 0; i <= n - len; i++){
                if(arr[i] == arr[i + len - 1] && (len <= 2 || dp[i + 1][i + len - 2])){
                    dp[i][i + len - 1] = true;
                    if(len > max){
                        max = len;
                        left = i;
                    }
                }
            }
        }

        return s.substring(left, left + max);
    }


    private int l, maxLen;

    /**
     * @param s
     * @return
     *
     * brute force, expand from each character in s as if it is middle of the palindrome.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(l, l + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }

        if (maxLen < k - j - 1) {
            l = j + 1;
            maxLen = k - j - 1;
        }
    }
}
