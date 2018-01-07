package LeetCode;

public class H_Leetcode_50_372 {
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

    /**
     372. Super Pow

     Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

     Example1:

     a = 2
     b = [3]

     Result: 8
     Example2:

     a = 2
     b = [1,0]

     Result: 1024
     Credits:
     Special thanks to @Stomach_ache for adding this problem and creating all test cases.
     */

    /**
     * @param a
     * @param b
     * @return
     *
     * utilize the feature of modular exponentiation
     *
     * a^200 mod b == (a^2 mod b)^100 mod b == ((a^2 mod b)^10 mod b)^10 mod b
     *
     * also utilize the pow function written before.
     */
    public int superPow(int a, int[] b) {
        a %= 1337;
        if(a == 0 || a == 1) return a;

        int res = 1;
        for(int i = 0; i < b.length; i++){
            res = pow(res, 10) * pow(a, b[i]) % 1337;
        }

        return res;
    }

    private int pow(int x, int n){
        if(n == 0) return 1;
        if(n == 1) return x % 1337;

        return pow(x % 1337, n / 2) * pow(x % 1337, n - n / 2) % 1337;
    }
}
