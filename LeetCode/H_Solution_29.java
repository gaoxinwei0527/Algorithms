package LeetCode;

/**
 29. Divide Two Integers

 Divide two integers without using multiplication, division and mod operator.

 If it is overflow, return MAX_INT.
 */
public class H_Solution_29 {
    /**
     * @param dividend
     * @param divisor
     * @return
     *
     * Every time, try to substract (divisor << k) from dividend, until dividend < divisor
     * So every time we can add (1 << k) to result.
     * To avoid overflow / underflow, use long type and remove sign for all intermediate results.
     *
     * Time - O(1), because bit length is fixed for int type
     * Space - O(1)
     */
    public int divide(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        if(dividend == 0) return 0;
        boolean positive = true;
        if(dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) positive = false;
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long res = 0;
        while(a >= b){
            long k = b;
            long base = 1;
            while(a >= (k << 1)){
                k <<= 1;
                base <<= 1;
            }

            a -= k;
            res += base;
        }

        res = positive ? res : -res;
        if(res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) res;
    }
}
