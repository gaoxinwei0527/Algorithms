package LeetCode;

/**
 186. Reverse Words in a String II

 Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

 The input string does not contain leading or trailing spaces and the words are always separated by a single space.

 For example,
 Given s = "the sky is blue",
 return "blue is sky the".

 Could you do it in-place without allocating extra space?

 Related problem: Rotate Array

 Update (2017-10-16):
 We have updated the function signature to accept a character array, so please reset to the default code definition by clicking on the reload button above the code editor. Also, Run Code is now available!
 */
public class L_Leetcode_186 {
    /**
     * @param str
     *
     * reverse the whole string, then reverse each word
     *
     * Time - O(n)
     * Space - O(1)
     */
    public void reverseWords(char[] str) {
        int i = 0;
        int j = str.length - 1;
        while(i < j){
            char tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
            i++;
            j--;
        }

        i = 0;
        j = 0;
        while(i < str.length){
            while(j < str.length && str[j] != ' '){
                j++;
            }

            int k = j - 1;
            while(i < k){
                char tmp = str[i];
                str[i] = str[k];
                str[k] = tmp;
                i++;
                k--;
            }

            i = j + 1;
            j = i;
        }
    }
}
