package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 68. Text Justification

 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.
 */
public class L_Leetcode_68 {
    /**
     * @param words
     * @param maxWidth
     * @return
     *
     * simple brute force
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0;
        List<String> res = new ArrayList<>();
        while(i < words.length){
            StringBuilder next = new StringBuilder();
            int len = words[i].length();
            int space = 0;
            int j = i + 1;
            while(j < words.length && (len + 1 + words[j].length() <= maxWidth)){
                len += (1 + words[j].length());
                space++;
                j++;
            }

            if(j == words.length || space == 0){
                next.append(words[i]);
                for(int k = i + 1; k < j; k++){
                    next.append(' ').append(words[k]);
                }

                while(next.length() < maxWidth){
                    next.append(' ');
                }
            }else{
                int[] spaces = new int[space];
                int remain = maxWidth - len;
                for(int k = 0; k < space; k++){
                    spaces[k] = 1 + remain / space;
                }

                for(int k = 0; k < remain % space; k++){
                    spaces[k]++;
                }

                next.append(words[i]);
                int s = 0;
                for(int k = i + 1; k < j; k++){
                    for(int l = 0; l < spaces[s]; l++){
                        next.append(' ');
                    }
                    next.append(words[k]);
                    s++;
                }
            }
            res.add(next.toString());
            i = j;
        }

        return res;
    }

    /**
     * @param words
     * @param maxWidth
     * @return
     *
     * relatively module way
     */
    public List<String> fullJustify2(String[] words, int maxWidth) {
        int i = 0;
        int len = words.length;
        List<String> res = new ArrayList<>();
        while(i < len){
            int j = i + 1;
            int tmp = words[i].length();
            while(j < len && (tmp + words[j].length() + 1 <= maxWidth)){
                tmp += (words[j].length() + 1);
                j++;
            }

            if(j == len) res.add(construct(words, i, j - 1, maxWidth, true));
            else res.add(construct(words, i, j - 1, maxWidth, false));
            i = j;
        }

        return res;
    }

    private String construct(String[] words, int i, int j, int L, boolean last){
        StringBuilder sb = new StringBuilder();
        sb.append(words[i]);
        if(last || i == j){
            for(int k = i + 1; k <= j; k++) sb.append(" ").append(words[k]);
            while(sb.length() < L) sb.append(" ");
        }else{
            int wordLen = 0;
            for(int k = i; k <= j; k++) wordLen += words[k].length();

            int extra = L - wordLen - (j - i);
            int even = extra / (j - i);
            int left = extra % (j - i);
            for(int k = i + 1; k <= j; k++){
                sb.append(" ");
                for(int m = 0; m < even; m++) sb.append(" ");
                if(left > 0){
                    sb.append(" ");
                    left--;
                }
                sb.append(words[k]);
            }
        }

        return sb.toString();
    }
}
