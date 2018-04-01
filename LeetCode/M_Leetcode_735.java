package LeetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 735. Asteroid Collision

 We are given an array asteroids of integers representing asteroids in a row.

 For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

 Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 Example 1:
 Input:
 asteroids = [5, 10, -5]
 Output: [5, 10]
 Explanation:
 The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 Example 2:
 Input:
 asteroids = [8, -8]
 Output: []
 Explanation:
 The 8 and -8 collide exploding each other.
 Example 3:
 Input:
 asteroids = [10, 2, -5]
 Output: [10]
 Explanation:
 The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 Example 4:
 Input:
 asteroids = [-2, -1, 1, 2]
 Output: [-2, -1, 1, 2]
 Explanation:
 The -2 and -1 are moving left, while the 1 and 2 are moving right.
 Asteroids moving the same direction never meet, so no asteroids will meet each other.
 Note:

 The length of asteroids will be at most 10000.
 Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 */
public class M_Leetcode_735 {
    /**
     * @param asteroids
     * @return
     *
     * iterate over all the asteroids, and maintain a stack to store asteroids going right.
     * 1. if we get a asteroid going right, push to stack
     * 2. if we get a asteroid going left, keep remove smaller stack.peek() since they will be destroyed.
     *   2.1 if we get stack.peek() + asteroid == 0, destroy both
     *   2.2 if we get stack.isEmpty(), means all asteroids processed and going right will be destroyed by this asteroid, then we can add this asteroid to result.
     *
     * at the end, we get all survive going right asteroids in stack in reversed order. put all of them to result.
     */
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        if(n <= 1) return asteroids;
        Deque<Integer> right = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) right.offerLast(asteroid);
            else {
                while (!right.isEmpty() && (right.peekLast() + asteroid < 0)) right.pollLast();
                if (!right.isEmpty() && (right.peekLast() + asteroid == 0)) {
                    right.pollLast();
                } else if (right.isEmpty()) {
                    res.add(asteroid);
                }
            }
        }

        while(!right.isEmpty()) res.add(right.pollFirst());
        int[] r = new int[res.size()];
        for(int i = 0; i < res.size(); i++) r[i] = res.get(i);
        return r;
    }
}
