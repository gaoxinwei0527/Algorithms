package LeetCode;

/**
 147. Insertion Sort List

 Sort a linked list using insertion sort.
 */
public class L_Leetcode_147 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * normal insertion sort
     *
     * Time - O(n ^ 2)
     * Space - O(1)
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        while(cur != null){
            ListNode insert = dummy;
            while(insert.next != null && insert.next.val < cur.val){
                insert = insert.next;
            }
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = insert.next;
            insert.next = tmp;
        }
        return dummy.next;
    }
}
