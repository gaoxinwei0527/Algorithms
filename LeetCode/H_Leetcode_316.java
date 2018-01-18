package LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 316. Remove Duplicate Letters

 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"

 Credits:
 Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class H_Leetcode_316 {
    /*
    Greedy
    1. for s, get smallest char c that satisfies-
    right side of c include all other characters
    2. append c to tmp string builder, remove all c occurence, and remove left side of leftmost c
    3. recursively call to resolve the remaining string
    */
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0) return s;
        Set<Character> contains = new HashSet<>();
        int[] count = new int[s.length()];
        for(int i = s.length() - 1; i >= 0; i--){
            contains.add(s.charAt(i));
            count[i] = contains.size();
        }

        int i = 0;
        char c = s.charAt(0);
        int cur = 0;
        while(i < s.length() - 1 && count[i + 1] == count[i]){
            i++;
            if((int)(c - s.charAt(i)) > 0){
                c = s.charAt(i);
                cur = i;
            }
        }

        s = s.substring(cur + 1).replaceAll("" + c, "");
        return "" + c + removeDuplicateLetters(s);
    }

    /**
     * @param s
     * @return
     *
     * utilize stack to do iterative greedy choice.
     * stack maintains the result we would return (smallest lexi order sub sequence for current substring)
     * when we get a char x, if the top of stack y is larger than x & there is other same char y after x (based upon count), then we can pop y.
     * until we get stack top < x or stack is empty or stack top has no more occurrence later, then we push x.
     */
    public String removeDuplicateLetters2(String s) {
        int[] count = new int[26];
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
            count[arr[i] - 'a']++;
        }

        Stack<Character> st = new Stack<>();
        boolean[] visited = new boolean[26];
        for(int i = 0; i < arr.length; i++){
            if(visited[arr[i] - 'a']){
                count[arr[i] - 'a']--;
                continue;
            }

            while(!st.isEmpty() && st.peek() > arr[i] && count[st.peek() - 'a'] > 0){
                visited[st.pop() - 'a'] = false;
            }

            st.push(arr[i]);
            visited[arr[i] - 'a'] = true;
            count[arr[i] - 'a']--;
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.insert(0, st.pop());
        }

        return sb.toString();
    }
}
