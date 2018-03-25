package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 76. Minimum Window Substring

 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".

 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class M_Leetcode_76 {
    /**
     * @param s
     * @param t
     * @return
     *
     * two pointers
     */
    public String minWindow(String s, String t) {
        if(t.length() == 0 || s.length() == 0) return "";
        Map<Character, Integer> sub = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            sub.put(t.charAt(i), sub.getOrDefault(t.charAt(i), 0) + 1);
        }

        int i = 0;
        int j = 0;
        String res = null;
        while(j < s.length()){
            while(j < s.length() && !check(sub)){
                char next = s.charAt(j);
                if(sub.containsKey(next)){
                    sub.put(next, sub.get(next) - 1);
                }
                j++;
            }

            if(check(sub)){
                while(check(sub)){
                    char next = s.charAt(i);
                    if(sub.containsKey(next)){
                        sub.put(next, sub.get(next) + 1);
                    }
                    i++;
                }

                if(res == null || res.length() > (j - i + 1)){
                    res = s.substring(i - 1, j);
                }
            }
        }

        return res == null ? "" : res;
    }

    private boolean check(Map<Character, Integer> sub){
        for(int v : sub.values()){
            if(v > 0) return false;
        }

        return true;
    }

    /**
     * @param s
     * @param t
     * @return
     *
     * use int[] as hashmap.
     */
    public String minWindow2(String s, String t) {
        if(s.length() == 0 || t.length() == 0) return "";
        int[] count = new int[128];
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        for(char c : arr2) count[(int) c]++;
        int size = Integer.MAX_VALUE;
        int x = -1;
        int y = -1;
        int i = -1;
        int j = 0;
        while(j < arr1.length){
            if(i != -1) count[(int) arr1[i]]++;
            i++;
            while(!check(count) && j < arr1.length) count[(int) arr1[j++]]--;
            if(!check(count)) break;
            while(count[(int) arr1[i]] < 0) count[(int) arr1[i++]]++;
            if(j - i < size){
                x = i;
                y = j;
                size = j - i;
            }
        }

        if(size == Integer.MAX_VALUE) return "";
        return s.substring(x, y);
    }

    private boolean check(int[] count){
        for(int i : count){
            if(i > 0) return false;
        }

        return true;
    }
}
