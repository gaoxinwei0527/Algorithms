package LeetCode;

/**
 14. Longest Common Prefix

 Write a function to find the longest common prefix string amongst an array of strings.
 */
public class L_Solution_14 {
    /**
     * @param strs
     * @return
     *
     * simple brute force way
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        String res = strs[0];
        for(int i = 1; i < strs.length; i++){
            while(!strs[i].startsWith(res)){
                res = res.substring(0, res.length() - 1);
            }
        }

        return res;
    }
}
