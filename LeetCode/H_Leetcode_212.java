package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 212. Word Search II

 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 For example,
 Given words = ["oath","pea","eat","rain"] and board =

 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].
 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 */
public class H_Leetcode_212 {
    class TrieNode{
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord = false;
    }

    TrieNode root = new TrieNode();
    int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void add(String word){
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            int next = (int)(word.charAt(i) - 'a');
            if(cur.children[next] == null) cur.children[next] = new TrieNode();
            cur = cur.children[next];
        }
        cur.isWord = true;
    }

    /**
     * @param board
     * @param words
     * @return
     *
     * parse dict into a trie, then start with each char in board, do dfs search in trie.
     */
    public List<String> findWords(char[][] board, String[] words) {
        if(board.length == 0 || board[0].length == 0) return new ArrayList<>();
        for(String word : words){
            add(word);
        }

        int m = board.length;
        int n = board[0].length;
        Set<String> res = new HashSet<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                TrieNode cur = root;
                Set<Integer> visited = new HashSet<>();
                search(res, cur, "", board, i, j, visited);
            }
        }

        return new ArrayList<>(res);
    }

    private void search(Set<String> res, TrieNode cur, String tmp, char[][] board, int i, int j, Set<Integer> visited){
        char c = board[i][j];
        int next = (int)(c - 'a');
        if(cur.children[next] == null) return;
        cur = cur.children[next];
        tmp = tmp + c;
        if(cur.isWord) res.add(tmp);

        visited.add(i * board[0].length + j);
        for(int[] d : direction){
            int a = i + d[0];
            int b = j + d[1];
            if(a >= 0 && a < board.length && b >= 0 && b < board[0].length && !visited.contains(a * board[0].length + b)){
                search(res, cur, tmp, board, a, b, visited);
            }
        }
        visited.remove(i * board[0].length + j);
    }
}
