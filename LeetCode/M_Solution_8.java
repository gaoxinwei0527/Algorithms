package LeetCode;

/**
 8. String to Integer (atoi)

 Implement atoi to convert a string to an integer.

 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

 Update (2015-02-10):
 The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

 */
public class M_Solution_8 {
    /**
     * @param str
     * @return
     *
     * corner cases include-
     * 1. spaces
     * 2. include both '+' and '-'
     * 3. empty
     * 4. include non numeric char
     * 5. overflow
     */
    public int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0) return 0;
        if(str.contains("+") && str.contains("-")) return 0;

        char[] arr = str.toCharArray();
        int i = 0;
        boolean neg = false;
        if(arr[0] == '-'){
            neg = true;
            i++;
        }

        if(arr[0] == '+'){
            i++;
        }

        while(i < arr.length && arr[i] == '0'){
            i++;
        }

        if(i == arr.length) return 0;

        int j = i;
        while(j < arr.length && arr[j] >= '0' && arr[j] <= '9') j++;
        j--;

        long res = 0;
        long base = 1;
        while(i <= j){
            res += ((long)(arr[j--] - '0')) * base;
            base *= 10;
            if(!neg && res > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }

            if(neg && -res < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }

        return (int)(neg ? -res : res);
    }
}
