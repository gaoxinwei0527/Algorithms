package LeetCode;

/**
 206. Reverse Linked List

 Reverse a singly linked list.
 */
public class M_Leetcode_206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * in-place way
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        while(cur != null && cur.next != null){
            ListNode tmp = cur.next.next;
            cur.next.next = dummy.next;
            dummy.next = cur.next;
            cur.next = tmp;
        }
        return dummy.next;
    }
}
