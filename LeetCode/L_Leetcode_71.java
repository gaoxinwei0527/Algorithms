package LeetCode;

import java.util.Stack;

/**
 71. Simplify Path

 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 */
public class L_Leetcode_71 {
    /**
     * @param path
     * @return
     *
     * use stack
     *
     * Time - O(n) where n is num of segments in path
     * Space - O(n)
     */
    public String simplifyPath(String path) {
        String[] dir = path.trim().split("/");
        Stack<String> st = new Stack<>();
        for(String d : dir){
            if(d.length() == 0 || d.equals(".")) continue;
            if(d.equals("..")){
                if(!st.isEmpty()) st.pop();
            }else{
                st.push(d);
            }
        }

        if(st.isEmpty()) return "/";
        String res = "";
        while(!st.isEmpty()) res = "/" + st.pop() + res;
        return res;
    }
}
