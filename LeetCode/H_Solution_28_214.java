package LeetCode;


public class H_Solution_28_214 {
    /**
     28. Implement strStr()

     Implement strStr().

     Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

     Example 1:

     Input: haystack = "hello", needle = "ll"
     Output: 2
     Example 2:

     Input: haystack = "aaaaa", needle = "bba"
     Output: -1
     */

    /**
     * @param haystack
     * @param needle
     * @return
     *
     * simple iterative and compare way
     *
     * Time - O(n * m)
     * Space - O(n + m), this space usage can be saved by operate on String instead of char[]
     */
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        for(int i = 0; i <= h.length - n.length; i++){
            if(h[i] == n[0]){
                boolean found = true;
                for(int j = 0; j < n.length; j++){
                    if(h[i + j] != n[j]){
                        found = false;
                        break;
                    }
                }

                if(found) return i;
            }
        }

        return -1;
    }

    /**
     * @param haystack
     * @param needle
     * @return
     *
     * KMP
     * for each i in haystack, we try to find the max j in needle, where haystack[i-1-j, i-1] == needle[0, j] && haystack[i] == needle[j+1],
     * then we can conclude that, regard haystack[i] as the last character in substring, at most j+2 characters (0 to j+1) can be matched, means
     * haystack[i-j-1, i] == needle[0, j+1];
     *
     * To find max j where haystack[i-1-j, i-1] == needle[0, j], we need to preprocess needle.
     * for each index a in needle, we need to find max index b in needle, where a > b && needle[0 - b] == needle[a-1-b, a-1] && needle[b+1] == needle[a];
     * then we can set match[a] = b;
     *
     * This finding process is recursive, say we are processing a, then we look at match[a - 1], if needle[match[a - 1] + 1] != needle[a],
     * then we need to shrink the matching size. Because needle[0, match[a - 1]] can match needle[a-1-match[a-1], a-1], then we shrink it to
     * needle[0, match[match[a-1]]], which will also match needle[a-1-match[match[a-1]], a-1], but the window will be smaller, then we can check
     * if needle[match[match[a-1]] + 1] == needle[a], etc..
     *
     * If needle[a] cannot match anything in needle, match[a] = -1.
     * Since we are looking for b < a, so if a == 0, match[a] = -1, we can start preprocessing iteration at a = 1;
     *
     * Reference - http://www.matrix67.com/blog/archives/115
     *
     * Time - O(n)
     * Space - O(n + m), can be saved
     *
     * Tip - the two for loops and preprocessed match array should all be 1-based.
     *
     */
    public int strStr2(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        if(haystack.length() == 0) return -1;
        char[] a = haystack.toCharArray();
        char[] b = needle.toCharArray();
        int[] match = preprocess(b);
        int j = 0;
        for(int i = 1; i <= a.length; i++){
            while(j > 0 && a[i - 1] != b[j]) j = match[j];
            if(a[i - 1] == b[j]) {
                j++;
                if(j == b.length) return (i - j);
            }else{
                j = 0;
            }
        }
        return -1;
    }

    /*====================================================================================================*/

    /**
     214. Shortest Palindrome

     Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

     For example:

     Given "aacecaaa", return "aaacecaaa".

     Given "abcd", return "dcbabcd".

     Credits:
     Special thanks to @ifanchu for adding this problem and creating all test cases. Thanks to @Freezen for additional test cases.
     */

    /**
     * @param s
     * @return
     *
     * accepted naive way.
     * if we want to minimize the added chars, then we need to find largest j, where s[0, j] is a palindrome,
     * then we just need to add reverse(s[j + 1, len - 1]);
     * try every j from the right to left, whenever we find such a j, we build the result string and return.
     * worst case is single s[0] is longest palindrome starts from 0.
     *
     * Time - O(n ^ 2), where n is length of s
     * Space - O(n), can be saved.
     */
    public String shortestPalindrome(String s) {
        char[] arr = s.toCharArray();
        for(int j = arr.length - 1; j >= 0; j--){
            if(isPalindrome(arr, 0, j)){
                if(j == arr.length - 1) return s;
                StringBuilder sb = new StringBuilder();
                sb.append(s.substring(j + 1)).reverse();
                sb.append(s);
                return sb.toString();
            }
        }
        return ""; // not reachable
    }

    private boolean isPalindrome(char[] arr, int i, int j){
        while(i < j){
            if(arr[i++] != arr[j--]) return false;
        }
        return true;
    }

    /**
     * @param s
     * @return
     *
     * KMP way
     * After KMP preprocessing, match[i] means the max length of prefix / postfix that starts from 0 / end with ith char can match.
     * We can utilize this feature, by constructing a string b- (s + '#' + reverse(s)) and do KMP preprocess on b;
     * then match[b.length] would be the max length of prefix of s that can match its reversed postfix :)
     * in another word, match[b.length] would be the max length of palindrome prefix of s. cool!
     * then we know what we should do, build the result string.
     *
     * since KMP preprocess is O(n), so even we double the string size (by append reversed s), we still get O(n) time
     * Space - O(n)
     */
    public String shortestPalindrome2(String s) {
        char[] arr = (s + '#' + new StringBuilder(s).reverse().toString()).toCharArray();
        int[] match = preprocess(arr);
        int len = match[arr.length];
        return (new StringBuilder(s.substring(len)).reverse() + s);
    }

    private int[] preprocess(char[] b){
        int[] match = new int[b.length + 1];
        match[1] = 0;
        for(int i = 2; i <= b.length; i++){
            int j = match[i - 1];
            while(j > 0 && b[j] != b[i - 1]) j = match[j];
            if(b[j] == b[i - 1]) match[i] = j + 1;
            else match[i] = 0;
        }
        return match;
    }
}
