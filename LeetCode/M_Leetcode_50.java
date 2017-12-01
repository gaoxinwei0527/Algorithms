package LeetCode;

/**
 50. Pow(x, n)

 Implement pow(x, n).


 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 */
public class M_Leetcode_50 {
    /**
     * @param x
     * @param n
     * @return
     *
     * divide and conquer
     * Note that myPow(x,n/2) should be saved and reused to save recursion times.
     */
    public double myPow(double x, int n) {
        if(n == 1) return x;
        if(n == -1) return 1.0 / x;
        if(n == 0) return 1.0;
        if(x == 0) return 0;

        double half=myPow(x,n/2);
        if(n % 2 == 0){
            return half * half;
        }else{
            double k = n > 0 ? x : (1.0 / x);
            return half * half * k;
        }
    }
}
