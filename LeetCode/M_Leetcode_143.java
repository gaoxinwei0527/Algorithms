package LeetCode;

/**
 143. Reorder List

 Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

 You must do this in-place without altering the nodes' values.

 For example,
 Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class M_Leetcode_143 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     *
     * 3 steps-
     * 1. find mid point with slow / fast pointer, and break in the mid to l1 & l2
     * 2. reverse l2
     * 3. merge l1 and reversed l2
     *
     * Time - O(n)
     * Space - O(1)
     */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode l1 = head;
        ListNode l2 = slow.next;
        slow.next = null;

        ListNode next = l2.next;
        l2.next = null;
        while(next != null){
            ListNode tmp = next.next;
            next.next = l2;
            l2 = next;
            next = tmp;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        boolean first = true;
        while(l1 != null && l2 != null){
            if(first){
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
                cur.next = null;
            }else{
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
                cur.next = null;
            }

            first = !first;
        }

        if(l1 != null){
            cur.next = l1;
        }

        if(l2 != null){
            cur.next = l2;
        }
    }
}
