package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 159. Longest Substring with At Most Two Distinct Characters

 Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

 For example, Given s = “eceba”,

 T is "ece" which its length is 3.
 */
public class L_Leetcode_159 {
    /**
     * @param s
     * @return
     *
     * sliding window, make the window maintain a substring with at most 2 distinct characters.
     * init state - get max j that [0, j) window contains at most 2 distinct characters, then we get max initialized as j
     * iterate - move j -> j + 1, if break window condition, keep moving i until it's satisfied again. Update max with j - i
     *
     * Time - O(n)
     * Space -O(n), can be optimized
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> window = new HashMap<>();
        char[] arr = s.toCharArray();
        int i = 0;
        int j = 0;

        while(j < arr.length && (window.size() < 2 || window.containsKey(arr[j]))){
            char c = arr[j++];
            window.put(c, window.getOrDefault(c, 0) + 1);
        }
        int max = j;

        while(j < arr.length){
            char c = arr[j++];
            window.put(c, window.getOrDefault(c, 0) + 1);

            while(window.size() > 2){
                char a = arr[i++];
                window.put(a, window.get(a) - 1);
                if(window.get(a) == 0) window.remove(a);
            }

            max = Math.max(max, j - i);
        }

        return max;
    }
}
