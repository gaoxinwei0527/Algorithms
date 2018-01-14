package LeetCode;

/**
 514. Freedom Trail

 In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

 Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

 Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.
 At the stage of rotating the ring to spell the key character key[i]:
 You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
 If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
 Example:


 Input: ring = "godding", key = "gd"
 Output: 4
 Explanation:
 For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 Also, we need 1 more step for spelling.
 So the final output is 4.
 Note:
 Length of both ring and key will be in range 1 to 100.
 There are only lowercase letters in both strings and might be some duplcate characters in both strings.
 It's guaranteed that string key could always be spelled by rotating the string ring.
 */
public class M_Leetcode_514 {
    /**
     * @param ring
     * @param key
     * @return
     *
     * subproblem S(i, j) means the min num of ops to solve key[0, i] and stop at ring[j].
     *
     * recurrence -
     * foreach x where ring[x] == key[i - 1]
     *     S(i, j) = min (S(i - 1, x) + dis(x, j))
     *
     * original problem - foreach k where ring[k] = key[key.len - 1]
     *                         min (S(key.len - 1, k))
     *
     * bottom-up: dp[i][j] stores the result of S(i, j)
     */
    public int findRotateSteps(String ring, String key) {
        int m = ring.length();
        int n = key.length();
        if(n == 0) return 0;
        int[][] dp = new int[n][m];

        for(int i = 0; i < m; i++){
            if(ring.charAt(i) == key.charAt(0)){
                dp[0][i] = Math.min(i, m - i);
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++){
                if(ring.charAt(j) == key.charAt(i)){
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = 0; k < m; k++){
                        if(ring.charAt(k) == key.charAt(i - 1)){
                            int dis = Math.abs(k - j);
                            if(k > j) dis = Math.min(dis, m - k + j);
                            else if(k < j) dis = Math.min(dis, m - j + k);
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + dis);
                        }
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++){
            if(ring.charAt(i) == key.charAt(n - 1)) res = Math.min(res, dp[n - 1][i]);
        }

        return (res + key.length());
    }
}
