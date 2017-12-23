package LeetCode;

/**
 148. Sort List

 Sort a linked list in O(n log n) time using constant space complexity.
 */
public class L_Leetcode_148 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * simulate the merge sort
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(tmp);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
                cur.next = null;
            }else{
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
                cur.next = null;
            }
        }

        if(l1 != null) cur.next = l1;
        if(l2 != null) cur.next = l2;
        return dummy.next;
    }
}
