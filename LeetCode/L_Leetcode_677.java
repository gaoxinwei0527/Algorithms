package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 677. Map Sum Pairs

 Implement a MapSum class with insert, and sum methods.

 For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

 For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

 Example 1:
 Input: insert("apple", 3), Output: Null
 Input: sum("ap"), Output: 3
 Input: insert("app", 2), Output: Null
 Input: sum("ap"), Output: 5
 */
public class L_Leetcode_677 {
    class MapSum {
        class TrieNode{
            TrieNode[] children = new TrieNode[26];
            boolean isWord = false;
            Set<String> words = new HashSet<>();
        }

        TrieNode root = new TrieNode();
        Map<String, Integer> map = new HashMap<>();

        /** Initialize your data structure here. */
        public MapSum() {
            //no-op
        }

        public void insert(String key, int val) {
            if(!map.containsKey(key)){
                TrieNode cur = root;
                cur.words.add(key);
                for(int i = 0; i < key.length(); i++){
                    char c = key.charAt(i);
                    if(cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
                    cur = cur.children[c - 'a'];
                    cur.words.add(key);
                }
                cur.isWord = true;
            }
            map.put(key, val);
        }

        public int sum(String prefix) {
            TrieNode cur = root;
            for(int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(cur.children[c - 'a'] == null) return 0;
                cur = cur.children[c - 'a'];
            }

            int sum = 0;
            for(String key : cur.words){
                sum += map.get(key);
            }

            return sum;
        }
    }
}
