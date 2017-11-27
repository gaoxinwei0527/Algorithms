package LeetCode;

/**
 28. Implement strStr()

 Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1
 */
public class H_Solution_28 {
    /**
     * @param haystack
     * @param needle
     * @return
     *
     * simple iterative and compare way
     *
     * Time - O(n * m)
     * Space - O(n + m), this space usage can be saved by operate on String instead of char[]
     */
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        for(int i = 0; i <= h.length - n.length; i++){
            if(h[i] == n[0]){
                boolean found = true;
                for(int j = 0; j < n.length; j++){
                    if(h[i + j] != n[j]){
                        found = false;
                        break;
                    }
                }

                if(found) return i;
            }
        }

        return -1;
    }

    /**
     * @param haystack
     * @param needle
     * @return
     *
     * KMP
     * for each i in haystack, we try to find the max j in needle, where haystack[i-1-j, i-1] == needle[0, j] && haystack[i] == needle[j+1],
     * then we can conclude that, regard haystack[i] as the last character in substring, at most j+2 characters (0 to j+1) can be matched, means
     * haystack[i-j-1, i] == needle[0, j+1];
     *
     * To find max j where haystack[i-1-j, i-1] == needle[0, j], we need to preprocess needle.
     * for each index a in needle, we need to find max index b in needle, where a > b && needle[0 - b] == needle[a-1-b, a-1] && needle[b+1] == needle[a];
     * then we can set match[a] = b;
     *
     * This finding process is recursive, say we are processing a, then we look at match[a - 1], if needle[match[a - 1] + 1] != needle[a],
     * then we need to shrink the matching size. Because needle[0, match[a - 1]] can match needle[a-1-match[a-1], a-1], then we shrink it to
     * needle[0, match[match[a-1]]], which will also match needle[a-1-match[match[a-1]], a-1], but the window will be smaller, then we can check
     * if needle[match[match[a-1]] + 1] == needle[a], etc..
     *
     * If needle[a] cannot match anything in needle, match[a] = -1.
     * Since we are looking for b < a, so if a == 0, match[a] = -1, we can start preprocessing iteration at a = 1;
     *
     * Reference - http://www.matrix67.com/blog/archives/115
     *
     * Time - O(n)
     * Space - O(n + m), can be saved
     *
     */
    public int strStr2(String haystack, String needle) {
        if(needle.length() == 0) return 0;
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int[] match = preprocess(n);

        int j = -1; // maintain the index for max matching (means max j where n[0 - j] == h[i-1-j, i-1])
        for(int i = 0; i < h.length; i++){
            while(j >= 0 && h[i] != n[j + 1]) j = match[j];
            if(h[i] == n[j + 1]) j++;
            if(j == n.length - 1) return (i - j);
        }

        return -1;
    }

    private int[] preprocess(char[] n){
        int[] match = new int[n.length];
        match[0] = -1;
        int b = -1; // maintain the index for max self matchnig (means max j where n[0 - j] == n[(i-1-j) - (i-1)])
        for(int a = 1; a < n.length; a++){
            while(b >= 0 && n[a] != n[b + 1]) b = match[b];
            if(n[a] == n[b + 1]) b++;
            match[a] = b;
        }

        return match;
    }
}
