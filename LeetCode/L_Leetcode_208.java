package LeetCode;

/**
 208. Implement Trie (Prefix Tree)

 Implement a trie with insert, search, and startsWith methods.

 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 */
public class L_Leetcode_208 {
    class Trie {
        class TrieNode{
            public TrieNode[] children;
            public boolean isWord;
            public TrieNode(){
                children = new TrieNode[26];
                isWord = false;
            }
        }

        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.children[(int)(c - 'a')] == null){
                    cur.children[(int)(c - 'a')] = new TrieNode();
                }
                cur = cur.children[(int)(c - 'a')];
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.children[(int)(c - 'a')] == null) return false;
                cur = cur.children[(int)(c - 'a')];
            }
            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for(int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(cur.children[(int)(c - 'a')] == null) return false;
                cur = cur.children[(int)(c - 'a')];
            }
            return true;
        }
    }
}
