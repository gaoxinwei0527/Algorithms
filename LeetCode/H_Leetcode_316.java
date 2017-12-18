package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 316. Remove Duplicate Letters

 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class H_Leetcode_316 {
    /*
    Greedy
    1. for s, get smallest char c that satisfies-
    right side of c include all other characters
    2. append c to tmp string builder, remove all c occurence, and remove left side of leftmost c
    3. recursively call to resolve the remaining string
    */
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0) return s;
        Set<Character> contains = new HashSet<>();
        int[] count = new int[s.length()];
        for(int i = s.length() - 1; i >= 0; i--){
            contains.add(s.charAt(i));
            count[i] = contains.size();
        }

        int i = 0;
        char c = s.charAt(0);
        int cur = 0;
        while(i < s.length() - 1 && count[i + 1] == count[i]){
            i++;
            if((int)(c - s.charAt(i)) > 0){
                c = s.charAt(i);
                cur = i;
            }
        }

        s = s.substring(cur + 1).replaceAll("" + c, "");
        return "" + c + removeDuplicateLetters(s);
    }
}
