package LeetCode;

import java.util.List;

/**
 648. Replace Words

 In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.

 Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

 You need to output the sentence after the replacement.

 Example 1:
 Input: dict = ["cat", "bat", "rat"]
 sentence = "the cattle was rattled by the battery"
 Output: "the cat was rat by the bat"
 Note:
 The input will only have lower-case letters.
 1 <= dict words number <= 1000
 1 <= sentence words number <= 1000
 1 <= root length <= 100
 1 <= sentence words length <= 1000
 */
public class L_Leetcode_648 {
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    TrieNode root = new TrieNode();

    /**
     * @param dict
     * @param sentence
     * @return
     *
     * use trie to parse the dict, then search in trie
     */
    public String replaceWords(List<String> dict, String sentence) {
        for(String word : dict) add(word);
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.trim().split("\\s+");
        for(String next : words){
            TrieNode cur = root;
            int i = 0;
            for(; i < next.length(); i++){
                char c = next.charAt(i);
                if(cur.children[c - 'a'] == null){
                    sb.append(next).append(" ");
                    break;
                }else{
                    cur = cur.children[c - 'a'];
                    if(cur.isWord || i == next.length() - 1){
                        sb.append(next.substring(0, i + 1)).append(" ");
                        break;
                    }
                }
            }
        }

        if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void add(String word){
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
            cur = cur.children[c - 'a'];
        }

        cur.isWord = true;
    }
}
