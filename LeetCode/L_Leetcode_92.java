package LeetCode;

/**
 92. Reverse Linked List II

 Reverse a linked list from position m to n. Do it in-place and in one-pass.

 For example:
 Given 1->2->3->4->5->NULL, m = 2 and n = 4,

 return 1->4->3->2->5->NULL.

 Note:
 Given m, n satisfy the following condition:
 1 ≤ m ≤ n ≤ length of list.
 */
public class L_Leetcode_92 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param m
     * @param n
     * @return
     *
     * simple linkedlist op
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m >= n) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode l = head;
        ListNode r = head;
        while(m > 1){
            l = l.next;
            pre = pre.next;
            m--;
        }

        while(n > 1){
            r = r.next;
            n--;
        }

        while(l != r){
            pre.next = l.next;
            l.next = r.next;
            r.next = l;
            l = pre.next;
        }

        return dummy.next;
    }
}
