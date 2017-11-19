package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 3. Longest Substring Without Repeating Characters

 Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class L_Solution_3 {
    /**
     * @param s
     * @return
     *
     * use hash set to maintain the characters in window.
     * Whenever get a character which exists in window, then shrink the window from left side to remove the duplicate.
     *
     * Time - O(n)
     * Space - O(n + k), k is length of longest substring
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        char[] c = s.toCharArray();
        int i = 0;
        int j = 0;
        int res = 0;
        while(j < c.length){
            while(j < c.length && !window.contains(c[j])){
                window.add(c[j]);
                j++;
            }

            res = Math.max(res, j - i);
            if(j < c.length){
                while(c[i] != c[j]){
                    window.remove(c[i]);
                    i++;
                }

                window.remove(c[i]);
                i++;
            }
        }

        return res;
    }

    /**
     * @param s
     * @return
     *
     * To reduce space to O(n), we can maintain the window without hashset.
     * l means the left side of last window
     * for each index r, we start search c[r] from l, say we find j which c[j] == c[r], then there are two cases-
     * 1. j < r, means j+1 is the left side of current window, then update l as j+1, and update res
     * 2. j == r, means c[r] is unique in c[l, r], then keep l and update res
     */
    public int lengthOfLongestSubstring2(String s) {
        if(s.length() == 0) return 0;
        char[] c = s.toCharArray();
        int l = 0;
        int res = 1;
        for(int r = 1; r < c.length; r++){
            int j = l;
            while(j < r && c[j] != c[r]){
                j++;
            }

            if(j == r){
                res = Math.max(res, r - l + 1);
            }else{
                res = Math.max(res, r - j);
                l = j + 1;
            }
        }

        return res;
    }
}
