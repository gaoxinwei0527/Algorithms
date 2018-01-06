package LeetCode;

/**
 233. Number of Digit One

 Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

 For example:
 Given n = 13,
 Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class H_Leetcode_233 {
    /**
     * @param n
     * @return
     *
     * logic is simple, say n has k digits - [k, k-1, ..., 2, 1];
     * if we fix digit i to 1, we can get prefix [k, k-1, .., i + 1] as max_prefix, surfix is (10 ^ (i - 1));
     * then the permutation with digit i as 1 is (max_prefix + 1) * surfix, means prefix could be [0, max_prefix];
     *
     * corner cases-
     * 1. if digit[i] == 0, then we cannot include max_prefix as prefix, because (max_prefix + 1 + surfix) > n;
     * 2. if digit[i] == 1, when prefix is max_prefix, surfix is n % (10 ^ (i - 1)) + 1, means the range [0, remainings]
     */
    public int countDigitOne(int n) {
        long m = 1;
        long count = 0;
        while(m <= n){
            long max_prefix = (n / m) / 10;
            long prefix = max_prefix + 1; // 0 is also a valid prefix, means nums start at m position, so need to + 1
            long surfix = m;
            long cur = (n / m) % 10; // current processing digit

            if(cur == 0) prefix--; // current digit cannot be 1 when prefix is max_prefix, as it will exceed n

            long remaining = 0;
            if(cur == 1){ // if current digit is 1, means when prefix is max_prefix, num of surfix won't exceed (n % m + 1)
                prefix--;
                remaining = n % m + 1;
            }

            count += ((prefix * surfix) + remaining);
            m *= 10;
        }

        return (int)count;
    }
}
