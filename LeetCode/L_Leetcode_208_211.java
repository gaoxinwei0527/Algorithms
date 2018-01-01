package LeetCode;

public class L_Leetcode_208_211 {
    /**
     208. Implement Trie (Prefix Tree)

     Implement a trie with insert, search, and startsWith methods.

     Note:
     You may assume that all inputs are consist of lowercase letters a-z.
     */

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

    /**
     211. Add and Search Word - Data structure design

     Design a data structure that supports the following two operations:

     void addWord(word)
     bool search(word)
     search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

     For example:

     addWord("bad")
     addWord("dad")
     addWord("mad")
     search("pad") -> false
     search("bad") -> true
     search(".ad") -> true
     search("b..") -> true
     Note:
     You may assume that all words are consist of lowercase letters a-z.

     same as leetcode 208, just need handle '.' in trie, we should do dfs try when we get '.'.
     */
    class WordDictionary {
        class TrieNode{
            public TrieNode[] children = new TrieNode[26];
            public boolean isWord = false;
        }

        TrieNode root = new TrieNode();

        /** Initialize your data structure here. */
        public WordDictionary() {
            //no-op
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                int next = (int)(word.charAt(i) - 'a');
                if(cur.children[next] == null) cur.children[next] = new TrieNode();
                cur = cur.children[next];
            }
            cur.isWord = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            TrieNode cur = root;
            return search(cur, word, 0);
        }

        private boolean search(TrieNode cur, String word, int i){
            if(i == word.length()){
                return cur.isWord;
            }
            char c = word.charAt(i);
            if(c == '.'){
                for(int k = 0; k < 26; k++){
                    if(cur.children[k] != null && search(cur.children[k], word, i + 1)) return true;
                }
            }else{
                int next = (int)(c - 'a');
                if(cur.children[next] != null && search(cur.children[next], word, i + 1)) return true;
            }

            return false;
        }
    }
}
