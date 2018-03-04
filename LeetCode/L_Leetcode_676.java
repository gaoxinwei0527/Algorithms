package LeetCode;

/**
 676. Implement Magic Dictionary

 Implement a magic directory with buildDict, and search methods.

 For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

 For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

 Example 1:
 Input: buildDict(["hello", "leetcode"]), Output: Null
 Input: search("hello"), Output: False
 Input: search("hhllo"), Output: True
 Input: search("hell"), Output: False
 Input: search("leetcoded"), Output: False
 Note:
 You may assume that all the inputs are consist of lowercase letters a-z.
 For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
 Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class L_Leetcode_676 {
    class MagicDictionary {
        class TrieNode{
            TrieNode[] children = new TrieNode[26];
            boolean isWord = false;
        }

        TrieNode root = new TrieNode();

        /** Initialize your data structure here. */
        public MagicDictionary() {
            //no-op
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for(String word : dict) insert(word);
        }

        private void insert(String word){
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            TrieNode cur = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                int idx = c - 'a';
                for(int j = 0; j < 26; j++){
                    if(j != idx && cur.children[j] != null && search(cur.children[j], word.substring(i + 1))) return true;
                }
                if(cur.children[idx] == null) return false;
                cur = cur.children[idx];
            }
            return false;
        }

        private boolean search(TrieNode cur, String word){
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(cur.children[c - 'a'] == null) return false;
                cur = cur.children[c - 'a'];
            }
            return cur.isWord;
        }
    }
}
