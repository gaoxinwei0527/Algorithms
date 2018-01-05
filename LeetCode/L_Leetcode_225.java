package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 225. Implement Stack using Queues

 Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.
 Notes:
 You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 Credits:
 Special thanks to @jianchao.li.fighter for adding this problem and all test cases.
 */
public class L_Leetcode_225 {
    class MyStack {
        Queue<Integer> q = new LinkedList<>();

        /** Initialize your data structure here. */
        public MyStack() {
            //no-op
        }

        /** Push element x onto stack. */
        public void push(int x) {
            q.offer(x);
            for(int i = 0; i < q.size() - 1; i++){
                q.offer(q.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return q.poll();
        }

        /** Get the top element. */
        public int top() {
            return q.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }
    }
}
