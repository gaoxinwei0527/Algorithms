package LeetCode;

/**
 21. Merge Two Sorted Lists

 Merge two sorted linked lists and return it as a new list.
 The new list should be made by splicing together the nodes of the first two lists.
 */
public class L_Solution_21 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param l1
     * @param l2
     * @return
     *
     * Time - O(m + n)
     * Space - O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
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
        }

        if(l1 != null){
            cur.next = l1;
        }

        if(l2 != null){
            cur.next = l2;
        }

        return dummy.next;
    }
}
