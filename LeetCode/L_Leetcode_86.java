package LeetCode;

/**
 86. Partition List

 Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 For example,
 Given 1->4->3->2->5->2 and x = 3,
 return 1->2->2->4->3->5.
 */
public class L_Leetcode_86 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @param x
     * @return
     *
     * simple one pass
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode d1 = new ListNode(0);
        ListNode d2 = new ListNode(0);

        ListNode cur1 = d1;
        ListNode cur2 = d2;
        while(head != null){
            if(head.val < x){
                cur1.next = head;
                cur1 = cur1.next;
            }else{
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = head.next;
            cur1.next = null;
            cur2.next = null;
        }

        cur1.next = d2.next;
        return d1.next;
    }
}
