package LeetCode;

import java.util.*;

/**
 140. Word Break II

 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].

 UPDATE (2017/1/4):
 The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */
public class H_Leetcode_140 {
    /**
     * @param s
     * @param wordDict
     * @return
     *
     * Tried with dp and got TLE
     * List<String>[] dp store all sentence that can be generated from first i chars in s;
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> dict = new HashSet<>(wordDict);
        List<String>[] dp = new List[n + 1];
        boolean[] valid = new boolean[n + 1];
        dp[0] = new ArrayList<>();
        dp[0].add("");
        valid[0] = true;
        for(int i = 1; i <= n; i++){
            dp[i] = new ArrayList<>();
            for(String word : dict){
                int len = word.length();
                if(i >= len && valid[i - len] && word.equals(s.substring(i - len, i))){
                    valid[i] = true;
                    for(String cur : dp[i - len]){
                        if(cur.length() == 0){
                            dp[i].add(s.substring(i - len, i));
                        }else{
                            dp[i].add(cur + " " + s.substring(i - len, i));
                        }
                    }
                }
            }
        }

        if(!valid[n]) return new ArrayList<>();
        return dp[n];
    }

    Map<String, List<String>> map = new HashMap<>();

    /**
     * @param s
     * @param wordDict
     * @return
     *
     * dfs + memo passed leetcode
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        if(s.length() == 0) return new ArrayList<>();
        Set<String> dict = new HashSet<>(wordDict);
        return helper(s, dict);
    }

    private List<String> helper(String s, Set<String> dict){
        if(s.length() == 0){
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }

        if(map.containsKey(s)) return new ArrayList<>(map.get(s));
        List<String> res = new ArrayList<>();
        for(String word : dict){
            if(s.startsWith(word)){
                List<String> next = helper(s.substring(word.length()), dict);
                for(String n : next){
                    res.add(word + (n.length() == 0 ? "" : " " + n));
                }
            }
        }
        map.put(s, new ArrayList<>(res));
        return res;
    }
}
