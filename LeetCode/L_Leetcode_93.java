package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 93. Restore IP Addresses

 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 For example:
 Given "25525511135",

 return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class L_Leetcode_93 {
    /**
     * @param s
     * @return
     *
     * dfs + backtrack
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        String[] tmp = new String[4];
        helper(res, tmp, 0, s, 0);
        return res;
    }

    private void helper(List<String> res, String[] tmp, int i, String s, int j){
        if(i == tmp.length && j == s.length()){
            StringBuilder sb = new StringBuilder();
            sb.append(tmp[0]);
            for(int k = 1; k < tmp.length; k++){
                sb.append(".").append(tmp[k]);
            }
            res.add(sb.toString());
            return;
        }

        if(i == tmp.length || j == s.length()) return;

        if(s.charAt(j) == '0'){
            tmp[i] = "0";
            helper(res, tmp, i + 1, s, j + 1);
            tmp[i] = "";
        }else{
            for(int k = j; k < j + 3 && k < s.length(); k++){
                int next = Integer.parseInt(s.substring(j, k + 1));
                if(next <= 255){
                    tmp[i] = s.substring(j, k + 1);
                    helper(res, tmp, i + 1, s, k + 1);
                    tmp[i] = "";
                }
            }
        }
    }
}
