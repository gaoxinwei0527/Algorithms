package LeetCode;

import java.util.*;

/**
 269. Alien Dictionary

 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

 Example 1:
 Given the following words in dictionary,

 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]
 The correct order is: "wertf".

 Example 2:
 Given the following words in dictionary,

 [
 "z",
 "x"
 ]
 The correct order is: "zx".

 Example 3:
 Given the following words in dictionary,

 [
 "z",
 "x",
 "z"
 ]
 The order is invalid, so return "".

 Note:
 You may assume all letters are in lowercase.
 You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 */
public class M_Leetcode_269 {
    /**
     * @param words
     * @return
     *
     * every pair of words can give an edge in graph.
     * do topological sort on the graph.
     */
    public String alienOrder(String[] words) {
        boolean[] visited = new boolean[26];
        int[] inDegree = new int[26];
        Map<Character, Set<Character>> out = new HashMap<>();

        for(int i = 0; i < words.length; i++){
            for(int k = 0; k < words[i].length(); k++)
                visited[words[i].charAt(k) - 'a'] = true;
            if(i == 0) continue;

            String w1 = words[i - 1];
            String w2 = words[i];
            int x = 0;
            int y = 0;
            while(x < w1.length() && y < w2.length()){
                char a = w1.charAt(x);
                char b = w2.charAt(y);
                if(a != b){
                    if(!out.containsKey(a)){
                        out.put(a, new HashSet<>());
                    }

                    if(!out.get(a).contains(b)){
                        inDegree[b - 'a']++;
                        out.get(a).add(b);
                    }
                    break;
                }
                x++;
                y++;
            }
        }

        Queue<Character> q = new LinkedList<>();
        for(int i = 0; i < 26; i++){
            if(visited[i] && inDegree[i] == 0) q.offer((char)(i + 'a'));
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            char next = q.poll();
            sb.append(next);
            if(out.containsKey(next)){
                for(char c : out.get(next)){
                    inDegree[c - 'a']--;
                    if(inDegree[c - 'a'] == 0) q.offer(c);
                }
            }
        }

        for(int i = 0; i < 26; i++){
            if(inDegree[i] > 0) return "";
        }

        return sb.toString();
    }
}
