package LeetCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 139. Word Break

 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

 For example, given
 s = "leetcode",
 dict = ["leet", "code"].

 Return true because "leetcode" can be segmented as "leet code".

 UPDATE (2017/1/4):
 The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
 */
public class H_leetcode_139 {
    /**
     * @param s
     * @param wordDict
     * @return
     *
     * dp[i] means whether we can break first i chars in s.
     * for each j in 0 ~ i - 1, if dp[j] && dict.contains(s[j, i)), then dp[i] = true;
     *
     * Time - O(n ^ 2)
     * Space - O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        Set<String> dict = new HashSet<>(wordDict);
        dp[0] = true;
        for(int i = 1; i <= n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
