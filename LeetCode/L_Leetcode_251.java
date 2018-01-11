package LeetCode;

import java.util.Iterator;
import java.util.List;

/**
 251. Flatten 2D Vector

 Implement an iterator to flatten a 2d vector.

 For example,
 Given 2d vector =

 [
 [1,2],
 [3],
 [4,5,6]
 ]
 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

 Follow up:
 As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class L_Leetcode_251 {
    public class Vector2D implements Iterator<Integer> {
        int i = 0;
        List<List<Integer>> vec;
        Iterator<Integer> iterator;

        public Vector2D(List<List<Integer>> vec2d) {
            this.vec = vec2d;
            while(i < vec.size() && vec.get(i).isEmpty()) i++;
            if(i < vec.size()) iterator = vec.get(i++).iterator();
            else iterator = null;
        }

        @Override
        public Integer next() {
            Integer next = iterator.next();
            if(!iterator.hasNext()){
                while(i < vec.size() && vec.get(i).isEmpty()) i++;
                if(i < vec.size()) iterator = vec.get(i++).iterator();
                else iterator = null;
            }
            return next;
        }

        @Override
        public boolean hasNext() {
            return (iterator != null);
        }
    }

}
