package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 716. Max Stack

 Design a max stack that supports push, pop, top, peekMax and popMax.

 push(x) -- Push element x onto stack.
 pop() -- Remove the element on top of the stack and return it.
 top() -- Get the element on the top.
 peekMax() -- Retrieve the maximum element in the stack.
 popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 Example 1:
 MaxStack stack = new MaxStack();
 stack.push(5);
 stack.push(1);
 stack.push(5);
 stack.top(); -> 5
 stack.popMax(); -> 5
 stack.top(); -> 1
 stack.peekMax(); -> 5
 stack.pop(); -> 1
 stack.top(); -> 5
 Note:
 -1e7 <= x <= 1e7
 Number of operations won't exceed 10000.
 The last four operations won't be called when stack is empty.
 */
public class H_Leetcode_716 {
    /**
     * make push, pop, top, peekMax O(1) and popMax O(n)
     */
    class MaxStack {
        class ListNode{
            public int val;
            public int max;
            public ListNode prev;
            public ListNode next;
            public ListNode(int val, int max){
                this.val = val;
                this.max = max;
            }
        }

        ListNode head;
        ListNode tail;
        Map<Integer, Stack<ListNode>> map;

        /** initialize your data structure here. */
        public MaxStack() {
            head = new ListNode(-1, -1);
            tail = new ListNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            map = new HashMap<>();
        }

        public void push(int x) {
            ListNode cur = new ListNode(x, head.next == tail ? x : Math.max(head.next.max, x));
            cur.next = head.next;
            head.next = cur;
            cur.next.prev = cur;
            cur.prev = head;
            if(!map.containsKey(x)) map.put(x, new Stack<>());
            map.get(x).push(cur);
        }

        public int pop() {
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next.prev = head;
            cur.next = null;
            cur.prev = null;
            map.get(cur.val).pop();
            return cur.val;
        }

        public int top() {
            return head.next.val;
        }

        public int peekMax() {
            return head.next.max;
        }

        public int popMax() {
            int max = head.next.max;
            ListNode maxNode = map.get(max).pop();
            if(maxNode.prev != head){
                ListNode cur = maxNode.prev;
                int local = maxNode.next == tail ? cur.val : maxNode.next.max;
                while(cur != head){
                    local = Math.max(cur.val, local);
                    cur.max = local;
                    cur = cur.prev;
                }
            }
            maxNode.prev.next = maxNode.next;
            maxNode.next.prev = maxNode.prev;
            maxNode.prev = null;
            maxNode.next = null;
            return max;
        }
    }
}
