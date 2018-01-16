package LeetCode;

/**
 69. Sqrt(x)

 Implement int sqrt(int x).

 Compute and return the square root of x.

 x is guaranteed to be a non-negative integer.


 Example 1:

 Input: 4
 Output: 2
 Example 2:

 Input: 8
 Output: 2
 Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.
 */
public class H_Leetcode_69 {
    /**
     * @param x
     * @return
     *
     * binary search
     *
     * Time - O(logN)
     * Space - O(1)
     */
    public int mySqrt(int x) {
        if(x <= 0) return 0;
        long l = 1;
        long r = x;
        while(l <= r){
            long m = l + (r - l) / 2;
            long square = m * m;
            if(square == x) return (int)m;
            if(square < x){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }

        return (int)(l - 1);
    }

    /**
     * @param x
     * @return
     *
     * 牛顿法
     */
    public int mySqrt2(int x) {
        long r = x;
        while(r * r > x){
            r = (r * r + x) / (2 * r);
        }

        return (int)r;
    }
}
