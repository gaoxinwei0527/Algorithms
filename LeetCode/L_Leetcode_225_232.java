package LeetCode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class L_Leetcode_225_232 {
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

    /**
     232. Implement Queue using Stacks

     Implement the following operations of a queue using stacks.

     push(x) -- Push element x to the back of queue.
     pop() -- Removes the element from in front of queue.
     peek() -- Get the front element.
     empty() -- Return whether the queue is empty.
     Notes:
     You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
     Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
     You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
     */

    class MyQueue {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();

        /** Initialize your data structure here. */
        public MyQueue() {
            // no-op
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            in.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(out.isEmpty()){
                while(!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(out.isEmpty()){
                while(!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return (in.isEmpty() && out.isEmpty());
        }
    }
}
