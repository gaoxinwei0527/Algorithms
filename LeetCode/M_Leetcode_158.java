package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 158. Read N Characters Given Read4 II - Call multiple times

 The API: int read4(char *buf) reads 4 characters at a time from a file.

 The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

 By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Note:
 The read function may be called multiple times.
 */
public class M_Leetcode_158 {
    class Reader4{
        /* The read4 API is defined in the parent class Reader4.*/
        int read4(char[] buf){return 0;}
    }

    public class Solution extends Reader4 {
        boolean eof = false;
        Queue<Character> remain = new LinkedList<>();

        /**
         * @param buf Destination buffer
         * @param n   Maximum number of characters to read
         * @return    The number of characters read
         *
         * Same as leetcode 157, only difference is need to have a queue to store remaining characters from last time read.
         *
         * Time - O(n)
         * Space - O(1)
         */
        public int read(char[] buf, int n) {
            if(n <= remain.size()){
                int i = 0;
                while(i < n){
                    buf[i++] = remain.poll();
                }
                return n;
            }

            int i = 0;
            while(!remain.isEmpty()){
                buf[i++] = remain.poll();
            }

            if(eof) return i;

            int read = n - i;
            while(read > 0 && !eof){
                char[] tmp = new char[4];
                int next = read4(tmp);
                eof = (next != 4);

                if(next > read){
                    System.arraycopy(tmp, 0, buf, i, read);
                    for(int k = read; k < next; k++){
                        remain.offer(tmp[k]);
                    }
                    i += read;
                    read = 0;
                }else{
                    System.arraycopy(tmp, 0, buf, i, next);
                    i += next;
                    read -= next;
                }
            }

            return i;
        }
    }
}
