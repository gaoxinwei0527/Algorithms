package LeetCode;

import java.util.Collections;
import java.util.List;

/**
 524. Longest Word in Dictionary through Deleting

 Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 Example 1:
 Input:
 s = "abpcplea", d = ["ale","apple","monkey","plea"]

 Output:
 "apple"
 Example 2:
 Input:
 s = "abpcplea", d = ["a","b","c"]

 Output:
 "a"
 Note:
 All the strings in the input will only contain lower-case letters.
 The size of the dictionary won't exceed 1,000.
 The length of all the strings in the input won't exceed 1,000.
 */
public class M_Leetcode_524 {
    /**
     * @param s
     * @param d
     * @return
     *
     * basically find longest subsequence of s which is in d.
     * sort d & check if each string in d is subsequence of s
     */
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, (String a, String b) -> {
            if(a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });

        for(String a : d){
            if(isSubSequence(a, s)) return a;
        }

        return "";
    }

    private boolean isSubSequence(String a, String s){
        char[] arr1 = a.toCharArray();
        char[] arr2 = s.toCharArray();
        if(arr1.length > arr2.length) return false;
        int i = 0;
        int j = 0;
        while(j < arr2.length && i < arr1.length){
            if(arr1[i] == arr2[j]){
                i++;
                j++;
            }else{
                j++;
            }
        }

        return (i == arr1.length);
    }
}
