package LeetCode;

/**
 9. Palindrome Number

 Determine whether an integer is a palindrome. Do this without extra space.
 */
public class M_Solution_9 {
    /**
     * @param x
     * @return
     *
     * reverse x as y and compare x and y
     */
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        if(x <= 9) return true;
        if(x % 10 == 0) return false;

        long y = 0;
        int k = x;
        while(k > 0){
            y = y * 10 + k % 10;
            k /= 10;
        }

        return y == x;
    }
}
