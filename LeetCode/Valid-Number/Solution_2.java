/*
3 boolean variables.
Complexity: O(n)
*/

public class Solution_2 {
    public boolean isNumber(String s) {
        s = s.trim();
        if( s.length() == 0 ) return false;
        int start = 0;
        int end = s.length() - 1;
        boolean num = false;
        boolean dot = false;
        boolean exp = false;
        if( s.charAt(start) == '+' || s.charAt(start) == '-' ) start++;
        while( start <= end ) {
            char c = s.charAt(start);
            if( Character.isDigit(c) ) {
                num = true;
            }
            else if( c == '.' ) {
                if( dot || exp ) return false; // 3. and .3 are both valid
                dot = true;
            }
            else if( c == 'e' ) {
                if( !num || exp || start == end ) return false;
                exp = true;
            }
            else if( c == '+' || c == '-' ) {
                if( s.charAt(start-1) != 'e' || start == end ) return false; // if start - 1 is e, exp and num both true, so need to add
            }
            else {
                return false;
            }
            start++;
        }
        return num;
    }
}
