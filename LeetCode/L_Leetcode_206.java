package LeetCode;

import java.util.Stack;

/**
 206. Reverse Linked List

 Reverse a singly linked list.
 */
public class L_Leetcode_206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * easiest way, use stack to reverse the linked list
     *
     * Time - O(n)
     * Space - O(n)
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        Stack<ListNode> st = new Stack<>();
        while(head != null) {
            ListNode tmp = head.next;
            head.next = null;
            st.push(head);
            head = tmp;
        }

        head = st.pop();
        ListNode cur = head;
        while(!st.isEmpty()){
            cur.next = st.pop();
            cur = cur.next;
        }
        return head;
    }

    /**
     * @param head
     * @return
     *
     * recursive way.
     */
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode tmp = head.next;
        head.next = null;
        ListNode reversed = reverseList(tmp);
        ListNode cur = reversed;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = head;
        return reversed;
    }

    /**
     * @param head
     * @return
     *
     * in-place reverse
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode reverseList3(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head.next;
        ListNode next = head.next.next;
        while(cur != null){
            head.next = next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
            if(cur != null) next = cur.next;
        }
        return dummy.next;
    }
}
