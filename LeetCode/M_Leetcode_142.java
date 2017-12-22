package LeetCode;

/**
 142. Linked List Cycle II

 Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

 Note: Do not modify the linked list.

 Follow up:
 Can you solve it without using extra space?
 */
public class M_Leetcode_142 {
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
     * first use slow / fast pointer to detect cycle, if there is a cycle-
     * let's say
     * 1. x = distance from head to cycle point
     * 2. y = distance from cycle point to meet point
     * 3. z = distance from meet point to cycle point (loop back)
     *
     * then for slow pointer, it traveled x + y when it meets fast
     * for fast pointer, it traveled x + 2 * y + z to meet slow.
     * so we have 2 * (x + y) = x + 2 * y + z;
     * then we have x == z;
     * then we can reset slow to head, and keep fast at meet point.
     * Then for both pointer, we move 1 step each time until they meet, they will meet at cycle point.
     *
     * Time - O(n)
     * Space - O(1)
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast != null && fast.next != null && slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }

        if(slow != fast) return null; // no cycle
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
