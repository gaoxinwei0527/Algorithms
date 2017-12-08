package LeetCode;

import java.util.Arrays;

/**
 87. Scramble String

 Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

 Below is one possible representation of s1 = "great":

 great
 /    \
 gr    eat
 / \    /  \
 g   r  e   at
 / \
 a   t
 To scramble the string, we may choose any non-leaf node and swap its two children.

 For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

 rgeat
 /    \
 rg    eat
 / \    /  \
 r   g  e   at
 / \
 a   t
 We say that "rgeat" is a scrambled string of "great".

 Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

 rgtae
 /    \
 rg    tae
 / \    /  \
 r   g  ta  e
 / \
 t   a
 We say that "rgtae" is a scrambled string of "great".

 Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class M_Leetcode_87 {
    /**
     * @param s1
     * @param s2
     * @return
     *
     * dp[k][i][j] means starting from s1[i] and s2[j], and length is k, if these two substrings are scrambled to each other.
     *
     * Time - O(n ^ 3)
     * Space - O(n ^ 3)
     */
    public boolean isScramble1(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        int n = s1.length();
        if(n == 0) return true;

        boolean[][][] dp = new boolean[n + 1][n][n];
        for(int k = 1; k <= n; k++){
            for(int i = 0; i <= n - k; i++){
                for(int j = 0; j <= n - k; j++){
                    if(k == 1) dp[k][i][j] = (s1.charAt(i) == s2.charAt(j));
                    else{
                        for(int x = 1; x < k; x++){
                            if(dp[x][i][j] && dp[k - x][i + x][j + x] || dp[x][i][j + k - x] && dp[k - x][i + x][j]){
                                dp[k][i][j] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return dp[n][0][0];
    }

    /**
     * @param s1
     * @param s2
     * @return
     *
     * Recursive way
     */
    public boolean isScramble(String s1, String s2) {
        if(s1.length() == 0){
            return s2.length() == 0;
        }

        if(s2.length() == 0){
            return s1.length() == 0;
        }

        char[] s1_arr = s1.toCharArray();
        char[] s2_arr = s2.toCharArray();
        Arrays.sort(s1_arr);
        Arrays.sort(s2_arr);
        String sorted1 = new String(s1_arr);
        String sorted2 = new String(s2_arr);
        if(!sorted1.equals(sorted2)){
            return false;
        }else if(sorted1.length() <= 2){
            return true;
        }

        for(int i = 1; i < s1.length(); i++){
            boolean c1 = isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i));
            boolean c2 = isScramble(s1.substring(0, i), s2.substring(s2.length()-i)) && isScramble(s1.substring(i), s2.substring(0, s2.length()-i));
            if(c1 || c2){
                return true;
            }
        }

        return false;
    }
}
