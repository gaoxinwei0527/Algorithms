package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 691. Stickers to Spell Word

 We are given N different types of stickers. Each sticker has a lowercase English word on it.

 You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

 You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

 What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

 Example 1:

 Input:

 ["with", "example", "science"], "thehat"
 Output:

 3
 Explanation:

 We can use 2 "with" stickers, and 1 "example" sticker.
 After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 Also, this is the minimum number of stickers necessary to form the target string.
 Example 2:

 Input:

 ["notice", "possible"], "basicbasic"
 Output:

 -1
 Explanation:

 We can't form the target "basicbasic" from cutting letters from the given stickers.
 Note:

 stickers has length in the range [1, 50].
 stickers consists of lowercase English words (without apostrophes).
 target has length in the range [1, 15], and consists of lowercase English letters.
 In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
 The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.
 */
public class S_Leetcode_691 {
    /**
     * @param stickers
     * @param target
     * @return
     *
     * backtrack + memo as there are a lot repeat sub-problems.
     */
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] count = new int[n][26];
        boolean[] occur = new boolean[26];
        for(int i = 0; i < n; i++){
            char[] arr = stickers[i].toCharArray();
            for(char c : arr){
                count[i][c - 'a']++;
                occur[c - 'a'] = true;
            }
        }

        int[] t = new int[26];
        char[] arr = target.toCharArray();
        for(char c : arr){
            t[c - 'a']++;
            if(!occur[c - 'a']) return -1;
        }

        Map<String, Integer> cache = new HashMap<>();
        return helper(cache, count, t);
    }

    private int helper(Map<String, Integer> cache, int[][] count, int[] target){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            if(target[i] > 0){
                sb.append((char)('a' + i)).append(target[i]);
            }
        }

        if(sb.length() == 0){
            return 0;
        }

        String key = sb.toString();
        if(cache.containsKey(key)) return cache.get(key);

        int res = Integer.MAX_VALUE;
        for(int[] c : count){
            boolean valid = false;
            for(int i = 0; i < 26; i++){
                if(c[i] > 0 && target[i] > 0){
                    valid = true;
                    break;
                }
            }

            if(valid){
                for(int i = 0; i < 26; i++){
                    target[i] -= c[i];
                }
                res = Math.min(res, 1 + helper(cache, count, target));
                for(int i = 0; i < 26; i++){
                    target[i] += c[i];
                }
            }
        }

        cache.put(key, res);
        return res;
    }
}
