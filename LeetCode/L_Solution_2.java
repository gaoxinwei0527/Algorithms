package LeetCode;

/**
 2. Add Two Numbers

 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */
public class L_Solution_2 {
    //Definition for singly-linked list.
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
     * Iterate both linked list and append sum to result linked list, also maintain the carry for each step.
     *
     * Time - O(max(m, n))
     * Space - O(max(m, n))
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            int a = (l1 == null ? 0 : l1.val);
            int b = (l2 == null ? 0 : l2.val);
            cur.next = new ListNode((a + b + carry) % 10);
            carry = (a + b + carry) / 10;
            cur = cur.next;
            l1 = (l1 == null ? null : l1.next);
            l2 = (l2 == null  ? null : l2.next);
        }

        if(carry > 0) cur.next = new ListNode(carry);
        return dummy.next;
    }

    /**
     * @param l1
     * @param l2
     * @return
     *
     * In-place, keep updating both linked list with sums. And return the head of longer list;
     * cons: need to modify the original list
     *
     * Time - O(max(m, n))
     * Space - O(1)
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int len_a = 0;
        int len_b = 0;
        ListNode h1 = l1;
        ListNode h2 = l2;
        ListNode prev = null;
        int carry = 0;
        while(l1 != null || l2 != null){
            int a = (l1 == null ? 0 : l1.val);
            int b = (l2 == null ? 0 : l2.val);
            prev = (l1 != null) ? l1 : l2;
            if(l1 != null){
                l1.val = (a + b + carry) % 10;
                l1 = l1.next;
                len_a++;
            }

            if(l2 != null){
                l2.val = (a + b + carry) % 10;
                l2 = l2.next;
                len_b++;
            }

            carry = (a + b + carry) / 10;
        }

        ListNode res = len_a >= len_b ? h1 : h2;
        if(carry > 0) prev.next = new ListNode(carry);
        return res;
    }
}
