package LeetCode;

import java.util.TreeSet;

/**
 683. K Empty Slots

 There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

 Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

 For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

 Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

 If there isn't such day, output -1.

 Example 1:
 Input:
 flowers: [1,3,2]
 k: 1
 Output: 2
 Explanation: In the second day, the first and the third flower have become blooming.
 Example 2:
 Input:
 flowers: [1,2,3]
 k: 1
 Output: -1
 Note:
 The given array will be in the range [1, 20000].
 */
public class H_Leetcode_683 {
    /**
     * @param flowers
     * @param k
     * @return
     *
     * every time get a position, try to find the ceiling / floor in existing positions with a tree set,
     * if we get a ceiling / floor with distance as k to next position, then return the day num.
     * otherwise insert the next position to a tree set
     */
    public int kEmptySlots(int[] flowers, int k) {
        if(k > flowers.length - 2) return -1;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(-100000);
        set.add(100000);
        for(int i = 0; i < flowers.length; i++){
            int next = flowers[i];
            int ceiling = set.ceiling(next);
            int floor = set.floor(next);
            if(Math.abs(next - floor) - 1 == k || Math.abs(next - ceiling) - 1 == k){
                return i + 1;
            }
            set.add(next);
        }
        return -1;
    }

    /**
     * @param flowers
     * @param k
     * @return
     *
     * BIT, for each position i, if (range sum q(i - k, i) == 1 && bloom(i - k - 1)) ||
     * (range sum q(i, i + k) == 1 && bloom(i + k + 1)), then return the current day num
     */
    public int kEmptySlots2(int[] flowers, int k) {
        int len = flowers.length;
        int[] tree = new int[len + 1];
        int[] idx = new int[len + 1];
        for(int i = 0; i < len; i++){
            int next = flowers[i];
            idx[next] = 1;
            update(tree, next, 1);
            int mid = query(tree, next);
            if(next - k - 1 > 0 && idx[next - k - 1] == 1){
                int left = query(tree, next - k - 1);
                if(mid - left == 1) return i + 1;
            }

            if(next + k + 1 <= len && idx[next + k + 1] == 1){
                int right = query(tree, next + k + 1);
                if(right - mid == 1) return i + 1;
            }
        }

        return -1;
    }

    private int query(int[] tree, int i){
        int sum = 0;
        while(i > 0){
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }

    private void update(int[] tree, int i, int delta){
        while(i < tree.length){
            tree[i] += delta;
            i += (i & -i);
        }
    }
}
