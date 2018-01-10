package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 249. Group Shifted Strings

 Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

 "abc" -> "bcd" -> ... -> "xyz"
 Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

 For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 A solution is:

 [
 ["abc","bcd","xyz"],
 ["az","ba"],
 ["acef"],
 ["a","z"]
 ]
 */
public class L_Leetcode_249 {
    /**
     * @param strings
     * @return
     *
     * group strings to corresponding key start with 'a'
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            int offset = (int)(s.charAt(0) - 'a');
            String key = shift(s, offset);
            if(!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private String shift(String s, int shift){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            char n = ((int)(c - 'a') < shift ? (char)(c - shift + 26) : (char)(c - shift));
            sb.append(n);
        }
        return sb.toString();
    }
}
