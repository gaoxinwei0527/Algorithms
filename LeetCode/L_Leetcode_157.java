package LeetCode;

/**
 157. Read N Characters Given Read4

 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function will only be called once for each test case.
 */
public class L_Leetcode_157 {
    class Reader4{
        /* The read4 API is defined in the parent class Reader4.*/
        int read4(char[] buf){return 0;}
    }

    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         *
         * the description is not very clear, we need to call read4(char[] buf) with a tmp array, then do array copy to the dest array.
         *
         */
        public int read(char[] buf, int n) {
            int total = 0;
            boolean eof = false;
            while(total < n && !eof){
                char[] tmp = new char[4];
                int next = read4(tmp);
                eof = (next != 4);
                int len = ((n - total) < next ? (n - total) : next);
                System.arraycopy(tmp, 0, buf, total, len);
                total += len;
            }

            return total;
        }
    }
}
