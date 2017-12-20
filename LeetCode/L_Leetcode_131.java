package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 131. Palindrome Partitioning

 Given a string s, partition s such that every substring of the partition is a palindrome.

 Return all possible palindrome partitioning of s.

 For example, given s = "aab",
 Return

 [
 ["aa","b"],
 ["a","a","b"]
 ]
 */
public class L_Leetcode_131 {
    /**
     * @param s
     * @return
     *
     * dfs + backtrack
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        helper(res, tmp, s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> tmp, String s, int i){
        if(i == s.length()){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int k = i + 1; k <= s.length(); k++){
            String sub = s.substring(i, k);
            if(isPalindrome(sub)){
                tmp.add(sub);
                helper(res, tmp, s, k);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s){
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
            if(s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
