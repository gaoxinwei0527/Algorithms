package LeetCode;

/**
 97. Interleaving String

 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 For example,
 Given:
 s1 = "aabcc",
 s2 = "dbbca",

 When s3 = "aadbbcbcac", return true.
 When s3 = "aadbbbaccc", return false.
 */
public class H_Leetcode_97 {
    /**
     * @param s1
     * @param s2
     * @param s3
     * @return
     *
     * dp[i][j] means first i + j chars in s3 is or not interleaving of first i chars in s1 and first j chars in s2
     *
     * Time - O(m * n)
     * Space - O(m * n)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int a = s1.length();
        int b = s2.length();
        int c = s3.length();
        if(a + b != c) return false;
        boolean[][] dp = new boolean[a + 1][b + 1];
        dp[0][0] = true;
        for(int i = 0; i <= a; i++){
            if(s1.substring(0, i).equals(s3.substring(0, i))){
                dp[i][0] = true;
            }else{
                break;
            }
        }

        for(int i = 0; i <= b; i++){
            if(s2.substring(0, i).equals(s3.substring(0, i))){
                dp[0][i] = true;
            }else{
                break;
            }
        }

        for(int i = 1; i <= a; i++){
            for(int j = 1; j <= b; j++){
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }

        return dp[a][b];
    }
}
