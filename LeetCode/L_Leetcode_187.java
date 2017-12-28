package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 187. Repeated DNA Sequences

 All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 For example,

 Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

 Return:
 ["AAAAACCCCC", "CCCCCAAAAA"].
 */
public class L_Leetcode_187 {
    /**
     * @param s
     * @return
     *
     * use hash set to record visited substrings
     *
     * Time - O(n), where n is string length
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int i = 0;
        int j = 10;
        Set<String> res = new HashSet<>();
        Set<String> visited = new HashSet<>();
        while(j <= s.length()){
            String next = s.substring(i, j);
            if(visited.contains(next)) res.add(next);
            else visited.add(next);
            i++;
            j++;
        }
        return new ArrayList<>(res);
    }
}
