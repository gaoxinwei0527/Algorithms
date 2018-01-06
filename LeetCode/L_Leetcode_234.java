package LeetCode;

/**
 234. Palindrome Linked List

 Given a singly linked list, determine if it is a palindrome.

 Follow up:
 Could you do it in O(n) time and O(1) space?
 */
public class L_Leetcode_234 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * @param head
     * @return
     *
     * 1. find mid point
     * 2. reverse second half
     * 3. compare first half with reversed second half
     *
     * Time - O(n)
     * Space - O(1)
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode l2 = slow.next;
        slow.next = null;

        ListNode reversed = reverse(l2);
        while(head != null && reversed != null){
            if(head.val != reversed.val) return false;
            head = head.next;
            reversed = reversed.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head;
        ListNode cur = head.next;
        ListNode next = head.next.next;
        while(cur != null){
            cur.next = dummy.next;
            tail.next = next;
            dummy.next = cur;
            cur = next;
            if(cur != null) next = cur.next;
        }
        return dummy.next;
    }
}
