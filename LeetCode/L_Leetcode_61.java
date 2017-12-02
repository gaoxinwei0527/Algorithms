package LeetCode;

/**
 61. Rotate List

 Given a list, rotate the list to the right by k places, where k is non-negative.


 Example:

 Given 1->2->3->4->5->NULL and k = 2,

 return 4->5->1->2->3->NULL.
 */
public class L_Leetcode_61 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param k
     * @return
     *
     * two pointers to hold l and r of the rotation part.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        ListNode l = head;
        ListNode r = head;
        ListNode cur = head;
        int len = 0;
        while(cur != null){
            len++;
            cur = cur.next;
        }

        k %= len;
        if(k == 0) return head;

        while(k > 0){
            r = r.next;
            k--;
        }

        while(r.next != null){
            r = r.next;
            l = l.next;
        }

        r.next = head;
        head = l.next;
        l.next = null;
        return head;
    }
}
