package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 737. Sentence Similarity II

 Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

 For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

 Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

 Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

 Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

 Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

 Note:

 The length of words1 and words2 will not exceed 1000.
 The length of pairs will not exceed 2000.
 The length of each pairs[i] will be 2.
 The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class M_Leetcode_737 {
    /**
     * @param words1
     * @param words2
     * @param pairs
     * @return
     *
     * union find.
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if(words1.length != words2.length) return false;
        Map<String, String> root = new HashMap<>();
        for(String[] pair : pairs){
            if(!root.containsKey(pair[0])) root.put(pair[0], pair[0]);
            if(!root.containsKey(pair[1])) root.put(pair[1], pair[1]);
            String r1 = find(root, pair[0]);
            String r2 = find(root, pair[1]);
            if(!r1.equals(r2)) root.put(r1, r2);
        }

        int n = words1.length;
        for(int i = 0; i < n; i++){
            if(words1[i].equals(words2[i])) continue;
            if(!root.containsKey(words1[i]) || !root.containsKey(words2[i])) return false;
            String r1 = find(root, words1[i]);
            String r2 = find(root, words2[i]);
            if(!r1.equals(r2)) return false;
        }

        return true;
    }

    private String find(Map<String, String> root, String a){
        while(!root.get(a).equals(a)) a = root.get(a);
        return a;
    }
}
