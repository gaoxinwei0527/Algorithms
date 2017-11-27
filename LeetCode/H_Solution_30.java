package LeetCode;

import java.util.*;

/**
 30. Substring with Concatenation of All Words

 You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]

 You should return the indices: [0,9].
 */
public class H_Solution_30 {
    /**
     * @param s
     * @param words
     * @return
     *
     * use map to maintain word to frequency mapping;
     * since the length of words are same, so we can get the window size in s.
     * Then traverse each window and check if the substring in window can be constructed by all words.
     *
     * Time - O(n * n / m)
     * Space - O(m + n) worst
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if(words.length == 0 || words[0].length() == 0) return new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int len = words.length * words[0].length();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i <= s.length() - len; i++){
            String sub = s.substring(i, i + len);
            if(check(sub, map, words[0].length())){
                res.add(i);
            }
        }

        return res;
    }

    private boolean check(String s, Map<String, Integer> map, int len){
        Map<String, Integer> copy = new HashMap<>(map);
        int i = 0;
        while(i < s.length()){
            String next = s.substring(i, i + len);
            if(!copy.containsKey(next)) return false;
            copy.put(next, copy.get(next) - 1);
            if(copy.get(next) == 0) copy.remove(next);
            i += len;
        }
        return true;
    }

    /**
     * @param s
     * @param words
     * @return
     *
     * dfs way
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0){
            return res;
        }

        int wordLen = words[0].length();
        int len = wordLen * words.length;
        List<String> wordList = new ArrayList(Arrays.asList(words));

        for(int i = 0; i <= s.length() - len; i++){
            String next = s.substring(i, i+wordLen);
            if(wordList.contains(next)){
                wordList.remove(next);
                dfs(res, wordList, s, wordLen, i, i + wordLen);
                wordList.add(next);
            }
        }

        return res;
    }

    private void dfs(List<Integer> res, List<String> words, String s, int wordLen, int start, int cur){
        if(words.isEmpty()){
            res.add(start);
            return;
        }

        String next = s.substring(cur, cur + wordLen);
        if(words.contains(next)){
            words.remove(next);
            dfs(res, words, s, wordLen, start, cur + wordLen);
            words.add(next);
        }
    }
}
