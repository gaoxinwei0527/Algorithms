package LeetCode;

/**
 24. Swap Nodes in Pairs

 Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
public class L_Solution_24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * simple iterative way
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode l = head;
        ListNode r = head.next;
        while(l != null && r != null){
            pre.next = r;
            l.next = r.next;
            r.next = l;
            pre = l;
            l = pre.next;
            if(l != null) r = l.next;
        }

        return dummy.next;
    }

    /**
     * @param head
     * @return
     *
     * recursive
     */
    public ListNode swapPairs2(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}
