package LeetCode;

/**
 686. Repeated String Match

 Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

 For example, with A = "abcd" and B = "cdabcdab".

 Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

 Note:
 The length of A and B will be between 1 and 10000.
 */
public class L_Leetcode_686 {
    /**
     * @param A
     * @param B
     * @return
     *
     * firstly repeat the String A until reach the length of B. if pattern.contains(B), return
     * append another A to pattern, if pattern.contains(B), return
     * else return -1, because even keep appending A to pattern, all substring with B.length() will already be tried in previous patterns.
     */
    public int repeatedStringMatch(String A, String B) {
        int m = A.length();
        int n = B.length();
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while(len < n){
            sb.append(A);
            len += m;
        }

        if(sb.toString().contains(B)) return len / m;
        sb.append(A);
        len += m;
        if(sb.toString().contains(B)) return len / m;
        return -1;
    }
}
