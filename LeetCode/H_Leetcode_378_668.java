package LeetCode;

public class H_Leetcode_378_668 {
    /**
     378. Kth Smallest Element in a Sorted Matrix

     Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

     Note that it is the kth smallest element in the sorted order, not the kth distinct element.

     Example:

     matrix = [
     [ 1,  5,  9],
     [10, 11, 13],
     [12, 13, 15]
     ],
     k = 8,

     return 13.
     Note:
     You may assume k is always valid, 1 ≤ k ≤ n2.
     */


    /**
     668. Kth Smallest Number in Multiplication Table

     Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

     Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

     Example 1:
     Input: m = 3, n = 3, k = 5
     Output:
     Explanation:
     The Multiplication Table:
     1	2	3
     2	4	6
     3	6	9

     The 5-th smallest number is 3 (1, 2, 2, 3, 3).
     Example 2:
     Input: m = 2, n = 3, k = 6
     Output:
     Explanation:
     The Multiplication Table:
     1	2	3
     2	4	6

     The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
     Note:
     The m and n will be in the range [1, 30000].
     The k will be in the range [1, m * n]
     */

    /*
    binary search on possible value, then binary search each row to decide the value position.
    */
    public int findKthNumber(int m, int n, int k) {
        long l = 1;
        long r = (long)m * (long)n;
        while(l <= r){
            long mid = (l + r) >> 1;
            long count = count(m, n, mid);
            if(count >= k) r = mid - 1;
            else l = mid + 1;
        }

        return (int)l;
    }

    private long count(long m, long n, long target){
        long count = 0;
        for(long i = 1; i <= m; i++){
            long l = 1;
            long r = n;
            while(l <= r){
                long mid = (l + r) >> 1;
                if(mid * i <= target){
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }

            count += (l - 1);
        }

        return count;
    }

    /**
     * @param m
     * @param n
     * @param k
     * @return
     *
     * some optimization, to determine the position of target, we don't need to binary search each row.
     * for each row, we can do count += Math.min(n, target / i); this is utilizing the feature of multiplication table.
     */
    public int findKthNumber2(int m, int n, int k) {
        long l = 1;
        long r = (long)m * (long)n;
        while(l <= r){
            long mid = (l + r) >> 1;
            long count = count2(m, n, mid);
            if(count >= k) r = mid - 1;
            else l = mid + 1;
        }

        return (int)l;
    }

    private long count2(long m, long n, long target){
        long count = 0;
        for(long i = 1; i <= m; i++){
            count += Math.min(n, target / i);
        }

        return count;
    }
}
