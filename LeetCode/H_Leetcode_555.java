package LeetCode;

/**
 555. Split Concatenated Strings

 Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will make the looped string into a regular one.

 Specifically, to find the lexicographically biggest string, you need to experience two phases:

 Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
 Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.
 And your job is to find the lexicographically biggest one among all the possible regular strings.

 Example:
 Input: "abc", "xyz"
 Output: "zyxcba"
 Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
 where '-' represents the looped status.
 The answer string came from the fourth looped one,
 where you could cut from the middle character 'a' and get "zyxcba".
 Note:
 The input strings will only contain lowercase letters.
 The total length of all the strings will not over 1,000.
 */
public class H_Leetcode_555 {
    /**
     * @param strs
     * @return
     *
     * for each string, if reversed[i] > strs[i], strs[i] = reversed[i]
     * only trick is, the cut string may not be follow this rule. e.g.
     * s[i] = 'aby', if not cut, then we definitely use 'yba' as it's lexical larger.
     * but if it's cut, 'y****ab' could be larger than 'yba******', so we need try both reversed & non-reversed for cut string.
     */
    public String splitLoopedString(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            String reversed = new StringBuilder(strs[i]).reverse().toString();
            if(strs[i].compareTo(reversed) < 0) strs[i] = reversed;
            sb.append(strs[i]);
        }

        String total = sb.toString();
        String res = total;
        for(int i = 0; i < strs.length; i++){
            String reversed = new StringBuilder(strs[i]).reverse().toString();
            String[] candidate = new String[]{reversed, strs[i]};

            StringBuilder sb2 = new StringBuilder();
            for(int k = i + 1; k < strs.length; k++) sb2.append(strs[k]);
            for(int k = 0; k < i; k++) sb2.append(strs[k]);

            String other = sb2.toString();
            for(String cut : candidate){
                for(int j = 0; j < cut.length(); j++){
                    String tmp = cut.substring(j) + other + cut.substring(0, j);
                    if(res.compareTo(tmp) < 0) res = tmp;
                }
            }
        }

        return res;
    }
}
