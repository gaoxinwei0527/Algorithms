package LeetCode;

import java.util.Iterator;
import java.util.Stack;

/**
 284. Peeking Iterator

 Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

 Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

 Call next() gets you 1, the first element in the list.

 Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

 You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

 Follow up: How would you extend your design to be generic and work with all types, not just integer?

 Credits:
 Special thanks to @porker2008 for adding this problem and creating all test cases.
 */
public class L_Leetcode_284 {
    class PeekingIterator implements Iterator<Integer> {
        Stack<Integer> peek = new Stack<>();
        Iterator<Integer> iterator;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if(peek.isEmpty()) peek.push(iterator.next());
            return peek.peek();
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if(!peek.isEmpty()) return peek.pop();
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext() || !peek.isEmpty();
        }
    }
}
