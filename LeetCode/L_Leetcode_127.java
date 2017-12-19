package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 127. Word Ladder

 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 For example,

 Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log","cog"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 UPDATE (2017/1/20):
 The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */
public class L_Leetcode_127 {
    /**
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     *
     * bfs from beginWord to endWord
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<String> level = new ArrayList<>();
        char[] alpha = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        level.add(beginWord);
        int res = 1;
        while(!level.isEmpty()){
            List<String> next_level = new ArrayList<>();
            for(String next : level){
                for(int i = 0; i < next.length(); i++){
                    for(int j = 0; j < alpha.length; j++){
                        if(next.charAt(i) == alpha[j]) continue;
                        StringBuilder sb = new StringBuilder();
                        String muted = sb.append(next.substring(0, i)).append(alpha[j]).append(next.substring(i + 1)).toString();
                        if(dict.contains(muted)) {
                            if(muted.equals(endWord)) return res + 1;
                            next_level.add(muted);
                            dict.remove(muted);
                        }
                    }
                }
            }
            res++;
            level = next_level;
        }
        return 0;
    }
}
