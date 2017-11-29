package LeetCode;

/**
 38. Count and Say

 The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"
 */
public class L_Solution_38 {
    /**
     * @param n
     * @return
     *
     * simple brute force way
     */
    public String countAndSay(int n) {
        String res = "1";
        for(int i = 1; i < n; i++){
            res = next(res);
        }
        return res;
    }

    private String next(String prev){
        StringBuilder next = new StringBuilder();
        char[] arr = prev.toCharArray();
        int i = 0;
        while(i < arr.length){
            int j = i;
            while(j < arr.length && arr[j] == arr[i]){
                j++;
            }

            next.append((char)('0' + (j - i))).append(arr[i]);
            i = j;
        }

        return next.toString();
    }
}
