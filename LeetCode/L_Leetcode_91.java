package LeetCode;

/**
 91. Decode Ways

 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.

 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

 The number of ways decoding "12" is 2.
 */
public class L_Leetcode_91 {
    /**
     * @param s
     * @return
     *
     * simple dp
     * Time - O(n)
     * Space - O(n), can be optimized
     */
    public int numDecodings(String s) {
        int n = s.length();
        if(n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        char[] arr = s.toCharArray();
        for(int i = 1; i <= n; i++){
            if(arr[i - 1] == '0') {
                if(i == 1 || arr[i - 2] != '1' && arr[i - 2] != '2') return 0;
                dp[i] = dp[i - 2];
            }else if(arr[i - 1] >= '1' && arr[i - 1] <= '9'){
                dp[i] += dp[i - 1];
                if(i > 1){
                    if(arr[i - 2] == '1' || arr[i - 2] == '2' && arr[i - 1] <= '6'){
                        dp[i] += dp[i - 2];
                    }
                }
            }else{
                return 0;
            }
        }

        return dp[n];
    }
}
