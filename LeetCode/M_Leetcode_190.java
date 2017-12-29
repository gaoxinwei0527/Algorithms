package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 190. Reverse Bits

 Reverse bits of a given 32 bits unsigned integer.

 For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

 Follow up:
 If this function is called many times, how would you optimize it?

 Related problem: Reverse Integer

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
public class M_Leetcode_190 {
    /**
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        long res = 0;
        for(int i = 0; i < 32; i++){
            long next = ((long) n >> i) & 1;
            res += (next << (31 - i));
        }

        return (int) res;
    }

    private final Map<Byte, Integer> cache = new HashMap<>();

    /**
     * @param n
     * @return
     *
     * to optimize against the follow-up
     * reverse byte by byte. and cache the byte results.
     */
    public int reverseBits2(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) // convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3)
                result <<= 8;
        }
        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b); // first look up from cache
        if (value != null)
            return value;
        value = 0;

        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7)
                value <<= 1;
        }
        cache.put(b, value);
        return value;
    }
}
