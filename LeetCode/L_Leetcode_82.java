package LeetCode;

/**
 82. Remove Duplicates from Sorted List II

 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

 For example,
 Given 1->2->3->3->4->4->5, return 1->2->5.
 Given 1->1->1->2->3, return 2->3.
 */
public class L_Leetcode_82 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * simple duplicate remove
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode l = head;
        ListNode r = head;
        while(r != null){
            while(r.next != null && r.val == r.next.val){
                r = r.next;
            }

            if(l == r){
                cur.next = l;
                cur = cur.next;
                l = r.next;
                r = l;
                cur.next = null;
            }else{
                l = r.next;
                r = l;
            }
        }

        return dummy.next;
    }
}
