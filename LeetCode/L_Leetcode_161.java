package LeetCode;

/**
 161. One Edit Distance

 Given two strings S and T, determine if they are both one edit distance apart.
 */
public class L_Leetcode_161 {
    /**
     * @param s
     * @param t
     * @return
     *
     * Time - O(n)
     * Space - O(n), can be optimized
     */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(Math.abs(m - n) >= 2 || s.equals(t)) return false;
        boolean edit = false;
        char[] arr_s = s.toCharArray();
        char[] arr_t = t.toCharArray();
        int i = 0;
        int j = 0;
        while(i < m && j < n){
            if(arr_s[i] == arr_t[j]){
                i++;
                j++;
            }else if(edit){
                return false; // already edited
            }else if(m > n){
                i++;
                edit = true;
            }else if(m < n){
                j++;
                edit = true;
            }else{
                i++;
                j++;
                edit = true;
            }
        }

        return ((!edit) || (i == m && j == n));
    }
}
