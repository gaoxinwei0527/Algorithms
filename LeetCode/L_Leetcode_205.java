package LeetCode;

import java.util.Arrays;

/**
 205. Isomorphic Strings

 Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

 For example,
 Given "egg", "add", return true.

 Given "foo", "bar", return false.

 Given "paper", "title", return true.
 */
public class L_Leetcode_205 {
    /**
     * @param s
     * @param t
     * @return
     *
     * map chars in s to chars in t, also map chars in t to chars in s.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] map = new int[256];
        int[] map2 = new int[256];
        Arrays.fill(map, -1);
        Arrays.fill(map2, -1);
        for(int i = 0; i < s.length(); i++){
            int a = (int)s.charAt(i);
            int b = (int)t.charAt(i);
            if(map[a] != -1 && map[a] != b || map2[b] != -1 && map2[b] != a) return false;
            if(map[a] == -1) map[a] = b;
            if(map2[b] == -1) map2[b] = a;
        }
        return true;
    }

    /**
     * @param s
     * @param t
     * @return
     *
     * slightly faster way, instead of map chars to each other, map both chars to index in strings.
     * if mapped index are different, return false;
     * Same time & space usage as above.
     */
    public boolean isIsomorphic2(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] map = new int[256];
        int[] map2 = new int[256];
        for(int i = 0; i < s.length(); i++){
            int a = (int)s.charAt(i);
            int b = (int)t.charAt(i);
            if(map[a] != map2[b]) return false;
            map[a] = i + 1; // use i + 1 because i could be 0, which is same as default value
            map2[b] = i + 1;
        }
        return true;
    }
}
