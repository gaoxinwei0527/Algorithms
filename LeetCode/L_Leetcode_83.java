package LeetCode;

/**
 83. Remove Duplicates from Sorted List

 Given a sorted linked list, delete all duplicates such that each element appear only once.

 For example,
 Given 1->1->2, return 1->2.
 Given 1->1->2->3->3, return 1->2->3.
 */
public class L_Leetcode_83 {
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
        while(head != null){
            if(cur == dummy || cur.val != head.val){
                cur.next = head;
                cur = cur.next;
                head = head.next;
                cur.next = null;
            }else{
                head = head.next;
            }
        }

        return dummy.next;
    }
}
