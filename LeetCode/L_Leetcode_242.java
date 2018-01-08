package LeetCode;

/**
 242. Valid Anagram

 Given two strings s and t, write a function to determine if t is an anagram of s.

 For example,
 s = "anagram", t = "nagaram", return true.
 s = "rat", t = "car", return false.

 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class L_Leetcode_242 {
    /**
     * @param s
     * @param t
     * @return
     *
     * count char frequency
     *
     * Time - O(m + n)
     * Space - O(1)
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            int k = (int)(s.charAt(i) - 'a');
            count[k]++;
        }

        for(int i = 0; i < t.length(); i++){
            int k = (int)(t.charAt(i) - 'a');
            count[k]--;
            if(count[k] < 0) return false;
        }

        return true;
    }
}
