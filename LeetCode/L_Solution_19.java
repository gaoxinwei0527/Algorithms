package LeetCode;

/**
 19. Remove Nth Node From End of List

 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 */
public class L_Solution_19 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param n
     * @return
     *
     * Maintain pointer l and r, and make their distance as n - 1.
     * So when r reaches last node, l would be on the node that need to be removed
     *
     * Time - O(N), where N is num of ListNodes in lined list
     * Space - O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode r = dummy;
        while(n > 0){
            r = r.next;
            n--;
        }

        ListNode pre = dummy;
        ListNode l = head;
        while(r.next != null){
            r = r.next;
            pre = pre.next;
            l = l.next;
        }

        pre.next = l.next;
        l.next = null;

        return dummy.next;
    }
}
