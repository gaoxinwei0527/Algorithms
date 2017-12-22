package LeetCode;

/**
 141. Linked List Cycle

 Given a linked list, determine if it has a cycle in it.

 Follow up:
 Can you solve it without using extra space?
 */
public class L_Leetcode_141 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @param head
     * @return
     *
     * classic slow / fast pointer way
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast != null && fast.next != null && slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }
        return (slow == fast);
    }
}
