package LeetCode;

import java.util.PriorityQueue;

/**
 616. Add Bold Tag in String

 Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 Example 1:
 Input:
 s = "abcxyz123"
 dict = ["abc","123"]
 Output:
 "<b>abc</b>xyz<b>123</b>"
 Example 2:
 Input:
 s = "aaabbcc"
 dict = ["aaa","aab","bc"]
 Output:
 "<b>aaabbc</b>c"
 Note:
 The given dict won't contain duplicates, and its length won't exceed 100.
 All the strings in input have length in range [1, 1000].
 */
public class M_Leetcode_616 {
    /**
     * @param s
     * @param dict
     * @return
     *
     * merge interval
     */
    public String addBoldTag(String s, String[] dict) {
        PriorityQueue<int[]> q = new PriorityQueue<>((int[] a, int[] b)->{
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        for(String word : dict){
            int len = word.length();
            for(int i = 0; i + len <= s.length(); i++){
                if(s.substring(i, i + len).equals(word)) q.offer(new int[]{i, i + len - 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        int start = -1;
        int end = -1;
        while(!q.isEmpty()){
            int[] next = q.poll();
            if(next[0] <= end){
                end = Math.max(end, next[1]);
            }else{
                if(start >= 0){
                    sb.append("<b>").append(s.substring(start, end + 1)).append("</b>");
                }
                sb.append(s.substring(end + 1, next[0]));
                start = next[0];
                end = next[1];
            }
        }

        if(start >= 0){
            sb.append("<b>").append(s.substring(start, end + 1)).append("</b>");
        }

        if(end + 1 < s.length()){
            sb.append(s.substring(end + 1));
        }
        String res = sb.toString();
        return res.replaceAll("</b><b>", "");
    }
}
