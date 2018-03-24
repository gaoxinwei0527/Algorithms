package LeetCode;

/**
 65. Valid Number

 Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

 Update (2015-02-10):
 The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
 */
public class H_Leetcode_65 {
    /**
     * @param s
     * @return
     *
     * handler all corner cases
     *
     * Time - O(n)
     * Space - O(1)
     */
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }

    /**
     * @param s
     * @return
     *
     * 1. handle the e firstly, then split s by 'e', then handle the two (or just one) parts separately
     * 2. for each part, handle '+' / '-', then split remaining part by '.' (if any), then handle the substrings as pure numbers.
     */
    public boolean isNumber2(String s) {
        s = s.trim();
        char[] arr = s.toCharArray();
        int len = arr.length;
        if(len == 0) return false;

        boolean e = false;
        for(int i = 0; i < len; i++){
            if(arr[i] != '.' && arr[i] != 'e' && arr[i] != '-' && arr[i] != '+' && (arr[i] < '0' || arr[i] > '9')) return false;
            else if(arr[i] == 'e'){
                if(e || i == 0 || i == len - 1) return false;
                e = true;
            }
        }
        if(!e) return isValid(s, false);
        if(len == 1) return false;

        String[] parts = s.split("e");
        if(!isValid(parts[0], false)) return false;
        if(!isValid(parts[1], true)) return false;

        return true;
    }

    private boolean isValid(String p, boolean exp){
        char[] arr = p.toCharArray();
        int len = arr.length;

        boolean dot = false;
        for(int i = 0; i < len; i++){
            if(arr[i] == '.'){
                if(dot) return false;
                dot = true;
            }
        }

        if(dot && exp) return false;

        int i = 0;
        while(i < len && (arr[i] == '+' || arr[i] == '-')) i++;

        if(i == len) return false;

        p = p.substring(i);
        if(!dot) return isValid2(p);
        if(p.length() == 1) return false;

        String[] parts = p.split("\\.");
        for(String p2 : parts){
            if(!isValid2(p2)) return false;
        }

        return true;
    }

    private boolean isValid2(String p){
        char[] arr = p.toCharArray();
        int i = 0;
        int len = arr.length;
        while(i < len && arr[i] >= '0' && arr[i] <= '9') i++;
        return i == len;
    }
}
