package LeetCode;

/**
 681. Next Closest Time

 Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

 You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

 Example 1:

 Input: "19:34"
 Output: "19:39"
 Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 Example 2:

 Input: "23:59"
 Output: "22:22"
 Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */
public class L_Leetcode_681 {
    /**
     * @param time
     * @return
     *
     * start from end to start, try to increase the digit and get a valid time.
     * if failed for all digits, means the time need to go back to the smallest one (constructed by the smallest digit)
     */
    public String nextClosestTime(String time) {
        char[] d = time.replaceAll(":", "").toCharArray();
        char m = findMin(d);
        for(int i = 3; i >= 0; i--){
            char next = findNext(d, d[i]);
            if(next != 'x'){
                char tmp = d[i];
                d[i] = next;
                if(valid(d)){
                    for(int k = i + 1; k < 4; k++) d[k] = m;
                    return "" + d[0] + d[1] + ":" + d[2] + d[3];
                }
                d[i] = tmp;
            }
        }

        return "" + m + m + ":" + m + m;
    }

    private char findNext(char[] d, char c){
        char res = 'x';
        for(int i = 0; i < 4; i++){
            if(d[i] > c && (res == 'x' || res > d[i])) res = d[i];
        }
        return res;
    }

    private boolean valid(char[] d){
        int hr = Integer.parseInt("" + d[0] + d[1]);
        int min = Integer.parseInt("" + d[2] + d[3]);
        return hr >= 0 && hr <= 23 && min >= 0 && min <= 59;
    }

    private char findMin(char[] d){
        char min = d[0];
        for(int i = 1; i < 4; i++){
            if(min > d[i]) min = d[i];
        }

        return min;
    }
}
