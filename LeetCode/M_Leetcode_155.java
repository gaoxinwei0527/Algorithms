package LeetCode;

import java.util.Stack;

/**
 155. Min Stack

 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.
 */
public class M_Leetcode_155 {
    /**
     * Use stack as internal data structure.
     * But instead of just store values, also store the current min, then st.peek().min would be current minimal element.
     *
     * Time - O(1) for all ops
     * Space - O(n)
     */
    class MinStack {
        private class Node{
            public int val;
            public int min;
            public Node(int val, int min){
                this.val = val;
                this.min = min;
            }
        }

        Stack<Node> st;

        /** initialize your data structure here. */
        public MinStack() {
            st = new Stack<>();
        }

        public void push(int x) {
            int min = (st.isEmpty() ? x : Math.min(x, st.peek().min));
            Node next = new Node(x, min);
            st.push(next);
        }

        public void pop() {
            st.pop();
        }

        public int top() {
            return st.peek().val;
        }

        public int getMin() {
            return st.peek().min;
        }
    }
}
