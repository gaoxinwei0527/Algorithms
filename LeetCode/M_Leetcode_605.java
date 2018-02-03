package LeetCode;

/**
 605. Can Place Flowers

 Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

 Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

 Example 1:
 Input: flowerbed = [1,0,0,0,1], n = 1
 Output: True
 Example 2:
 Input: flowerbed = [1,0,0,0,1], n = 2
 Output: False
 Note:
 The input array won't violate no-adjacent-flowers rule.
 The input array size is in the range of [1, 20000].
 n is a non-negative integer which won't exceed the input array size.
 */
public class M_Leetcode_605 {
    /**
     * @param flowerbed
     * @param n
     * @return
     *
     * dp
     *
     * T(i) means the max num of flowers we can plant in [0 - i]
     * if(f[i] == 1) T(i) = T(i - 2)
     * else {
     *     if(f[i - 1] == 1 || f[i + 1] == 1) T(i) = T(i - 1)
     *     else T(i) = max(T(i - 2) + 1, T(i - 1))
     * }
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed.length == 0) return n == 0;
        if(flowerbed.length == 1){
            if(flowerbed[0] == 1 && n == 0) return true;
            if(flowerbed[0] == 0 && n <= 1) return true;
            return false;
        }
        int x = ((flowerbed[0] == 0 && flowerbed[1] == 0) ? 1 : 0);
        int y = ((flowerbed[0] == 1 || flowerbed[1] == 1 || (flowerbed.length > 2 && flowerbed[2] == 1)) ? x : 1);
        for(int i = 2; i < flowerbed.length; i++){
            if(flowerbed[i] == 1){
                int tmp = x;
                x = y;
                y = tmp;
            }else{
                if(flowerbed[i - 1] == 1 || i + 1 < flowerbed.length && flowerbed[i + 1] == 1){
                    x = y;
                }else{
                    int tmp = Math.max(y, x + 1);
                    x = y;
                    y = tmp;
                }
            }
        }

        return (y >= n);
    }

    /*
     * @param flowerbed
     * @param n
     * @return
     *
     * greedy
     */
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int max = 0;
        for(int i = 0; i < flowerbed.length; i++){
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)){
                max++;
                flowerbed[i] = 1;
            }
        }

        return max >= n;
    }
}
